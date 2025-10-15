package View;

import Controller.ProdutoController;
import Model.Produto;
import java.util.List;
import java.util.Scanner;

public class ProdutoView {
    private ProdutoController produtoController = new ProdutoController();
    private Scanner scanner = new Scanner(System.in);

    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n---===[ Gestão de Produtos ]===---");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Listar Todos os Produtos");
            System.out.println("3. Atualizar Produto");
            System.out.println("4. Remover Produto");
            System.out.println("------------------------------------");
            System.out.println("5. Definir Posição/Local de Produto");
            System.out.println("6. Pesquisar Produto por Código");
            System.out.println("------------------------------------");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1: cadastrarProduto(); break;
                    case 2: listarProdutos(); break;
                    case 3: atualizarProduto(); break;
                    case 4: removerProduto(); break;
                    case 5: definirLocalizacao(); break;
                    case 6: pesquisarPorCodigo(); break;
                    case 0: break;
                    default: System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Por favor, insira um número válido.");
            }
        }
    }

    // =================================================================
    // A CORREÇÃO ESTÁ AQUI: O MÉTODO AGORA É 'public'
    // =================================================================
    public void listarProdutos() {
        System.out.println("\n--- Lista de Produtos ---");
        List<Produto> produtos = produtoController.listarProdutos();
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            produtos.forEach(System.out::println);
        }
    }

    private void cadastrarProduto() {
        System.out.println("\n--- Cadastro de Produto ---");
        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();
        System.out.print("Quantidade inicial: ");
        int quantidade = Integer.parseInt(scanner.nextLine());
        produtoController.cadastrarProduto(nome, quantidade);
        System.out.println("Produto cadastrado com sucesso!");
    }

    private void atualizarProduto() {
        System.out.print("ID do produto a ser atualizado: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();
        System.out.print("Nova quantidade total: ");
        int quantidade = Integer.parseInt(scanner.nextLine());
        produtoController.atualizarProduto(id, nome, quantidade);
        System.out.println("Produto atualizado!");
    }

    private void removerProduto() {
        System.out.print("ID do produto a ser removido: ");
        int id = Integer.parseInt(scanner.nextLine());
        produtoController.removerProduto(id);
        System.out.println("Produto removido!");
    }

    private void definirLocalizacao() {
        System.out.println("\n--- Definir Localização de Produto ---");
        System.out.print("Digite o ID do produto: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Digite a Posição (ex: 1 para Corredor 1): ");
        int posicao = Integer.parseInt(scanner.nextLine());
        System.out.print("Digite o Local (ex: 3 para Prateleira 3): ");
        int local = Integer.parseInt(scanner.nextLine());

        boolean sucesso = produtoController.definirLocalizacao(id, posicao, local);

        if (sucesso) {
            System.out.println("Localização definida com sucesso!");
        } else {
            System.out.println("ERRO: Produto com ID " + id + " não encontrado.");
        }
    }

    private void pesquisarPorCodigo() {
        System.out.println("\n--- Pesquisar Produto por Código ---");
        System.out.print("Digite o Código do produto (ex: 123432): ");
        String codigo = scanner.nextLine();

        Produto produtoEncontrado = produtoController.buscarProdutoPorCodigo(codigo);

        if (produtoEncontrado != null) {
            System.out.println("Produto encontrado:");
            System.out.println(produtoEncontrado);
        } else {
            System.out.println("ERRO: Produto com código '" + codigo + "' não encontrado.");
        }
    }
}