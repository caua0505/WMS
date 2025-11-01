/*
 * Autor: Cauã e Natan
 * Descrição: Responsável por exibir o menu de clientes, capturar
 * os dados via console e se comunicar com o Controller.
 */

package View;

import Controller.ClienteController;
import Model.Cliente;
import java.util.List;
import java.util.Scanner;

public class ClienteView {

    private ClienteController clienteController;
    private Scanner scanner;

    // CONSTRUTOR //
    public ClienteView(ClienteController clienteController, Scanner scanner) {
        this.clienteController = clienteController;
        this.scanner = scanner;
    }

    // METODO DE EXIBIÇÃO //
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
                    case 1 -> cadastrarCliente();   // Chama o método para cadastrar um novo cliente
                    case 2 -> listarClientes();     // Lista todos os clientes cadastrados
                    case 3 -> atualizarCliente();   // Atualiza os dados de um cliente existente
                    case 4 -> removerCliente();     // Remove um cliente do sistema
                    case 5 -> buscarCliente();      // Busca um cliente pelo nome
                    case 0 -> System.out.println("Retornando ao menu principal...");
                    default -> System.out.println("⚠️ Opção inválida. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida. Digite apenas números.");
            }
        }
    }

    // METODO CADASTRAR CLIENTE //
    private void cadastrarCliente() {
        System.out.println("\n--- Cadastro de Cliente ---");

        // Solicita o nome do cliente //
        System.out.print("Nome do cliente: ");
        String nome = scanner.nextLine();

        // Solicita e valida o CPF (precisa conter 11 dígitos numéricos) //
        String cpf;
        while (true) {
            System.out.print("⚠️ CPF do cliente (11 dígitos): ");
            cpf = scanner.nextLine().replaceAll("\\D", "").trim(); // Remove caracteres não numéricos
            if (cpf.length() == 11) break;
            System.out.println("❌ CPF inválido! Deve conter 11 dígitos numéricos.");
        }

        // Solicita o endereço do cliente //
        System.out.print("Endereço do cliente: ");
        String endereco = scanner.nextLine();

        // Solicita o telefone do cliente //
        System.out.print("Telefone do cliente: ");
        String telefone = scanner.nextLine();

        // Criação do cliente com ID automático e envio ao Controller //
        Cliente cliente = new Cliente(cpf, endereco, nome, telefone);
        clienteController.cadastroCliente(cliente);

        // Exibe mensagem de sucesso e o código gerado //
        System.out.println("✅ Cliente cadastrado com sucesso!");
        System.out.println("Código do cliente: " + cliente.getId());
    }

    // METODO LISTAR CLIENTES //
    private void listarClientes() {
        System.out.println("\n--- Lista de Clientes ---");
        List<Cliente> clientes = clienteController.listarCliente();

        if (clientes.isEmpty()) {
            System.out.println("❌ Nenhum cliente cadastrado.");
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

    // METODO ATUALIZAR CLIENTES //
    private void atualizarCliente() {
        System.out.println("\n--- Atualizar Cliente ---");
        System.out.print("ID do cliente a ser atualizado: ");
        int id = Integer.parseInt(scanner.nextLine());

        // Solicita os novos dados do cliente //
        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();
        System.out.print("Novo CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Novo endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Novo telefone: ");
        String telefone = scanner.nextLine();

        // Cria o novo objeto Cliente e envia para o Controller atualizar //
        Cliente clienteAtualizado = new Cliente(nome, cpf, endereco, telefone);
        clienteController.atualizarCliente(String.valueOf(id), clienteAtualizado);

        // Exibe mensagem de confirmação //
        System.out.println("🔄 Cliente atualizado com sucesso!");
        System.out.println("🔄 Novo código do cliente: " + clienteAtualizado.getId());
    }

    // METODO REMOVER CLIENTES //
    private void removerCliente() {
        System.out.println("\n--- Remover Cliente ---");
        System.out.print("ID do cliente a ser removido: ");
        int id = Integer.parseInt(scanner.nextLine());

        // Chama o método de remoção no Controller
        clienteController.removerCliente(String.valueOf(id));
        System.out.println("🗑️ Cliente removido com sucesso!");
    }

    // METODO BUSCAR CLIENTES //
    private void buscarCliente() {
        System.out.println("\n--- Buscar Cliente por Nome ---");
        System.out.print("Nome do cliente a buscar: ");
        String nome = scanner.nextLine();

        // Realiza a busca no Controller //
        Cliente cliente = clienteController.buscarCliente(nome);

        // Verifica se encontrou e exibe os resultados //
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
