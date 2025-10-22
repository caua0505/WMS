/*
 * Repositório de Produtos (CORRIGIDO).
 * Substitui a antiga classe 'Estoque.java'.
 */
package Repository;

import Model.Produto;
import Model.GerenciadorArquivo; // Importa o gerenciador
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ProdutoRepository {

    // MUDANÇA: A lista não é mais 'static'
    private List<Produto> produtos;

    // NOVO: Instância do gerenciador
    private GerenciadorArquivo gerenciador;

    // Gerador de ID único
    private static AtomicInteger proximoId;

    // NOVO: Construtor que carrega dados do "produtos.txt"
    public ProdutoRepository() {
        // Aponta para o GerenciadorArquivo no pacote Model
        this.gerenciador = new Model.GerenciadorArquivo();
        this.produtos = gerenciador.carregarProdutos();

        // LÓGICA IMPORTANTE: Descobre qual é o MAIOR ID salvo no arquivo
        // para evitar IDs duplicados ao reiniciar o programa.
        int maxId = 0;
        for(Produto p : produtos) {
            if (p.getId() > maxId) {
                maxId = p.getId();
            }
        }
        // O próximo ID será (maior ID salvo + 1)
        proximoId = new AtomicInteger(maxId + 1);
    }

    /**
     * Adiciona um produto, define um ID único e salva no arquivo.
     */
    public void adicionar(Produto produto) {
        produto.setId(proximoId.getAndIncrement()); // Define o ID
        produtos.add(produto);
        // NOVO: Salva a lista atualizada no arquivo
        gerenciador.salvarProdutos(produtos);
    }

    public List<Produto> listarTodos() {
        return produtos;
    }

    public Produto buscarPorId(int id) {
        for (Produto p : produtos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    /**
     * Atualiza um produto na lista e salva no arquivo.
     */
    public void atualizar(Produto produto) {
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId() == produto.getId()) {
                produtos.set(i, produto);
                // NOVO: Salva a lista atualizada no arquivo
                gerenciador.salvarProdutos(produtos);
                return;
            }
        }
    }

    /**
     * Remove um produto da lista e salva no arquivo.
     */
    public void remover(int id) {
        if(produtos.removeIf(produto -> produto.getId() == id)) {
            // NOVO: Salva a lista atualizada no arquivo
            gerenciador.salvarProdutos(produtos);
        }
    }
}