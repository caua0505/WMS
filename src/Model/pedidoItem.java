package Model;

import Model.Pedido;
import java.util.Date;

public class pedidoItem extends Pedido{
    private Produto produto;
    private int qtd;

    public pedidoItem(){
        super ();
    }

    // Construtor Herdando de Pedido
    public pedidoItem(Date data, String status, Produto produto, int qtd){
        super(0, data, status);
        this.produto = produto;
        this.qtd = qtd;
    }

    // Adicionar Item
    public void adicionarItem() {
        System.out.println("Item adicionado: " + produto.getNome() + "Quantidade: " + qtd);
    }

    // Get Produto
    public Produto getProduto() {
        return produto;
    }

    // Set Produto
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    // Get Quantidade
    public int getQtd() {
        return qtd;
    }

    // Set Quantidade
    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
}




