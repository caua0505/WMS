/*
 * Classe de modelo (MODIFICADA).
 *
 * "COMMIT" (Objetivo 1):
 * - Adicionado um novo campo 'numeroPedido' (String) para
 * guardar um identificador aleatório (UUID).
 * - O 'id' (int) continua sendo o identificador sequencial (1, 2, 3...).
 */
package Model;

import View.Inter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID; // <--- COMMIT: Importado para gerar IDs únicos

public class Pedido implements Inter {
    protected int id; // ID sequencial (1, 2, 3...)
    private String numeroPedido; // <--- COMMIT: Novo campo para o ID aleatório (solicitado)
    protected Date data;
    protected String status;
    private List<Produto> produtos;

    /**
     * Construtor usado pelo GerenciadorArquivo ao carregar do TXT.
     */
    public Pedido(int id, Date data, String status) {
        this.id = id;
        this.data = data;
        this.status = status;
        this.produtos = new ArrayList<>();
        // <--- COMMIT: Garante que pedidos antigos (sem UUID) recebam um ao serem carregados
        this.numeroPedido = UUID.randomUUID().toString().substring(0, 8);
    }

    /**
     * Construtor para criar um novo pedido (usado pelo Controller).
     */
    public Pedido() {
        // --- COMMIT (Objetivo 1): Geração de ID Aleatório ---
        // Gera um número de pedido único e aleatório (ex: "a8b2c1f-")
        // Usamos os primeiros 8 caracteres do UUID para facilitar a leitura.
        this.numeroPedido = UUID.randomUUID().toString().substring(0, 8);

        // O 'id' (int) sequencial será definido pelo PedidoRepository ao salvar.
        Random random = new Random();
        this.id = random.nextInt(100000); // Valor temporário

        this.data = new Date(); // Data atual
        this.status = "Criado";
        this.produtos = new ArrayList<>(); // Inicializa a lista
    }

    /**
     * Adiciona um produto à lista interna de produtos do pedido.
     */
    public void adicionarProduto(Produto produto) {
        if (produto != null) {
            this.produtos.add(produto);
        }
    }

    // --- GETTERS ---
    public Date getData() {
        return data;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    // --- COMMIT: Getter para o novo campo ---
    public String getNumeroPedido() {
        return numeroPedido;
    }

    // --- SETTERS (Usados pelo Repositório/Controller) ---
    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // --- COMMIT: Setter para o novo campo (usado pelo GerenciadorArquivo) ---
    public void setNumeroPedido(String numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    // --- Métodos de Interface Inter (Apenas exibem no console) ---
    @Override
    public void criar() {
        System.out.println("Pedido Criado!" + this);
    }

    @Override
    public void atualizar() {
        System.out.println("Pedido Atualizado!" + this);
    }

    @Override
    public void deletar() {
        System.out.println("Pedido Deletado!" + this);
    }

    @Override
    public void listar() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        // --- COMMIT: Atualizado o toString para incluir o novo número ---
        return "Pedido ID: " + id + " | Num: " + numeroPedido + " | Data: " + data + " | Status: " + status;
    }
}