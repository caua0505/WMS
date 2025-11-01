/*
    * Autor: Natan e Cauã
    * Classe Pessoa
    * Herdam dessa classe Cliente e Fornecedor.
*/

package Model;

public abstract class Pessoa {
    protected String id; // Agora ID é String //
    protected String nome;

    // CONSTRUTOR //
    public Pessoa(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    // GETTERS E SETTERS //
    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setId(String id) {
        this.id = id;
    }
}
