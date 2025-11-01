/*
     * Autor: Natan e Cauã
     * View de Fornecedor.
     * Recebe o Controller e o Scanner via Injeção de Dependência.
 */
package View;

import Controller.FornecedorController;
import Model.Fornecedor;
import java.util.List;
import java.util.Scanner;

public class FornecedorView {

    private final FornecedorController fornecedorController;
    private final Scanner scanner;

    // CONSTRUTOR //
    public FornecedorView(FornecedorController fornecedorController, Scanner scanner) {
        this.fornecedorController = fornecedorController;
        this.scanner = scanner;
    }
    // METODO DE EXIBIÇÃO //
    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n---===[ Gestão de Fornecedores ]===---");
            System.out.println("1. Cadastrar Fornecedor");
            System.out.println("2. Listar Fornecedores");
            System.out.println("3. Atualizar Fornecedor");
            System.out.println("4. Remover Fornecedor");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1 -> cadastrarFornecedor();
                    case 2 -> listarFornecedores();
                    case 3 -> atualizarFornecedor();
                    case 4 -> removerFornecedor();
                    case 0 -> System.out.println("Voltando ao menu principal...");
                    default -> System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Entrada inválida. Por favor, insira um número.");
            }
        }
    }

    // METODO CADASTRAR FORNECEDOR //
    private void cadastrarFornecedor() {
        System.out.println("\n--- Cadastro de Fornecedor ---");
        System.out.print("Nome do fornecedor: ");
        String nome = scanner.nextLine();
        System.out.print("CNPJ do fornecedor: ");
        String cnpj = scanner.nextLine();

        Fornecedor fornecedor = new Fornecedor(cnpj, nome);

        fornecedorController.cadastrarFornecedor(fornecedor);
        System.out.println("✅ Fornecedor cadastrado com sucesso!");
    }

    // METODO LISTAR FORNECEDOR //
    private void listarFornecedores() {
        System.out.println("\n--- Lista de Fornecedores ---");
        List<Fornecedor> fornecedores = fornecedorController.listarFornecedores();

        if (fornecedores.isEmpty()) {
            System.out.println("Nenhum fornecedor cadastrado.");
        } else {
            fornecedores.forEach(System.out::println);
        }
    }
   // METODO ATUALIZAR FORNECEDOR //
    private void atualizarFornecedor() {
        System.out.println("\n--- Atualizar Fornecedor ---");
        System.out.print("ID do fornecedor a ser atualizado: ");
        String id = scanner.nextLine(); // ID é String //
        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();
        System.out.print("Novo CNPJ: ");
        String cnpj = scanner.nextLine();

        Fornecedor fornecedorAtualizado = new Fornecedor(cnpj, nome);
        fornecedorController.atualizarFornecedor(id, fornecedorAtualizado);

        System.out.println("✅ Fornecedor atualizado com sucesso!");
    }
    // METODO REMOVER FORNECEDOR //
    private void removerFornecedor() {
        System.out.println("\n--- Remover Fornecedor ---");
        System.out.print("ID do fornecedor a ser removido: ");
        String id = scanner.nextLine(); // ID é String //

        fornecedorController.removerFornecedor(id);
        System.out.println("🗑️ Fornecedor removido com sucesso!");
    }
}
