/*
 * Autor ;Natan e Cauã
 * Classe do "banco de dados" TXT.
 */
package Model;

import Repository.ProdutoRepository;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GerenciadorArquivo {

    // Define o caractere que separa os campos no TXT //
    private static final String DELIMITADOR = ";";

    // Nomes de arquivos separados para cada entidade
    private static final String ARQUIVO_PRODUTOS = "produtos.txt";
    private static final String ARQUIVO_CLIENTES = "clientes.txt";
    private static final String ARQUIVO_FORNECEDORES = "fornecedores.txt";
    private static final String ARQUIVO_PEDIDOS = "pedidos.txt";

    // Formato padrão para salvar e ler datas
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // MÉTODOS PARA PRODUTOS //
    // Salva a LISTA completa de produtos no arquivo "produtos.txt".
    // Este metodo SOBRESCREVE o arquivo antigo com a lista atualizada //

    public void salvarProdutos(List<Produto> produtos) {
        // "try-with-resources" garante que o 'writer' seja fechado //
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_PRODUTOS))) {
            for (Produto produto : produtos) {
                // Formato: id;codigo;nome;quantidade;posicao;local //
                String linha = produto.getId() + DELIMITADOR +
                        produto.getCodigo() + DELIMITADOR +
                        produto.getNome() + DELIMITADOR +
                        produto.getQuantidade() + DELIMITADOR +
                        produto.getPosicao() + DELIMITADOR +
                        produto.getLocal();
                writer.write(linha);
                writer.newLine(); // Pula para a próxima linha //
            }
        } catch (IOException e) {
            System.err.println("ERRO AO SALVAR PRODUTOS: " + e.getMessage());
        }
    }

    // METODO LISTAR PRODUTOS //
    public List<Produto> carregarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_PRODUTOS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(DELIMITADOR);

                if (dados.length == 6) { // Espera 6 campos //
                    try {
                        int id = Integer.parseInt(dados[0]);
                        String codigo = dados[1];
                        String nome = dados[2];
                        int quantidade = Integer.parseInt(dados[3]);
                        int posicao = Integer.parseInt(dados[4]);
                        int local = Integer.parseInt(dados[5]);

                        Produto produto = new Produto(codigo, nome, quantidade);
                        produto.setId(id);
                        produto.setPosicao(posicao);
                        produto.setLocal(local);
                        produtos.add(produto);
                    } catch (NumberFormatException e) {
                        System.err.println("ERRO AO LER LINHA DE PRODUTO (formato numérico): " + linha);
                    }
                }
            }
        } catch (IOException e) {
            // Isso é normal na primeira vez que o programa roda //
            System.out.println("Arquivo de produtos não encontrado. Um novo será criado.");
        }
        return produtos;
    }

    // MÉTODOS PARA CLIENTES //
    public List<Cliente> carregarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_CLIENTES))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(DELIMITADOR);
                if (dados.length == 3) { // Espera 3 campos //
                    try {
                        String id = dados[0];
                        String nome = dados[1];
                        String endereco = dados[2];
                        Cliente cliente = new Cliente("" ,endereco, nome, "");
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

    // MÉTODOS PARA FORNECEDORES //
    public void salvarClientes(List<Cliente> clientes) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_CLIENTES))) {
            for (Cliente cliente : clientes) {
                // Formato: id;nome;endereco
                String linha = cliente.getId() + DELIMITADOR
                        + cliente.getNome() + DELIMITADOR
                        + cliente.getEndereco();
                writer.write(linha);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("ERRO AO SALVAR CLIENTES: " + e.getMessage());
            e.printStackTrace(); // adiciona para debugar erros de escrita //
        }
    }

    public void salvarFornecedores(List<Fornecedor> fornecedores) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_FORNECEDORES))) {
            for (Fornecedor f : fornecedores) {
                // Formato: id;nome;cnpj //
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

    // METODO LISTAR FORNECEDORES //
    public List<Fornecedor> carregarFornecedores() {
        List<Fornecedor> fornecedores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_FORNECEDORES))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(DELIMITADOR);
                if (dados.length == 3) { // Espera 3 campos //
                    try {
                        String id = dados[0]; // <<< corrigido: era int
                        String nome = dados[1];
                        String cnpj = dados[2];
                        Fornecedor f = new Fornecedor(cnpj, nome);
                        f.setId(id); // <<< garante que mantém o mesmo ID
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

    // MÉTODOS PARA PEDIDOS (MODIFICADOS)
    // Salva a LISTA completa de pedidos no arquivo "pedidos.txt" //
    public void salvarPedidos(List<Pedido> pedidos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_PEDIDOS))) {
            for (Pedido pedido : pedidos) {
                List<String> idsProdutos = new ArrayList<>();
                for (Produto p : pedido.getProdutos()) {
                    idsProdutos.add(String.valueOf(p.getId()));
                }
                String produtosString = String.join(",", idsProdutos);

                String linha = pedido.getId() + DELIMITADOR +
                        pedido.getNumeroPedido() + DELIMITADOR +
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

    // METODO LISTAR PRODUTOS //
    public List<Pedido> carregarPedidos(ProdutoRepository produtoRepo) {
        List<Pedido> pedidos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_PEDIDOS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(DELIMITADOR);
                if (dados.length >= 4) {
                    try {
                        int id = Integer.parseInt(dados[0]);
                        String numeroPedido = dados[1];
                        Date data = SDF.parse(dados[2]);
                        String status = dados[3];
                        Pedido pedido = new Pedido(id, data, status);
                        pedido.setNumeroPedido(numeroPedido);

                        if (dados.length == 5 && !dados[3].isEmpty()) {
                            String[] idsProdutos = dados[4].split(",");
                            for (String idProdutoStr : idsProdutos) {
                                int idProduto = Integer.parseInt(idProdutoStr);
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
