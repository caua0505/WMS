/*
 * View de Fornecedor (CORRIGIDA).
 * Recebe o Controller e o Scanner via Injeção de Dependência.
 */
package View;

import Controller.FornecedorController;
import Model.Fornecedor;
import java.util.List;
import java.util.Scanner;

public class FornecedorView {

    // MUDANÇA: Apenas declara o controller
    private FornecedorController fornecedorController;

    // MUDANÇA: Apenas declara o scanner
    private Scanner scanner;

    /**
     * MUDANÇA: Construtor de Injeção de Dependência.
     * Recebe o controller e o scanner único da Main.
     */
    public FornecedorView(FornecedorController fornecedorController, Scanner scanner) {
        this.fornecedorController = fornecedorController;
        this.scanner = scanner;
    }

    // O RESTO DA CLASSE NÃO MUDA

    public void exibirMenu() {
        int opcao = -1;
        while(opcao != 0) {
            System.out.println("\n---===[ Gestão de Fornecedores ]===---");
            System.out.println("1. Cadastrar Fornecedor");
            System.out.println("2. Listar Fornecedores");
            System.out.println("3. Atualizar Fornecedor");
            System.out.println("4. Remover Fornecedor");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                // Usa o scanner compartilhado
                opcao = Integer.parseInt(scanner.nextLine());

                switch(opcao) {
                    case 1: cadastrarFornecedor(); break;
                    case 2: listarFornecedores(); break;
                    case 3: atualizarFornecedor(); break;
                    case 4: removerFornecedor(); break;
                    case 0: break;
                    default: System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Entrada inválida. Por favor, insira um número.");
            }
        }
    }

    private void cadastrarFornecedor() {
        System.out.println("\n--- Cadastro de Fornecedor ---");
        System.out.print("ID do fornecedor: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Nome do fornecedor: ");
        String nome = scanner.nextLine();
        System.out.print("CNPJ do fornecedor: ");
        String cnpj = scanner.nextLine();
        Fornecedor fornecedor = new Fornecedor(id, cnpj, nome);
        fornecedorController.cadastrarFornecedor(fornecedor);
        System.out.println("Fornecedor cadastrado com sucesso!");
    }

    private void listarFornecedores() {
        System.out.println("\n--- Lista de Fornecedores ---");
        List<Fornecedor> fornecedores = fornecedorController.listarFornecedores();
        if (fornecedores.isEmpty()) {
            System.out.println("Nenhum fornecedor cadastrado.");
        } else {
            fornecedores.forEach(System.out::println);
        }
    }

    private void atualizarFornecedor() {
        System.out.println("\n--- Atualizar Fornecedor ---");
        System.out.print("ID do fornecedor a ser atualizado: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();
        System.out.print("Novo CNPJ: ");
        String cnpj = scanner.nextLine();
        Fornecedor fornecedor = new Fornecedor(id, cnpj, nome);
        fornecedorController.atualizarFornecedor(id, fornecedor);
        System.out.println("Fornecedor atualizado com sucesso!");
    }

    private void removerFornecedor() {
        System.out.println("\n--- Remover Fornecedor ---");
        System.out.print("ID do fornecedor a ser removido: ");
        int id = Integer.parseInt(scanner.nextLine());
        fornecedorController.removerFornecedor(id);
        System.out.println("Fornecedor removido com sucesso!");
    }
}