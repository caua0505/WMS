/*
 * Controller de Produto (CORRIGIDO).
 * Recebe o repositório via Injeção de Dependência.
 */
package Controller;

import Model.Produto;
import Repository.ProdutoRepository;
import java.util.List;

public class ProdutoController {

    // MUDANÇA: Apenas declara o repositório
    private ProdutoRepository produtoRepository;

    /**
     * MUDANÇA: Construtor de Injeção de Dependência.
     * Recebe a instância única do repositório criada na Main.
     * @param produtoRepository A instância única do repositório.
     */
    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    // --- Métodos de negócio ---

    public Produto cadastrarProduto(String nome, int quantidade) {
        Produto produto = new Produto(nome, quantidade);
        produtoRepository.adicionar(produto);
        return produto;
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.listarTodos();
    }

    public void atualizarProduto(int id, String nome, int quantidade) {
        Produto produtoParaAtualizar = produtoRepository.buscarPorId(id);
        if (produtoParaAtualizar != null) {
            produtoParaAtualizar.setNome(nome);
            int diferenca = quantidade - produtoParaAtualizar.getQuantidade();
            if (diferenca > 0) {
                produtoParaAtualizar.adicionarQuantidade(diferenca);
            } else if (diferenca < 0) {
                produtoParaAtualizar.removerQuantidade(-diferenca);
            }
            // NOVO: Chama 'atualizar' para salvar a mudança no TXT
            produtoRepository.atualizar(produtoParaAtualizar);
        }
    }

    public void removerProduto(int id) {
        produtoRepository.remover(id);
    }

    public boolean definirLocalizacao(int id, int posicao, int local) {
        Produto produtoParaLocalizar = produtoRepository.buscarPorId(id);

        if (produtoParaLocalizar != null) {
            produtoParaLocalizar.setPosicao(posicao);
            produtoParaLocalizar.setLocal(local);
            // NOVO: Salva a alteração de localização no TXT
            produtoRepository.atualizar(produtoParaLocalizar);
            return true;
        }
        return false;
    }

    public Produto buscarProdutoPorCodigo(String codigo) {
        List<Produto> todosProdutos = produtoRepository.listarTodos();
        for (Produto produto : todosProdutos) {
            if (produto.getCodigo().equals(codigo)) {
                return produto;
            }
        }
        return null;
    }
}