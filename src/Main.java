// Autor : Cauã Tobias , Natan Lima
// Main = Arquivo principal de execução do sistema

import View.ClienteView;
import View.FornecedorView;
import View.PedidoView;
import View.ProdutoView;
import java.util.Scanner;
import Controller.*;
import Repository.*;

public class Main {
    public static void main(String[] args) {

        // --- PASSO 1: CRIAR O SCANNER ÚNICO ---
        // Criamos uma só instância do Scanner que será
        // compartilhada por todas as classes 'View'.
        // Isso corrige o bug de travamento do menu.
        // Autor : Cauã Tobias , Natan Lima

        Scanner scanner = new Scanner(System.in);

        // --- PASSO 2: CAMADA DE REPOSITÓRIO (Dados) ---
        // Criamos as instâncias dos repositórios.
        // Ao serem criados, eles automaticamente carregam os dados dos arquivos TXT.
        // Autor : Cauã Tobias , Natan Lima

        System.out.println("Iniciando sistema... Carregando dados do TXT...");

        // O ProdutoRepository deve ser criado primeiro,
        // pois o PedidoRepository depende dele.
        ProdutoRepository produtoRepo = new ProdutoRepository();

        // Repositórios independentes
        ClienteRepository clienteRepo = new ClienteRepository();
        FornecedorRepository fornecedorRepo = new FornecedorRepository();

        // O PedidoRepository recebe o 'produtoRepo' em seu construtor
        // para "conectar" os produtos aos pedidos.
        // Autor : Cauã Tobias , Natan Lima

        PedidoRepository pedidoRepo = new PedidoRepository(produtoRepo);

        System.out.println("Dados carregados. Iniciando serviços...");

        // --- PASSO 3: CAMADA DE CONTROLLER (Lógica) ---
        // Criamos os controllers e "injetamos" (passamos) os repositórios neles.
        // Autor : Cauã Tobias , Natan Lima

        ProdutoController produtoController = new ProdutoController(produtoRepo);
        ClienteController clienteController = new ClienteController(clienteRepo);
        FornecedorController fornecedorController = new FornecedorController(fornecedorRepo);
        PedidoController pedidoController = new PedidoController(pedidoRepo, produtoRepo);

        System.out.println("Serviços iniciados. Iniciando interface...");

        // --- PASSO 4: CAMADA DE VIEW (Interface) ---
        // Criamos as Views e "injetamos" os controllers E o scanner único.
        // Autor : Cauã Tobias , Natan Lima

        ProdutoView produtoView = new ProdutoView(produtoController, scanner);
        ClienteView clienteView = new ClienteView(clienteController, scanner);
        FornecedorView fornecedorView = new FornecedorView(fornecedorController, scanner);

        // A PedidoView precisa do seu controller, da ProdutoView (para listar)
        // e do scanner.
        PedidoView pedidoView = new PedidoView(pedidoController, produtoView, scanner);

        System.out.println("Sistema pronto!");

        // --- PASSO 5: MENU PRINCIPAL ---
        // O loop que roda o programa
        // Autor : Cauã Tobias , Natan Lima

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
                // Usamos o scanner principal
                opcao = Integer.parseInt(scanner.nextLine());

                // MUDANÇA: Agora chamamos os métodos nas instâncias
                // 'produtoView', 'clienteView', etc., em vez de criar novas.
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

        // --- PASSO 6: FECHAR O SCANNER ---
        // Fechamos o scanner SÓ AQUI, no final do programa.
        scanner.close();
    }
}