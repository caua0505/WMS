/*
 * View de Pedido (CORRIGIDA).
 * Recebe o Controller, a ProdutoView e o Scanner via Injeção de Dependência.
 */
package View;

import Controller.PedidoController;
import Model.Pedido;
import Model.Produto;
// Imports de Repository não são mais necessários aqui
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PedidoView {

    // MUDANÇA: Apenas declara
    private PedidoController pedidoController;
    private ProdutoView produtoView; // Referência para listar produtos
    private Scanner scanner;

    /**
     * MUDANÇA: Construtor de Injeção de Dependência.
     * Recebe o controller, a view de produto (para listar)
     * e o scanner único da Main.
     */
    public PedidoView(PedidoController pedidoController, ProdutoView produtoView, Scanner scanner) {
        this.pedidoController = pedidoController;
        this.produtoView = produtoView;
        this.scanner = scanner;
    }

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
                // Usa o scanner compartilhado
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
                System.out.println("Erro: Entrada inválida. Por favor, insira um número.");
            }
        }
    }

    private void criarPedido() {
        System.out.println("\n--- Criar Novo Pedido ---");
        System.out.println("Produtos disponíveis:");

        // MUDANÇA: Usa a instância de ProdutoView injetada
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
                System.out.println("ID inválido.");
            }
        }

        if (!idsProdutos.isEmpty()) {
            pedidoController.criarPedido(idsProdutos);
            System.out.println("Pedido criado com sucesso!");
        } else {
            System.out.println("Nenhum produto adicionado. Pedido cancelado.");
        }
    }

    private void listarPedidos() {
        System.out.println("\n--- Lista de Pedidos ---");
        List<Pedido> pedidos = pedidoController.listarPedidos();
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido realizado.");
        } else {
            for (Pedido pedido : pedidos) {
                System.out.println("Pedido ID: " + pedido.getId() + " | Status: " + pedido.getStatus() + " | Data: " + pedido.getData());
                System.out.println("  Produtos no Pedido:");
                if (pedido.getProdutos().isEmpty()) {
                    System.out.println("    (Nenhum produto listado - pode ter sido removido)");
                } else {
                    for (Produto produto : pedido.getProdutos()) {
                        System.out.println("    - ID: " + produto.getId() + ", Nome: " + produto.getNome());
                    }
                }
                System.out.println("-------------------------");
            }
        }
    }

    private void atualizarStatus() {
        System.out.println("\n--- Atualizar Status do Pedido ---");
        System.out.print("ID do pedido para atualizar o status: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Novo status: ");
        String status = scanner.nextLine();
        pedidoController.atualizarStatus(id, status);
        System.out.println("Status atualizado com sucesso!");
    }

    private void removerPedido() {
        System.out.println("\n--- Remover Pedido ---");
        System.out.print("ID do pedido a ser removido: ");
        int id = Integer.parseInt(scanner.nextLine());
        pedidoController.removerPedido(id);
        System.out.println("Pedido removido com sucesso!");
    }
}