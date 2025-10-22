/*
 * View de Produto (CORRIGIDA).
 * Recebe o Controller e o Scanner via Injeção de Dependência.
 */
package View;

import Controller.ProdutoController;
import Model.Produto;
import java.util.List;
import java.util.Scanner;

public class ProdutoView {

    // MUDANÇA: Apenas declara o controller
    // Autor : Cauã Tobias , Natan Lima

    private ProdutoController produtoController;

    // MUDANÇA: Apenas declara o scanner
    // Autor : Cauã Tobias , Natan Lima

    private Scanner scanner;

    /**
     * MUDANÇA: Construtor de Injeção de Dependência.
     * Recebe o controller e o scanner único da Main.
     */
    // Autor : Cauã Tobias , Natan Lima

    public ProdutoView(ProdutoController produtoController, Scanner scanner) {
        this.produtoController = produtoController;
        this.scanner = scanner;
    }

    //Menu de exibição do Sistema WMS
    // Autor : Cauã Tobias , Natan Lima

    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            // Este menu estava travando antes da correção do Scanner
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
                // Usa o scanner compartilhado
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

    // O RESTO DA CLASSE NÃO MUDA
    // Listar Produto
    // Autor : Cauã Tobias , Natan Lima

    public void listarProdutos() {
        System.out.println("\n--- Lista de Produtos ---");
        List<Produto> produtos = produtoController.listarProdutos();
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            produtos.forEach(System.out::println);
        }
    }
     // Produto cadastro
     // Autor : Cauã Tobias , Natan Lima
    private void cadastrarProduto() {
        System.out.println("\n--- Cadastro de Produto ---");
        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();
        System.out.print("Quantidade inicial: ");
        int quantidade = Integer.parseInt(scanner.nextLine());

        //Retorno do Produto cadastrado
        // Autor : Cauã Tobias , Natan Lima

        Produto produto = produtoController.cadastrarProduto(nome, quantidade);
        System.out.println("Código do Produto: " + produto.getCodigo());
        System.out.println("Nome do Produto: " + produto.getNome());
        System.out.println("Produto cadastrado com sucesso!");
    }
      // Produto Atualizado
      // Autor : Cauã Tobias , Natan Lima

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

    // Remover Produto
    // Autor : Cauã Tobias , Natan Lima

    private void removerProduto() {
        System.out.print("ID do produto a ser removido: ");
        int id = Integer.parseInt(scanner.nextLine());
        produtoController.removerProduto(id);
        System.out.println("Produto removido!");
    }

    // Definir localização no Estoque
    // Autor : Cauã Tobias , Natan Lima

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

    //Pesquisa de Produto por codigo
    // Autor : Cauã Tobias , Natan Lima

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