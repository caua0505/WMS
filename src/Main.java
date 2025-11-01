/*
 * Classe Principal (Main) - O "Commit Final"
 *
 * "COMMIT": Esta é a classe mais importante para a ARQUITETURA.
 * Ela é o ponto de entrada do sistema e é responsável por
 * "montar" o quebra-cabeça da Injeção de Dependência (DI).
 *
 * A ORDEM de criação aqui é CRUCIAL.
 */

// Importa todas as camadas
import View.*;
import Controller.*;
import Repository.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // PASSO 1: CRIAR O SCANNER ÚNICO
        // "COMMIT": Criamos UM Scanner e o passamos para TODAS as Views.
        // Isso corrige o bug de "menu travado" (misturar nextInt/nextLine). //
        Scanner scanner = new Scanner(System.in);

        // PASSO 2: CAMADA DE REPOSITÓRIO (Dados)
        // "COMMIT": Criamos os repositórios. Ao serem criados (no construtor),
        // eles automaticamente carregam os dados dos arquivos TXT. //
        System.out.println("Iniciando sistema... Carregando dados do TXT...");

        // O ProdutoRepository deve ser criado PRIMEIRO,
        // pois o PedidoRepository DEPENDE dele. //
        ProdutoRepository produtoRepo = new ProdutoRepository();

        // Repositórios independentes //
        ClienteRepository clienteRepo = new ClienteRepository();
        FornecedorRepository fornecedorRepo = new FornecedorRepository();

        // O PedidoRepository é criado POR ÚLTIMO, pois ele
        // recebe o 'produtoRepo' pronto em seu construtor
        // para "conectar" os produtos aos pedidos salvos no TXT.//
        PedidoRepository pedidoRepo = new PedidoRepository(produtoRepo);

        System.out.println("Dados carregados. Iniciando serviços...");

        // PASSO 3: CAMADA DE CONTROLLER (Lógica)
        // "COMMIT": Criamos os controllers e "injetamos" (passamos) os
        // repositórios que eles precisam para trabalhar.//
        ProdutoController produtoController = new ProdutoController(produtoRepo);
        ClienteController clienteController = new ClienteController(clienteRepo);
        FornecedorController fornecedorController = new FornecedorController(fornecedorRepo);
        // O PedidoController precisa de AMBOS os repositórios. //
        PedidoController pedidoController = new PedidoController(pedidoRepo, produtoRepo);

        System.out.println("Serviços iniciados. Iniciando interface...");

        // PASSO 4: CAMADA DE VIEW (Interface)
        // "COMMIT": Criamos as Views e "injetamos" os controllers
        // (para que a View possa enviar comandos) E o scanner único (para ler input).//
        ProdutoView produtoView = new ProdutoView(produtoController, scanner);
        ClienteView clienteView = new ClienteView(clienteController, scanner);
        FornecedorView fornecedorView = new FornecedorView(fornecedorController, scanner);
        // A PedidoView precisa do seu controller, da ProdutoView (para listar) e do scanner. //
        PedidoView pedidoView = new PedidoView(pedidoController, produtoView, scanner);

        System.out.println("Sistema pronto!");

        //  PASSO 5: MENU PRINCIPAL
        // O loop que roda o programa //
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
                // Usa o scanner principal //
                opcao = Integer.parseInt(scanner.nextLine());

                // Chama os métodos nas instâncias JÁ CRIADAS //
                switch (opcao) {
                    case 1: produtoView.exibirMenu(); break;
                    case 2: clienteView.exibirMenu(); break;
                    case 3: fornecedorView.exibirMenu(); break;
                    case 4: pedidoView.exibirMenu(); break;
                    case 0:
                        System.out.println("Encerrando o sistema... Dados salvos.");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Entrada inválida. Por favor, digite um número.");
            }
        }

        // PASSO 6: FECHAR O SCANNER
        // Fechamos o scanner SÓ AQUI, no final do programa. //
        scanner.close();
    }
}