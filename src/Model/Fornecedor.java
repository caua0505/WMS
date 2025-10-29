/*
 * Classe de modelo.
 * Herda os campos 'id' e 'nome' da classe Pessoa.
 */
package Model;

import View.Inter;
import java.util.Random;

public class Fornecedor extends Pessoa implements Inter {
    private String cnpj;
    private String endereco;
    private String telefone;

    // CONSTRUTOR //

    public Fornecedor( String cnpj, String nome) {
        super(gerarCodigoFornecedor(), nome);
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.telefone = (telefone != null) ? telefone : "";
    }

    // RANDOM GERAR CODIGO FORNECEDOR //
    private static String gerarCodigoFornecedor() {
        Random random = new Random();
        int numero = 100000 + random.nextInt(900000);
        return "FOR" + numero;
    }

    // GET E SET //
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        if (cnpj != null &&  !cnpj.matches("\\d{14}")) {
            this.cnpj = cnpj;
        } else {
            throw new IllegalArgumentException("CNPJ inválido1 Deve conter 14 digitos númericos");
        }
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
            this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = (telefone != null) ? telefone : "";
    }

    // METODOS DE INTERFACE //
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