/*
 * Repositório de Pedidos.
 * Lógica semelhante ao ProdutoRepository.
 *
 * "COMMIT": Este repositório é especial pois DEPENDE de outro repositório
 * (ProdutoRepository) para funcionar.
 */
package Repository;

import Model.Pedido;
import Model.GerenciadorArquivo;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PedidoRepository {

    private List<Pedido> pedidos; // Lista em memória
    private GerenciadorArquivo gerenciador;
    private static AtomicInteger proximoId;

    /**
     * MUDANÇA IMPORTANTE: Construtor do PedidoRepository.
     * Recebe o ProdutoRepository por Injeção de Dependência.
     *
     * @param produtoRepository A instância JÁ CARREGADA do repo de produtos.
     * É necessária para o 'carregarPedidos'
     * "reconectar" os produtos aos pedidos.
     */
    public PedidoRepository(ProdutoRepository produtoRepository) {
        this.gerenciador = new Model.GerenciadorArquivo();

        // Passamos o 'produtoRepository' para o 'carregarPedidos'
        this.pedidos = gerenciador.carregarPedidos(produtoRepository);

        // Lógica para encontrar o maior ID salvo
        int maxId = 0;
        for (Pedido p : pedidos) {
            if (p.getId() > maxId) {
                maxId = p.getId();
            }
        }
        proximoId = new AtomicInteger(maxId + 1);
    }

    /**
     * Adiciona um pedido, define um ID único e salva no arquivo.
     */
    public void adicionar(Pedido pedido) {
        pedido.setId(proximoId.getAndIncrement()); // Define o ID sequencial
        pedidos.add(pedido); // Adiciona na memória

        // --- "COMMIT" (Objetivo 2): A GRAVAÇÃO ---
        gerenciador.salvarPedidos(pedidos); // Salva no TXT
    }

    /**
     * Atualiza um pedido na lista e salva no arquivo.
     * (Usado para mudar o Status, por exemplo)
     */
    public void atualizar(Pedido pedidoAtualizado) {
        Pedido pedidoExistente = buscarPorId(pedidoAtualizado.getId());
        if (pedidoExistente != null) {
            int index = pedidos.indexOf(pedidoExistente);
            pedidos.set(index, pedidoAtualizado); // Atualiza na memória

            // --- "COMMIT" (Objetivo 2): A GRAVAÇÃO ---
            gerenciador.salvarPedidos(pedidos); // Salva no TXT
        }
    }

    /**
     * Remove um pedido da lista e salva no arquivo.
     */
    public void remover(int id) {
        if (pedidos.removeIf(pedido -> pedido.getId() == id)) { // Remove da memória
            // --- "COMMIT" (Objetivo 2): A GRAVAÇÃO ---
            gerenciador.salvarPedidos(pedidos); // Salva no TXT
        }
    }

    // --- Métodos de Leitura ---

    public List<Pedido> listarTodos() {
        return pedidos;
    }

    public Pedido buscarPorId(int id) {
        for (Pedido p : pedidos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
}