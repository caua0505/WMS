import View.ClienteView;
import View.FornecedorView;
import View.PedidoView;
import View.ProdutoView;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n--===[ WMS - Sistema de Gestão de Armazém ]===--");
            System.out.println("1. Gestão de Produtos");
            System.out.println("2. Gestão de Clientes");
            System.out.println("3. Gestão de Fornecedores");
            System.out.println("4. Gestão de Pedidos");
            System.out.println("0. Sair do Sistema");
            System.out.print("Escolha um módulo para gerenciar: ");

            try {
                // Lê a linha inteira para evitar problemas com o scanner
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        // Chama a nova ProdutoView que tem os métodos de localização
                        new ProdutoView().exibirMenu();
                        break;
                    case 2:
                        new ClienteView().exibirMenu();
                        break;
                    case 3:
                        new FornecedorView().exibirMenu();
                        break;
                    case 4:
                        new PedidoView().exibirMenu();
                        break;
                    case 0:
                        System.out.println("Encerrando o sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Entrada inválida. Por favor, digite um número.");
            }
        }
        scanner.close();
    }
}