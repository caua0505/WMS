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

    // Construtor (injeção de dependência)
    public ClienteView(ClienteController clienteController, Scanner scanner) {
        this.clienteController = clienteController;
        this.scanner = scanner;
    }

    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n---===[ Gestão de Clientes ]===---");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Atualizar Cliente");
            System.out.println("4. Remover Cliente");
            System.out.println("5. Buscar Cliente por Nome");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1 -> cadastrarCliente();
                    case 2 -> listarClientes();
                    case 3 -> atualizarCliente();
                    case 4 -> removerCliente();
                    case 5 -> buscarCliente();
                    case 0 -> System.out.println("Retornando ao menu principal...");
                    default -> System.out.println("⚠️ Opção inválida. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida. Digite apenas números.");
            }
        }
    }

    //  Métodos privados (operações) //

    private void cadastrarCliente() {
        System.out.println("\n--- Cadastro de Cliente ---");

        // Nome
        System.out.print("Nome do cliente: ");
        String nome = scanner.nextLine();

        // CPF com validação
        String cpf;
        while (true) {
            System.out.print("CPF do cliente (11 dígitos): ");
            cpf = scanner.nextLine().replaceAll("\\D", "").trim(); // remove tudo que não for número
            if (cpf.length() == 11) break;
            System.out.println("❌ CPF inválido! Deve conter 11 dígitos numéricos.");
        }

        // Endereço
        System.out.print("Endereço do cliente: ");
        String endereco = scanner.nextLine();

        // Telefone
        System.out.print("Telefone do cliente: ");
        String telefone = scanner.nextLine();

        // Criação do cliente com ID automático
        Cliente cliente = new Cliente(cpf, endereco, nome, telefone);
        clienteController.cadastroCliente(cliente);

        System.out.println("✅ Cliente cadastrado com sucesso!");
        System.out.println("Código do cliente: " + cliente.getId());
    }


    private void listarClientes() {
        System.out.println("\n--- Lista de Clientes ---");
        List<Cliente> clientes = clienteController.listarCliente();
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            for (Cliente c : clientes) {
                System.out.println("Código: " + c.getId() +
                        " | ID: " + c.getId() +
                        " | Nome: " + c.getNome() +
                        " | CPF: " + c.getCpf() +
                        " | Endereço: " + c.getEndereco() +
                        " | Telefone: " + c.getTelefone());
            }
        }
    }

    private void atualizarCliente() {
        System.out.println("\n--- Atualizar Cliente ---");
        System.out.print("ID do cliente a ser atualizado: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();
        System.out.print("Novo CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Novo endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Novo telefone: ");
        String telefone = scanner.nextLine();

        Cliente clienteAtualizado = new Cliente(nome, cpf, endereco, telefone);
        clienteController.atualizarCliente(String.valueOf(id), clienteAtualizado);
        System.out.println("🔄 Cliente atualizado com sucesso!");
        System.out.println("Novo código do cliente: " + clienteAtualizado.getId());
    }

    private void removerCliente() {
        System.out.println("\n--- Remover Cliente ---");
        System.out.print("ID do cliente a ser removido: ");
        int id = Integer.parseInt(scanner.nextLine());
        clienteController.removerCliente(String.valueOf(id));
        System.out.println("🗑️ Cliente removido com sucesso!");
    }

    private void buscarCliente() {
        System.out.println("\n--- Buscar Cliente por Nome ---");
        System.out.print("Nome do cliente a buscar: ");
        String nome = scanner.nextLine();

        Cliente cliente = clienteController.buscarCliente(nome);
        if (cliente != null) {
            System.out.println("✅ Cliente encontrado:");
            System.out.println("Código: " + cliente.getId());
            System.out.println("ID: " + cliente.getId());
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("CPF: " + cliente.getCpf());
            System.out.println("Endereço: " + cliente.getEndereco());
            System.out.println("Telefone: " + cliente.getTelefone());
        } else {
            System.out.println("❌ Cliente não encontrado.");
        }
    }
}
