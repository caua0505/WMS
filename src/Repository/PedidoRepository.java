package Repository;

import Model.Pedido;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PedidoRepository {
    private static List<Pedido> pedidos = new ArrayList<>();
    private static AtomicInteger proximoId = new AtomicInteger(1);

    /**
     * Adiciona um novo pedido à lista, atribuindo um ID único.
     * Este é o método que estava faltando.
     */
    public void adicionar(Pedido pedido) {
        pedido.setId(proximoId.getAndIncrement()); // Define um ID único e sequencial.
        pedidos.add(pedido);
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

    public void atualizar(Pedido pedidoAtualizado) {
        Pedido pedidoExistente = buscarPorId(pedidoAtualizado.getId());
        if (pedidoExistente != null) {
            int index = pedidos.indexOf(pedidoExistente);
            pedidos.set(index, pedidoAtualizado);
        }
    }

    public void remover(int id) {
        pedidos.removeIf(pedido -> pedido.getId() == id);
    }
}