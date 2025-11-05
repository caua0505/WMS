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
    private ProdutoView produtoView;
    private Scanner scanner;

    // CONSTRUTOR //
    public PedidoView(PedidoController pedidoController, ProdutoView produtoView, Scanner scanner) {
        this.pedidoController = pedidoController;
        this.produtoView = produtoView;
        this.scanner = scanner;
    }

    // MÉTODO DE EXIBIÇÃO //
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
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1 -> criarPedido();
                    case 2 -> listarPedidos();
                    case 3 -> atualizarStatus();
                    case 4 -> removerPedido();
                    case 0 -> { /* volta ao menu principal */ }
                    default -> System.out.println("⚠️ Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida. Por favor, insira um número.");
            }
        }
    }

    // MÉTODO CRIAR PEDIDO //
    private void criarPedido() {
        System.out.println("\n--- Criar Novo Pedido ---");
        System.out.println("Produtos disponíveis:");

        produtoView.listarProdutos();

        List<Integer> idsProdutos = new ArrayList<>();

        while (true) {
            System.out.print("Digite o ID de um produto para adicionar (ou 0 para finalizar): ");
            String entrada = scanner.nextLine();

            try {
                int idProduto = Integer.parseInt(entrada);

                if (idProduto == 0) {
                    break;
                }

                idsProdutos.add(idProduto);
                System.out.println("✅ Produto ID " + idProduto + " adicionado. Digite outro ID ou 0 para finalizar.");
            } catch (NumberFormatException e) {
                System.out.println("❌ ID inválido. Digite um número inteiro.");
            }
        }

        if (!idsProdutos.isEmpty()) {
            pedidoController.criarPedido(idsProdutos);
            System.out.println("🟢 Pedido criado com sucesso!");
        } else {
            System.out.println("⚠️ Nenhum produto adicionado. Pedido cancelado.");
        }
    }

    // MÉTODO LISTAR PEDIDOS //
    private void listarPedidos() {
        System.out.println("\n--- Lista de Pedidos ---");
        List<Pedido> pedidos = pedidoController.listarPedidos();

        if (pedidos.isEmpty()) {
            System.out.println("⚠️ Nenhum pedido realizado.");
        } else {
            for (Pedido pedido : pedidos) {
                System.out.println(pedido);
                System.out.println("Produtos no Pedido:");
                if (pedido.getProdutos().isEmpty()) {
                    System.out.println("  ⚠️ Nenhum produto listado.");
                } else {
                    for (Produto produto : pedido.getProdutos()) {
                        System.out.println("   - ID: " + produto.getId() + ", Nome: " + produto.getNome());
                    }
                }
                System.out.println("-------------------------");
            }
        }
    }

    // MÉTODO ATUALIZAR STATUS //
    private void atualizarStatus() {
        System.out.println("\n--- Atualizar Status do Pedido ---");
        try {
            System.out.print("ID do pedido: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Novo status (Ex: Enviado, Entregue, Cancelado): ");
            String status = scanner.nextLine();

            pedidoController.atualizarStatus(id, status);
            System.out.println("✅ Status atualizado com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println("❌ ID inválido. Digite um número inteiro.");
        }
    }

    // MÉTODO REMOVER PEDIDO //
    private void removerPedido() {
        System.out.println("\n--- Remover Pedido ---");
        try {
            System.out.print("ID do pedido a ser removido: ");
            int id = Integer.parseInt(scanner.nextLine());

            pedidoController.removerPedido(id);
            System.out.println("🗑️ Pedido removido com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println("❌ ID inválido. Digite um número inteiro.");
        }
    }
}
