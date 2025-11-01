/*
    * Autor: Natan e Cauã
    * Classe Pedido item
    * Herda de Pedido
*/
package Model;

import Model.Pedido;
import java.util.Date;

public class pedidoItem extends Pedido {
    private Produto produto;
    private int qtd;

    public pedidoItem() {
        super();
    }

    // CONSTRUTOR Herdando de Pedido //
    public pedidoItem(Date data, String status, Produto produto, int qtd) {
        super(0, data, status);
        this.produto = produto;
        this.qtd = qtd;
    }

    // Adicionar Item //
    public void adicionarItem() {
        System.out.println("Item adicionado: " + produto.getNome() + "Quantidade: " + qtd);
    }

    // GETTERS E SETTERS //
    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    public int getQtd() {
        return qtd;
    }
    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
}