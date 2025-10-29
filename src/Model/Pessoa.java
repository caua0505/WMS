package Model;

public abstract class Pessoa {
    protected String id; // Agora ID é String
    protected String nome;

    // Construtor
    public Pessoa(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    // Setter
    public void setId(String id) {
        this.id = id;
    }
}
