/*
 * Classe principal que inicializa todas as dependências do sistema.
 */

import View.*;
import Controller.*;
import Repository.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Iniciando sistema... Carregando dados do TXT...");

        ProdutoRepository produtoRepo = new ProdutoRepository();
        ClienteRepository clienteRepo = new ClienteRepository();
        FornecedorRepository fornecedorRepo = new FornecedorRepository();
        PedidoRepository pedidoRepo = new PedidoRepository(produtoRepo);

        System.out.println("Dados carregados. Iniciando serviços...");

        ProdutoController produtoController = new ProdutoController(produtoRepo);
        ClienteController clienteController = new ClienteController(clienteRepo);
        FornecedorController fornecedorController = new FornecedorController(fornecedorRepo);
        PedidoController pedidoController = new PedidoController(pedidoRepo, produtoRepo);

        System.out.println("Serviços iniciados. Iniciando interface...");

        ProdutoView produtoView = new ProdutoView(produtoController, scanner);
        ClienteView clienteView = new ClienteView(clienteController, scanner);
        FornecedorView fornecedorView = new FornecedorView(fornecedorController, scanner);
        PedidoView pedidoView = new PedidoView(pedidoController, produtoView, scanner);

        System.out.println("Sistema pronto!");

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
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1 -> produtoView.exibirMenu();
                    case 2 -> clienteView.exibirMenu();
                    case 3 -> fornecedorView.exibirMenu();
                    case 4 -> pedidoView.exibirMenu();
                    case 0 -> System.out.println("Encerrando o sistema...");
                    default -> System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida. Por favor, insira um número.");
            }
        }

        scanner.close();
    }
}
