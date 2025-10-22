/*
 * Classe de modelo.
 * Nenhuma alteração foi necessária aqui.
 */
package Model;

import View.Inter; // Dependência da interface

public class Cliente extends Pessoa implements Inter{
    private String endereco;

    // Construtor Herdando de Pessoa
    public Cliente(int id , String endereco, String nome) {
        super(id, nome);
        this.endereco = endereco;
    }

    // Get Endereco
    public String getEndereco() {
        return endereco;
    }

    // Metodo Interface
    @Override
    public void criar() {
        System.out.println("Criando Cliente!" + this);
    }

    @Override
    public void atualizar() {
        System.out.println("Atualizando Cliente!" + this);
    }

    @Override
    public void deletar() {
        System.out.println("Deletando Cliente!" + this);
    }

    @Override
    public void listar() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return id + "-" + nome + "-" + endereco;
    }
}