// GerenciadorArquivo.java
package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GerenciadorArquivo {

    private static final String NOME_ARQUIVO = "estoque.txt";
    private static final String DELIMITADOR = ";";

    /**
     * Salva o mapa de produtos em um arquivo .TXT.
     * @param produtos O mapa de produtos a ser salvo.
     */
    public void salvarProdutos(Map<String, Produto> produtos) {
        // Usa try-with-resources para garantir que o arquivo seja fechado.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOME_ARQUIVO))) {
            for (Produto produto : produtos.values()) {
                // Formata a linha: codigo;nome;quantidade
                String linha = produto.getCodigo() + DELIMITADOR +
                        produto.getNome() + DELIMITADOR +
                        produto.getQuantidade();
                writer.write(linha);
                writer.newLine(); // Pula para a próxima linha
            }
        } catch (IOException e) {
            System.err.println("ERRO AO SALVAR ARQUIVO: " + e.getMessage());
        }
    }

    /**
     * Carrega os produtos do arquivo .TXT para um mapa.
     * @return Um mapa com os produtos carregados.
     */
    public Map<String, Produto> carregarProdutos() {
        Map<String, Produto> produtos = new HashMap<>();
        // Usa try-with-resources para garantir que o arquivo seja fechado.
        try (BufferedReader reader = new BufferedReader(new FileReader(NOME_ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(DELIMITADOR);
                if (dados.length == 3) {
                    String codigo = dados[0];
                    String nome = dados[1];
                    int quantidade = Integer.parseInt(dados[2]); // Converte String para int

                    // Usa o novo construtor da classe Produto para recriar o objeto
                    Produto produto = new Produto(codigo, nome, quantidade);
                    produtos.put(codigo, produto);
                }
            }
        } catch (IOException e) {
            // Se o arquivo não existe, apenas retorna um mapa vazio (normal na primeira execução)
            System.out.println("Arquivo de estoque não encontrado. Um novo será criado ao salvar.");
        } catch (NumberFormatException e) {
            System.err.println("ERRO AO LER ARQUIVO: Formato de número inválido. " + e.getMessage());
        }
        return produtos;
    }
}