package Controller;

import Model.Pedido;
import Model.Produto;
import Repository.PedidoRepository;
import Repository.ProdutoRepository;
import java.util.List;

public class PedidoController {

    private PedidoRepository pedidoRepository;
    private ProdutoRepository produtoRepository;

    public PedidoController(PedidoRepository pedidoRepository, ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
    }

    // Criar pedido com lista de IDs de produtos
    public void criarPedido(List<Integer> idProdutos) {
        Pedido pedido = new Pedido();

        for (int id : idProdutos) {
            Produto produto = produtoRepository.buscarPorId(id);
            if (produto != null) {
                pedido.adicionarProduto(produto);
            }
        }

        // Salvar pedido no repositório
        pedidoRepository.adicionar(pedido);
    }

    // Listar todos pedidos
    public List<Pedido> listarPedidos() {
        return pedidoRepository.listarTodos();
    }

    // Atualizar status do pedido
    public void atualizarStatus(int idPedido, String status) {
        Pedido pedido = pedidoRepository.buscarPorId(idPedido);
        if (pedido != null) {
            pedido.setStatus(status);
            pedidoRepository.atualizar(pedido);
        }
    }

    // Remover pedido
    public void removerPedido(int id) {
        pedidoRepository.remover(id);
    }
}
