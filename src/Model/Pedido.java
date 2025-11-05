/* Autor: Natan e Cauã
 * Classe Pedido
 *  */
package Model;

import View.Inter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID; // <--- COMMIT: Importado para gerar IDs únicos

public class Pedido implements Inter {
    protected int id; // ID sequencial (1, 2, 3...) //
    private String numeroPedido; //  Novo campo para o ID aleatório (solicitado) //
    protected Date data;
    protected String status;
    private List<Produto> produtos;


    public Pedido(int id, Date data, String status) {
        this.id = id;
        this.data = data;
        this.status = status;
        this.produtos = new ArrayList<>();
        // COMMIT: Garante que pedidos antigos (sem UUID) recebam um ao serem carregados //
        this.numeroPedido = UUID.randomUUID().toString().substring(0, 8);
    }

    // Construtor usado pelo GerenciadorArquivo ao carregar do TXT. //
    public Pedido() {
        //  Geração de ID Aleatório
        // Gera um número de pedido único e aleatório (ex: "a8b2c1f-")
        // Usamos os primeiros 8 caracteres do UUID para facilitar a leitura.//
        this.numeroPedido = UUID.randomUUID().toString().substring(0, 8);

        // O 'id' (int) sequencial será definido pelo PedidoRepository ao salvar.
        // ❌ Removido o Random que gerava conflito de ID
        this.id = 0; // Valor temporário até o Repository definir o ID real //

        this.data = new Date(); // Data atual
        this.status = "Criado";
        this.produtos = new ArrayList<>(); // Inicializa a lista //
    }

    // Adiciona um produto à lista interna de produtos do pedido.//
    public void adicionarProduto(Produto produto) {
        if (produto != null) {
            this.produtos.add(produto);
        }
    }

    //  GETTERS E SETTERS //
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

    public String getNumeroPedido() {
        return numeroPedido;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setNumeroPedido(String numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    // METODO DE INTERFACE //
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
        return "Pedido ID: " + id + " | Num: " + numeroPedido + " | Data: " + data + " | Status: " + status;
    }
}
