/*
 * Repositório de Pedidos (CORRIGIDO).
 * Responsável por gerenciar a lista de pedidos.
 */
package Repository;

import Model.Pedido;
import Model.GerenciadorArquivo; // Importa o gerenciador
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PedidoRepository {

    // MUDANÇA: A lista não é mais 'static'
    private List<Pedido> pedidos;

    // NOVO: Instância do gerenciador
    private GerenciadorArquivo gerenciador;

    // Gerador de ID único
    private static AtomicInteger proximoId;

    /**
     * MUDANÇA IMPORTANTE: Construtor do PedidoRepository
     * Agora ele RECEBE o ProdutoRepository (Injeção de Dependência).
     * @param produtoRepository A instância já carregada do repo de produtos.
     */
    public PedidoRepository(ProdutoRepository produtoRepository) {
        // Aponta para o GerenciadorArquivo no pacote Model
        this.gerenciador = new Model.GerenciadorArquivo();

        // MUDANÇA: Passamos o 'produtoRepository' para o 'carregarPedidos'
        // para que ele possa reconectar os produtos aos pedidos.
        this.pedidos = gerenciador.carregarPedidos(produtoRepository);

        // Lógica para encontrar o maior ID salvo
        int maxId = 0;
        for(Pedido p : pedidos) {
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
        pedido.setId(proximoId.getAndIncrement());
        pedidos.add(pedido);
        // NOVO: Salva a lista atualizada no arquivo
        gerenciador.salvarPedidos(pedidos);
    }

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

    /**
     * Atualiza um pedido na lista e salva no arquivo.
     */
    public void atualizar(Pedido pedidoAtualizado) {
        Pedido pedidoExistente = buscarPorId(pedidoAtualizado.getId());
        if (pedidoExistente != null) {
            int index = pedidos.indexOf(pedidoExistente);
            pedidos.set(index, pedidoAtualizado);
            // NOVO: Salva a lista atualizada no arquivo
            gerenciador.salvarPedidos(pedidos);
        }
    }

    /**
     * Remove um pedido da lista e salva no arquivo.
     */
    public void remover(int id) {
        if(pedidos.removeIf(pedido -> pedido.getId() == id)) {
            // NOVO: Salva a lista atualizada no arquivo
            gerenciador.salvarPedidos(pedidos);
        }
    }
}