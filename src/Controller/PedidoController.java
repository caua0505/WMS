/*
 * Controller de Pedido.
 * "COMMIT": Este controller é mais complexo, pois recebe DOIS
 * repositórios:
 * 1. PedidoRepository (para salvar o pedido)
 * 2. ProdutoRepository (para checar estoque e buscar produtos)
 */
package Controller;

import Model.Pedido;
import Model.Produto;
import Repository.PedidoRepository;
import Repository.ProdutoRepository;
import java.util.List;

public class PedidoController {

    private PedidoRepository pedidoRepository;
    private ProdutoRepository produtoRepository;

    /**
     * Construtor de Injeção de Dependência (recebe ambos os repos).
     */
    public PedidoController(PedidoRepository pedidoRepository, ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
    }

    /**
     * Lógica de negócio para criar um pedido.
     * Recebe uma lista de IDs de produtos (ex: [1, 5, 2]).
     */
    public void criarPedido(List<Integer> idProdutos) {
        Pedido pedido = new Pedido(); // Cria um pedido novo (com UUID aleatório)

        for (int id : idProdutos) {
            Produto produto = produtoRepository.buscarPorId(id); // Busca no repo de produtos
            if (produto != null) {
                // Lógica de negócio: Verifica se há estoque
                if (produto.getQuantidade() > 0) {
                    produto.removerQuantidade(1); // Remove 1 do estoque
                    pedido.adicionarProduto(produto); // Adiciona ao pedido

                    // Salva a mudança de estoque no "produtos.txt"
                    produtoRepository.atualizar(produto);
                } else {
                    System.out.println("AVISO: Produto " + produto.getNome() + " está fora de estoque.");
                }
            } else {
                System.out.println("AVISO: Produto com ID " + id + " não encontrado.");
            }
        }

        // Só salva o pedido se ele não estiver vazio
        if (!pedido.getProdutos().isEmpty()) {
            pedidoRepository.adicionar(pedido); // Salva no "pedidos.txt"
        } else {
            System.out.println("Pedido não foi criado (vazio ou sem estoque).");
        }
    }

    // --- Métodos que delegam para o Repositório ---

    public List<Pedido> listarPedidos() {
        return pedidoRepository.listarTodos();
    }

    public void atualizarStatus(int idPedido, String status) {
        Pedido pedido = pedidoRepository.buscarPorId(idPedido);
        if (pedido != null) {
            pedido.setStatus(status);
            pedidoRepository.atualizar(pedido); // Salva o novo status
        }
    }

    public void removerPedido(int id) {
        pedidoRepository.remover(id);
    }
}