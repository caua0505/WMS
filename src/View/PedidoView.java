/*
 * Autor: Natan e Cauã
 * View de Pedido.
 * 1. PedidoController (para criar/listar pedidos)
 * 2. ProdutoView (para mostrar a lista de produtos disponíveis)
 * 3. Scanner (para ler a entrada)
 */

package View;

import Controller.PedidoController;
import Model.Pedido;
import Model.Produto;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PedidoView {

    private PedidoController pedidoController;
    private ProdutoView produtoView; // Referência para listar produtos
    private Scanner scanner;

    // CONSTRUTOR //
    public PedidoView(PedidoController pedidoController, ProdutoView produtoView, Scanner scanner) {
        this.pedidoController = pedidoController;
        this.produtoView = produtoView;
        this.scanner = scanner;
    }

    // METODO DE EXIBIÇÃO //
    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n---===[ Gestão de Pedidos ]===---");
            System.out.println("1. Criar Pedido");
            System.out.println("2. Listar Pedidos");
            System.out.println("3. Atualizar Status do Pedido");
            System.out.println("4. Remover Pedido");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                // Usa o scanner compartilhado //
                opcao = Integer.parseInt(scanner.nextLine());

                switch(opcao) {
                    case 1: criarPedido(); break;
                    case 2: listarPedidos(); break;
                    case 3: atualizarStatus(); break;
                    case 4: removerPedido(); break;
                    case 0: break;
                    default: System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println(" ❌ Erro: Entrada inválida. Por favor, insira um número.");
            }
        }
    }

    // METODO CRIAR PEDIDO //
    private void criarPedido() {
        System.out.println("\n--- Criar Novo Pedido ---");
        System.out.println("Produtos disponíveis:");

        produtoView.listarProdutos();

        List<Integer> idsProdutos = new ArrayList<>();
        int idProduto;

        while (true) {
            System.out.print("Digite o ID de um produto para adicionar (ou 0 para finalizar): ");
            try {
                idProduto = Integer.parseInt(scanner.nextLine());
                if (idProduto == 0) {
                    break;
                }
                idsProdutos.add(idProduto);
                System.out.println("Produto ID " + idProduto + " adicionado. Digite outro ID ou 0.");
            } catch (NumberFormatException e) {
                System.out.println("❌ ID inválido.");
            }
        }

        if (!idsProdutos.isEmpty()) {
            // Envia a lista de IDs para o controller //
            pedidoController.criarPedido(idsProdutos);
            System.out.println("Pedido criado com sucesso!");
        } else {
            System.out.println("❌ Nenhum produto adicionado. Pedido cancelado.");
        }
    }

    // METODO LISTAR PEDIDO //
    private void listarPedidos() {
        System.out.println("\n--- Lista de Pedidos ---");
        List<Pedido> pedidos = pedidoController.listarPedidos();
        if (pedidos.isEmpty()) {
            System.out.println("⚠️ Nenhum pedido realizado.");
        } else {
            // Loop para mostrar detalhes do pedido e seus produtos //
            for (Pedido pedido : pedidos) {
                // Usa o toString() modificado do Pedido (com o 'numeroPedido') //
                System.out.println(pedido);
                System.out.println("Produtos no Pedido:");
                if (pedido.getProdutos().isEmpty()) {
                    System.out.println("⚠️ Nenhum produto listado");
                } else {
                    for (Produto produto : pedido.getProdutos()) {
                        System.out.println("    - ID: " + produto.getId() + ", Nome: " + produto.getNome());
                    }
                }
                System.out.println("-------------------------");
            }
        }
    }

    // METODO ATUALIZAR PEDIDO //
    private void atualizarStatus() {
        System.out.println("\n--- Atualizar Status do Pedido ---");
        System.out.print("ID do pedido para atualizar o status: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Novo status (Ex: Enviado, Entregue, Cancelado): ");
        String status = scanner.nextLine();
        // Chama o controller //
        pedidoController.atualizarStatus(id, status);
        System.out.println("✅ Status atualizado com sucesso!");
    }

    private void removerPedido() {
        System.out.println("\n--- Remover Pedido ---");
        System.out.print("ID do pedido a ser removido: ");
        int id = Integer.parseInt(scanner.nextLine());
        // Chama o controller //
        pedidoController.removerPedido(id);
        System.out.println("🗑️ Pedido removido com sucesso!");
    }
}