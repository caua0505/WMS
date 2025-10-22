/*
 * Controller de Pedido (CORRIGIDO).
 * Recebe AMBOS os repositórios via Injeção de Dependência.
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
     * Este construtor já estava correto.
     * Recebe o PedidoRepo (para salvar pedidos) e o
     * ProdutoRepo (para buscar produtos e verificar estoque).
     */
    public PedidoController(PedidoRepository pedidoRepository, ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
    }

    // Criar pedido com lista de IDs de produtos
    public void criarPedido(List<Integer> idProdutos) {
        Pedido pedido = new Pedido(); // Cria um pedido novo

        for (int id : idProdutos) {
            Produto produto = produtoRepository.buscarPorId(id);
            if (produto != null) {
                // NOVO: Verifica se há estoque
                if(produto.getQuantidade() > 0) {
                    produto.removerQuantidade(1); // Remove 1 do estoque
                    pedido.adicionarProduto(produto); // Adiciona ao pedido

                    // NOVO: Salva a mudança de estoque no "produtos.txt"
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

    // Listar todos pedidos
    public List<Pedido> listarPedidos() {
        return pedidoRepository.listarTodos();
    }

    // Atualizar status do pedido
    public void atualizarStatus(int idPedido, String status) {
        Pedido pedido = pedidoRepository.buscarPorId(idPedido);
        if (pedido != null) {
            pedido.setStatus(status);
            pedidoRepository.atualizar(pedido); // Salva o novo status
        }
    }

    // Remover pedido
    public void removerPedido(int id) {
        pedidoRepository.remover(id);
    }
}