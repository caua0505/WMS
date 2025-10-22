/*
 * Classe de modelo.
 * Nenhuma alteração foi necessária aqui.
 */
package Model;

import View.Inter; // Dependência da interface

public class Fornecedor extends Pessoa implements Inter{
    private String cnpj;

    // Construtor Herdando de Pessoa
    public Fornecedor(int id , String cnpj , String nome) {
        super(id, nome);
        this.cnpj = cnpj;
    }

    // Get Cnpj
    public String getCnpj() {
        return cnpj;
    }

    // Metodo Interface
    @Override
    public void criar() {
        System.out.println("Criando fornecedor!");
    }

    @Override
    public void atualizar() {
        System.out.println("Atualizando fornecedor!");
    }

    @Override
    public void deletar() {
        System.out.println("Deletando fornecedor!");
    }

    @Override
    public void listar() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return id + " - " + cnpj + " - " + nome;
    }
}