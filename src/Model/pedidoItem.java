/*
 * Classe de modelo.
 * OBS: Esta classe parece ser um conceito antigo do seu projeto
 * e não está sendo usada pela View/Controller/Repository
 * (o Pedido agora tem uma List<Produto>).
 * Mantive ela aqui, mas ela não está conectada ao resto do fluxo.
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

    // Construtor Herdando de Pedido
    public pedidoItem(Date data, String status, Produto produto, int qtd) {
        super(0, data, status);
        this.produto = produto;
        this.qtd = qtd;
    }

    // Adicionar Item
    public void adicionarItem() {
        System.out.println("Item adicionado: " + produto.getNome() + "Quantidade: " + qtd);
    }

    // Getters e Setters
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