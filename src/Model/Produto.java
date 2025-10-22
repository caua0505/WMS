/*
 * Classe de modelo.
 * Nenhuma alteração foi necessária aqui.
 * Os getters e setters para 'posicao' e 'local'
 * que você adicionou já estavam corretos.
 */
package Model;

import View.Inter; // Dependência da interface
import java.util.Random;

public class Produto implements Inter {
    private int id;
    private String codigo;
    private String nome;
    private int quantidade;

    // Campos de localização
    private int posicao;
    private int local;

    // Construtor principal
    public Produto(String nome, int quantidade) {
        this.codigo = gerarCodAleatorio();
        this.nome = nome;
        this.quantidade = quantidade;
        this.posicao = 0; // Valor padrão
        this.local = 0;   // Valor padrão
    }

    // Construtor usado para recriar objetos (ex: do arquivo)
    public Produto (String codigo, String nome, int quantidade){
        this.codigo = codigo;
        this.nome = nome;
        this.quantidade = quantidade;
        this.posicao = 0;
        this.local = 0;
    }

    private String gerarCodAleatorio() {
        Random aleatorio = new Random();
        int numero = 100000 + aleatorio.nextInt(900000);
        return String.valueOf(numero);
    }

    // --- GETTERS ---
    public int getId() { return id; }
    public String getCodigo() { return codigo; }
    public String getNome() { return nome; }
    public int getQuantidade() { return quantidade; }
    public int getPosicao() { return posicao; }
    public int getLocal() { return local; }

    // --- SETTERS ---
    public void setId(int id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }
    public void setLocal(int local) {
        this.local = local;
    }

    // Métodos de manipulação de estoque
    public void adicionarQuantidade(int qtd) {
        this.quantidade += qtd;
    }

    public void removerQuantidade(int qtd) {
        if (this.quantidade >= qtd) {
            this.quantidade -= qtd;
        } else {
            System.out.println("Quantidade insuficiente em estoque!");
        }
    }

    // Métodos da interface Inter
    @Override
    public void criar() { System.out.println("Produto criado: " + this); }
    @Override
    public void atualizar() { System.out.println("Produto atualizado: " + this); }
    @Override
    public void deletar() { System.out.println("Produto removido: " + this); }
    @Override
    public void listar() { System.out.println(this); }

    // toString ATUALIZADO
    @Override
    public String toString() {
        return String.format("ID: %d | Cod: %s | Produto: %s | QTD: %d | Posição: %d | Local: %d",
                id, codigo, nome, quantidade, posicao, local);
    }
}