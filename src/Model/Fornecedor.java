/*
 * Classe de modelo.
 * Herda os campos 'id' e 'nome' da classe Pessoa.
 */
package Model;

import View.Inter;

public class Fornecedor extends Pessoa implements Inter {
    private String cnpj;

    // Construtor que chama o construtor da superclasse (Pessoa)
    public Fornecedor(int id, String cnpj, String nome) {
        super(id, nome);
        this.cnpj = cnpj;
    }

    // Get Cnpj
    public String getCnpj() {
        return cnpj;
    }

    // Metodos da Interface
    @Override
    public void criar() { System.out.println("Criando fornecedor!"); }
    @Override
    public void atualizar() { System.out.println("Atualizando fornecedor!"); }
    @Override
    public void deletar() { System.out.println("Deletando fornecedor!"); }
    @Override
    public void listar() { System.out.println(this); }

    @Override
    public String toString() {
        return id + " - " + cnpj + " - " + nome;
    }
}