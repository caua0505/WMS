/*
 * Repositório de Produtos.
 * Responsável por gerenciar a LISTA de produtos em memória
 * e persistir (salvar) essa lista no TXT.
 */
package Repository;

import Model.Produto;
import Model.GerenciadorArquivo;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ProdutoRepository {

    private List<Produto> produtos; // A lista de produtos em memória
    private GerenciadorArquivo gerenciador; // O objeto que sabe ler/escrever TXT
    private static AtomicInteger proximoId; // Um contador de ID seguro

    /**
     * Construtor.
     * Ao criar o repositório, ele imediatamente:
     * 1. Cria uma instância do Gerenciador.
     * 2. Pede ao gerenciador para carregar os produtos do "produtos.txt".
     * 3. Descobre qual foi o maior ID salvo para continuar a contagem.
     */
    public ProdutoRepository() {
        this.gerenciador = new Model.GerenciadorArquivo();
        this.produtos = gerenciador.carregarProdutos(); // Carrega do TXT

        // Lógica para encontrar o maior ID salvo e evitar IDs duplicados
        int maxId = 0;
        for (Produto p : produtos) {
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
        produto.setId(proximoId.getAndIncrement()); // Define o próximo ID
        produtos.add(produto); // Adiciona na lista em memória

        // --- "COMMIT" (Objetivo 2): A GRAVAÇÃO ---
        // Chama o gerenciador para salvar a lista ATUALIZADA no TXT.
        // É isso que corrige o "bug de não salvar".
        gerenciador.salvarProdutos(produtos);
    }

    /**
     * Atualiza um produto na lista e salva no arquivo.
     */
    public void atualizar(Produto produto) {
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId() == produto.getId()) {
                produtos.set(i, produto); // Atualiza na lista em memória

                // --- "COMMIT" (Objetivo 2): A GRAVAÇÃO ---
                gerenciador.salvarProdutos(produtos);
                return;
            }
        }
    }

    /**
     * Remove um produto da lista e salva no arquivo.
     */
    public void remover(int id) {
        // Remove o produto da lista em memória se o ID bater
        if (produtos.removeIf(produto -> produto.getId() == id)) {
            // --- "COMMIT" (Objetivo 2): A GRAVAÇÃO ---
            gerenciador.salvarProdutos(produtos);
        }
    }

    // --- Métodos de Leitura (não precisam salvar) ---

    public List<Produto> listarTodos() {
        return produtos; // Retorna a lista da memória
    }

    public Produto buscarPorId(int id) {
        for (Produto p : produtos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
}