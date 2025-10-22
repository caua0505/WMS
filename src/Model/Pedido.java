/*
 * Classe de modelo.
 * Nenhuma alteração foi necessária aqui.
 * O método 'adicionarProduto' que você adicionou
 * já estava correto.
 */
package Model;

import View.Inter; // Dependência da interface
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Pedido implements Inter{
    protected int id;
    protected Date data;
    protected String status;
    private List<Produto> produtos; // Lista de produtos no pedido

    // Construtor usado pelo GerenciadorArquivo
    public Pedido(int id, Date data, String status) {
        this.id = id;
        this.data = data;
        this.status = status;
        this.produtos = new ArrayList<>(); // Inicializa a lista
    }

    // Construtor para criar um novo pedido
    public Pedido(){
        Random random = new Random();
        this.id = random.nextInt(100000); // ID temporário, será sobrescrito pelo Repositório
        this.data = new Date(); // Data atual
        this.status = "Criado";
        this.produtos = new ArrayList<>(); // Inicializa a lista
    }

    /**
     * Adiciona um produto à lista interna de produtos do pedido.
     */
    public void adicionarProduto(Produto produto) {
        this.produtos.add(produto);
    }

    // Métodos antigos de status (não mais usados pelo Controller, mas podem ficar)
    public int criarPedido(Date data){
        this.data = data;
        this.status = "Criado!";
        return this.id;
    }
    public void atulizarStatus(int id , String status){
        if (this.id == id){
            this.status = status;
        }
    }
    public void cancelarPedido(int id){
        if(this.id == id){
            this.status = "Cancelado!";
        }
    }

    // Getters
    public Date getData() { return data; }
    public int getId() { return id; }
    public String getStatus() { return status; }
    public List<Produto> getProdutos() { return produtos; }

    // Setters (Usados pelo Repositório/Controller)
    public void setId(int id) { this.id = id; }
    public void setStatus(String status) { this.status = status; }

    // Métodos de Interface Inter
    @Override
    public void criar(){ System.out.println("Pedido Criado!" + this); }
    @Override
    public void atualizar(){ System.out.println("Pedido Atualizado!" + this); }
    @Override
    public void deletar() { System.out.println("Pedido Deletado!" + this); }
    @Override
    public void listar() { System.out.println(this); }

    @Override
    public String toString() {
        return "Pedido ID: " + id + "|Data: " + data + "|Status: " + status;
    }
}