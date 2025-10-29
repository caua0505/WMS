package Model;

import View.Inter;
import java.util.Random;

public class Cliente extends Pessoa implements Inter {
    private String endereco;
    private String cpf;
    private String telefone;

    // CONSTRUTOR //
    public Cliente(String cpf, String endereco, String nome, String telefone) {
        super(gerarCodigoAleatorio(), nome); // ID é gerado automaticamente
        this.endereco = endereco;
        this.telefone = (telefone != null) ? telefone : "";
        if (cpf != null && !cpf.isEmpty()) {
            setCpf(cpf); // validação de CPF
        } else {
            this.cpf = "";
        }
    }

    // GETTERS E SETTERS //

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        // Remove caracteres que não são números
        String somenteNumeros = cpf.replaceAll("\\D", "");
        if (somenteNumeros.length() == 11) {
            this.cpf = somenteNumeros;
        } else {
            throw new IllegalArgumentException("CPF inválido! Deve conter 11 dígitos numéricos.");
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

    // GERAR CÓDIGO AUTOMÁTICO //
    private static String gerarCodigoAleatorio() {
        Random random = new Random();
        int numero = 10000 + random.nextInt(90000); // gera 5 dígitos
        return "CLI" + numero;
    }

    // MÉTODOS DA INTERFACE //
    @Override
    public void criar() {
        System.out.println("Criando Cliente! " + this);
    }

    @Override
    public void atualizar() {
        System.out.println("Atualizando Cliente! " + this);
    }

    @Override
    public void deletar() {
        System.out.println("Deletando Cliente! " + this);
    }

    @Override
    public void listar() {
        System.out.println(this);
    }

    // TO STRING //
    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", endereco='" + endereco + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
