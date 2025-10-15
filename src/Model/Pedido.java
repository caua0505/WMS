package Model;

import View.Inter;
import java.util.ArrayList; // <-- IMPORTANTE
import java.util.Date;
import java.util.List;      // <-- IMPORTANTE
import java.util.Random;

public class Pedido implements Inter{
    protected int id;
    protected Date data;
    protected String status;
    private List<Produto> produtos; // <-- 1. ADICIONA A LISTA DE PRODUTOS

    // Construtor
    public Pedido(int id, Date data, String status) {
        this.id = id;
        this.data = data;
        this.status = status;
        this.produtos = new ArrayList<>(); // <-- Inicializa a lista
    }

    // Gerar código aleatório
    public Pedido(){
        Random random = new Random();
        this.id = random.nextInt(100000);
        this.data = new Date(); // Garante que a data não seja nula
        this.status = "Criado";
        this.produtos = new ArrayList<>(); // <-- Inicializa a lista
    }

    // =================================================================
    // MÉTODO QUE ESTAVA FALTANDO (A SOLUÇÃO)
    // =================================================================
    /**
     * Adiciona um produto à lista interna de produtos do pedido.
     * @param produto O objeto Produto a ser adicionado.
     */
    public void adicionarProduto(Produto produto) {
        this.produtos.add(produto);
    }
    // =================================================================

    // Construtor Criar Pedido
    public int criarPedido(Date data){
        this.data = data;
        this.status = "Criado!";
        return this.id;
    }

    // Atualizar Pedido
    public void atulizarStatus(int id , String status){
        if (this.id == id){
            this.status = status;
            System.out.println("Status realizado para : " + status);
        } else {
            System.out.println("Pedido não localizado!");
        }
    }

    // Cancelar Pedido
    public void cancelarPedido(int id){
        if(this.id == id){
            this.status = "Cancelado!";
            System.out.println("Pedido Cancelado!");
        }
    }

    // Getters
    public Date getData() { return data; }
    public int getId() { return id; }
    public String getStatus() { return status; }
    public List<Produto> getProdutos() { return produtos; } // <-- Getter para a lista

    // Setters (Úteis para o repositório)
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