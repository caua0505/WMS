/*
 * View de Cliente.
 * Recebe o Controller e o Scanner via Injeção de Dependência.
 */
package View;

import Controller.ClienteController;
import Model.Cliente;
import java.util.List;
import java.util.Scanner;

public class ClienteView {

    private ClienteController clienteController;
    private Scanner scanner;

    /**
     * Construtor de Injeção de Dependência.
     */
    public ClienteView(ClienteController clienteController, Scanner scanner) {
        this.clienteController = clienteController;
        this.scanner = scanner;
    }

    public void exibirMenu() {
        int opcao = -1;
        while(opcao != 0) {
            System.out.println("\n---===[ Gestão de Clientes ]===---");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Atualizar Cliente");
            System.out.println("4. Remover Cliente");
            System.out.println("5. Buscar Cliente por Nome");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                // Usa o scanner compartilhado
                opcao = Integer.parseInt(scanner.nextLine());

                switch(opcao) {
                    case 1: cadastrarCliente(); break;
                    case 2: listarClientes(); break;
                    case 3: atualizarCliente(); break;
                    case 4: removerCliente(); break;
                    case 5: buscarCliente(); break;
                    case 0: break;
                    default: System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Entrada inválida. Por favor, insira um número.");
            }
        }
    }

    // --- Métodos privados que chamam o Controller ---

    private void cadastrarCliente() {
        System.out.println("\n--- Cadastro de Cliente ---");
        System.out.print("ID do cliente: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Nome do cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Endereço do cliente: ");
        String endereco = scanner.nextLine();
        Cliente cliente = new Cliente(id, endereco, nome);
        // Chama o controller
        clienteController.cadastroCliente(cliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private void listarClientes() {
        System.out.println("\n--- Lista de Clientes ---");
        List<Cliente> clientes = clienteController.listarCliente();
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            clientes.forEach(System.out::println);
        }
    }

    private void atualizarCliente() {
        System.out.println("\n--- Atualizar Cliente ---");
        System.out.print("ID do cliente a ser atualizado: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();
        System.out.print("Novo endereço: ");
        String endereco = scanner.nextLine();
        Cliente cliente = new Cliente(id, endereco, nome);
        // Chama o controller
        clienteController.atualizarCliente(id, cliente);
        System.out.println("Cliente atualizado com sucesso!");
    }

    private void removerCliente() {
        System.out.println("\n--- Remover Cliente ---");
        System.out.print("ID do cliente a ser removido: ");
        int id = Integer.parseInt(scanner.nextLine());
        // Chama o controller
        clienteController.removerCliente(id);
        System.out.println("Cliente removido com sucesso!");
    }

    private void buscarCliente() {
        System.out.println("\n--- Buscar Cliente por Nome ---");
        System.out.print("Nome do cliente a buscar: ");
        String nome = scanner.nextLine();
        // Chama o controller
        Cliente cliente = clienteController.buscarCliente(nome);
        if (cliente != null) {
            System.out.println("Cliente encontrado: " + cliente);
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }
}