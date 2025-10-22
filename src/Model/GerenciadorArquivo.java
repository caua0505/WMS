/*
 * Esta é a classe CORRIGIDA do "banco de dados" TXT.
 * Ela agora é responsável por salvar e carregar
 * TODAS as suas entidades, cada uma em seu próprio arquivo.
 */
package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import Repository.ProdutoRepository; // Import necessário para carregar Pedidos

public class GerenciadorArquivo {

    // Define o caractere que separa os campos no TXT
    private static final String DELIMITADOR = ";";

    // Nomes de arquivos separados para cada entidade
    private static final String ARQUIVO_PRODUTOS = "produtos.txt";
    private static final String ARQUIVO_CLIENTES = "clientes.txt";
    private static final String ARQUIVO_FORNECEDORES = "fornecedores.txt";
    private static final String ARQUIVO_PEDIDOS = "pedidos.txt";

    // Formato padrão para salvar e ler datas
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // ========================================================================
    // MÉTODOS PARA PRODUTOS
    // ========================================================================

    /**
     * Salva a LISTA completa de produtos no arquivo "produtos.txt".
     */
    public void salvarProdutos(List<Produto> produtos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_PRODUTOS))) {
            for (Produto produto : produtos) {
                // CORREÇÃO: Agora salvamos 6 campos, incluindo id, posicao e local
                // Formato: id;codigo;nome;quantidade;posicao;local
                String linha = produto.getId() + DELIMITADOR +
                        produto.getCodigo() + DELIMITADOR +
                        produto.getNome() + DELIMITADOR +
                        produto.getQuantidade() + DELIMITADOR +
                        produto.getPosicao() + DELIMITADOR +
                        produto.getLocal();
                writer.write(linha);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("ERRO AO SALVAR PRODUTOS: " + e.getMessage());
        }
    }

    /**
     * Carrega os produtos do arquivo "produtos.txt" para uma Lista.
     */
    public List<Produto> carregarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_PRODUTOS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(DELIMITADOR);

                // CORREÇÃO: Verificamos se a linha tem os 6 campos esperados
                if (dados.length == 6) {
                    try {
                        int id = Integer.parseInt(dados[0]);
                        String codigo = dados[1];
                        String nome = dados[2];
                        int quantidade = Integer.parseInt(dados[3]);
                        int posicao = Integer.parseInt(dados[4]);
                        int local = Integer.parseInt(dados[5]);

                        // Recria o objeto Produto
                        Produto produto = new Produto(codigo, nome, quantidade);
                        // Define os campos que não estão no construtor
                        produto.setId(id);
                        produto.setPosicao(posicao);
                        produto.setLocal(local);

                        produtos.add(produto);
                    } catch (NumberFormatException e) {
                        System.err.println("ERRO AO LER LINHA DE PRODUTO: " + linha);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Arquivo de produtos não encontrado. Um novo será criado.");
        }
        return produtos;
    }

    // ========================================================================
    // MÉTODOS PARA CLIENTES (LÓGICA NOVA)
    // ========================================================================

    /**
     * Salva a LISTA completa de clientes no arquivo "clientes.txt".
     */
    public void salvarClientes(List<Cliente> clientes) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_CLIENTES))) {
            for (Cliente cliente : clientes) {
                // Formato: id;nome;endereco
                String linha = cliente.getId() + DELIMITADOR +
                        cliente.getNome() + DELIMITADOR +
                        cliente.getEndereco();
                writer.write(linha);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("ERRO AO SALVAR CLIENTES: " + e.getMessage());
        }
    }

    /**
     * Carrega os clientes do arquivo "clientes.txt" para uma Lista.
     */
    public List<Cliente> carregarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_CLIENTES))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(DELIMITADOR);
                if (dados.length == 3) { // Espera 3 campos
                    try {
                        int id = Integer.parseInt(dados[0]);
                        String nome = dados[1];
                        String endereco = dados[2];

                        Cliente cliente = new Cliente(id, endereco, nome);
                        clientes.add(cliente);
                    } catch (NumberFormatException e) {
                        System.err.println("ERRO AO LER LINHA DE CLIENTE: " + linha);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Arquivo de clientes não encontrado. Um novo será criado.");
        }
        return clientes;
    }

    // ========================================================================
    // MÉTODOS PARA FORNECEDORES (LÓGICA NOVA)
    // ========================================================================

    /**
     * Salva a LISTA completa de fornecedores no arquivo "fornecedores.txt".
     */
    public void salvarFornecedores(List<Fornecedor> fornecedores) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_FORNECEDORES))) {
            for (Fornecedor f : fornecedores) {
                // Formato: id;nome;cnpj
                String linha = f.getId() + DELIMITADOR +
                        f.getNome() + DELIMITADOR +
                        f.getCnpj();
                writer.write(linha);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("ERRO AO SALVAR FORNECEDORES: " + e.getMessage());
        }
    }

    /**
     * Carrega os fornecedores do arquivo "fornecedores.txt" para uma Lista.
     */
    public List<Fornecedor> carregarFornecedores() {
        List<Fornecedor> fornecedores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_FORNECEDORES))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(DELIMITADOR);
                if (dados.length == 3) { // Espera 3 campos
                    try {
                        int id = Integer.parseInt(dados[0]);
                        String nome = dados[1];
                        String cnpj = dados[2];

                        Fornecedor f = new Fornecedor(id, cnpj, nome);
                        fornecedores.add(f);
                    } catch (NumberFormatException e) {
                        System.err.println("ERRO AO LER LINHA DE FORNECEDOR: " + linha);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Arquivo de fornecedores não encontrado. Um novo será criado.");
        }
        return fornecedores;
    }

    // ========================================================================
    // MÉTODOS PARA PEDIDOS (LÓGICA NOVA E COMPLEXA)
    // ========================================================================

    /**
     * Salva a LISTA completa de pedidos no arquivo "pedidos.txt".
     */
    public void salvarPedidos(List<Pedido> pedidos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_PEDIDOS))) {
            for (Pedido pedido : pedidos) {
                // LÓGICA ESPECIAL: Salvamos apenas os IDs dos produtos,
                // separados por vírgula. Ex: "1,2,5"
                List<String> idsProdutos = new ArrayList<>();
                for (Produto p : pedido.getProdutos()) {
                    idsProdutos.add(String.valueOf(p.getId()));
                }
                String produtosString = String.join(",", idsProdutos);

                // Formato: id;data_formatada;status;lista_de_ids_de_produto
                String linha = pedido.getId() + DELIMITADOR +
                        SDF.format(pedido.getData()) + DELIMITADOR +
                        pedido.getStatus() + DELIMITADOR +
                        produtosString;
                writer.write(linha);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("ERRO AO SALVAR PEDIDOS: " + e.getMessage());
        }
    }

    /**
     * Carrega os pedidos do arquivo "pedidos.txt".
     * @param produtoRepo O repositório de produtos já carregado (necessário
     * para "reconectar" os produtos aos pedidos).
     */
    public List<Pedido> carregarPedidos(ProdutoRepository produtoRepo) {
        List<Pedido> pedidos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_PEDIDOS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(DELIMITADOR);
                if (dados.length >= 3) { // 3 ou 4 campos
                    try {
                        int id = Integer.parseInt(dados[0]);
                        Date data = SDF.parse(dados[1]); // Converte a String de volta para Date
                        String status = dados[2];

                        // Recria o objeto Pedido
                        Pedido pedido = new Pedido(id, data, status);

                        // LÓGICA ESPECIAL: Se o campo 4 (produtos) existir...
                        if (dados.length == 4 && !dados[3].isEmpty()) {
                            String[] idsProdutos = dados[3].split(","); // Ex: ["1", "2", "5"]
                            for (String idProdutoStr : idsProdutos) {
                                int idProduto = Integer.parseInt(idProdutoStr);
                                // Usa o repositório para achar o objeto Produto real
                                Produto p = produtoRepo.buscarPorId(idProduto);
                                if (p != null) {
                                    pedido.adicionarProduto(p);
                                }
                            }
                        }
                        pedidos.add(pedido);

                    } catch (NumberFormatException | ParseException e) {
                        System.err.println("ERRO AO LER LINHA DE PEDIDO: " + linha);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Arquivo de pedidos não encontrado. Um novo será criado.");
        }
        return pedidos;
    }
}