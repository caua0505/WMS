package Controller;

import Model.Produto;
import Repository.ProdutoRepository;
import java.util.List;

public class ProdutoController {

    private ProdutoRepository produtoRepository;

    public ProdutoController() {
        this.produtoRepository = new ProdutoRepository();
    }

    // --- Seus métodos existentes ---
    public void cadastrarProduto(String nome, int quantidade) {
        Produto produto = new Produto(nome, quantidade);
        produtoRepository.adicionar(produto);
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
            produtoRepository.atualizar(produtoParaAtualizar);
        }
    }

    public void removerProduto(int id) {
        produtoRepository.remover(id);
    }

    // ========================================================================
    // MÉTODO 'definirLocalizacao' QUE ESTAVA FALTANDO
    // ========================================================================
    /**
     * Busca um produto pelo seu ID e define sua posição e local.
     * @param id O ID do produto a ser localizado.
     * @param posicao A posição do produto.
     * @param local O local do produto.
     * @return true se o produto foi encontrado e atualizado, false caso contrário.
     */
    public boolean definirLocalizacao(int id, int posicao, int local) {
        Produto produtoParaLocalizar = produtoRepository.buscarPorId(id);

        if (produtoParaLocalizar != null) {
            produtoParaLocalizar.setPosicao(posicao);
            produtoParaLocalizar.setLocal(local);
            produtoRepository.atualizar(produtoParaLocalizar); // Salva a alteração
            return true;
        }
        return false;
    }

    // ========================================================================
    // MÉTODO 'buscarProdutoPorCodigo' QUE ESTAVA FALTANDO
    // ========================================================================
    /**
     * Busca um produto pelo seu código alfanumérico.
     * @param codigo O código gerado aleatoriamente (ex: "123432").
     * @return O objeto Produto se encontrado, caso contrário, retorna null.
     */
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