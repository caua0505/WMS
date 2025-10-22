/*
 * Classe de modelo (Abstrata).
 * Nenhuma alteração foi necessária aqui.
 */
package Model;

public abstract class Pessoa {
    protected int id;
    protected String nome;

    //Construtor
    public Pessoa(int id, String nome){
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

    // MÉTODO NECESSÁRIO PARA ATUALIZAÇÕES
    public void setId(int id) {
        this.id = id;
    }
}