/*
 * Classe de modelo (Abstrata).
 * Serve como base para Cliente e Fornecedor.
 * Não pode ser instanciada diretamente (new Pessoa()).
 */
package Model;

public abstract class Pessoa {
    // Protected: Visível para esta classe e suas subclasses
    protected int id;
    protected String nome;

    //Construtor
    public Pessoa(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    // Setter necessário para atualizações
    public void setId(int id) {
        this.id = id;
    }
}