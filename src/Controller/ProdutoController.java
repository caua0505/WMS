/*
 * Controller de Produto.
 *
 * "COMMIT": Esta classe segue a Injeção de Dependência (DI).
 * Ela não cria seu próprio repositório (ex: new ProdutoRepository()).
 * Em vez disso, ela RECEBE o repositório pronto no construtor.
 */
package Controller;

import Model.Produto;
import Repository.ProdutoRepository;
import java.util.List;

public class ProdutoController {

    // Apenas declara o repositório
    private ProdutoRepository produtoRepository;

    /**
     * Construtor de Injeção de Dependência.
     * Recebe a instância única do repositório criada na Main.
     */
    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    // --- Métodos de negócio ---
    // A View chama estes métodos. O Controller então delega
    // o trabalho pesado para o Repositório.

    public Produto cadastrarProduto(String nome, int quantidade) {
        Produto produto = new Produto(nome, quantidade);
        produtoRepository.adicionar(produto); // Delega
        return produto;
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.listarTodos(); // Delega
    }

    public void atualizarProduto(int id, String nome, int quantidade) {
        Produto produtoParaAtualizar = produtoRepository.buscarPorId(id);
        if (produtoParaAtualizar != null) {
            produtoParaAtualizar.setNome(nome);
            // Lógica de negócio (calcular diferença de estoque)
            int diferenca = quantidade - produtoParaAtualizar.getQuantidade();
            if (diferenca > 0) {
                produtoParaAtualizar.adicionarQuantidade(diferenca);
            } else if (diferenca < 0) {
                produtoParaAtualizar.removerQuantidade(-diferenca);
            }
            produtoRepository.atualizar(produtoParaAtualizar); // Delega a atualização
        }
    }

    public void removerProduto(int id) {
        produtoRepository.remover(id); // Delega
    }

    public boolean definirLocalizacao(int id, int posicao, int local) {
        Produto produtoParaLocalizar = produtoRepository.buscarPorId(id);
        if (produtoParaLocalizar != null) {
            produtoParaLocalizar.setPosicao(posicao);
            produtoParaLocalizar.setLocal(local);
            produtoRepository.atualizar(produtoParaLocalizar); // Delega
            return true;
        }
        return false;
    }

    public Produto buscarProdutoPorCodigo(String codigo) {
        // Lógica de negócio (busca)
        List<Produto> todosProdutos = produtoRepository.listarTodos();
        for (Produto produto : todosProdutos) {
            if (produto.getCodigo().equals(codigo)) {
                return produto;
            }
        }
        return null;
    }
}