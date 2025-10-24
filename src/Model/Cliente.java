/*
 * Classe de modelo.
 * Herda os campos 'id' e 'nome' da classe Pessoa.
 */
package Model;

import View.Inter; // Dependência da interface

public class Cliente extends Pessoa implements Inter {
    private String endereco;

    // Construtor que chama o construtor da superclasse (Pessoa)
    public Cliente(int id, String endereco, String nome) {
        super(id, nome); // Passa 'id' e 'nome' para Pessoa
        this.endereco = endereco;
    }

    // Get Endereco
    public String getEndereco() {
        return endereco;
    }

    // Metodos da Interface
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