package Model;

import View.Inter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;



public class Estoque implements Inter {
    private Map<String, Produto> produtos = new HashMap<>();
    private GerenciadorArquivo gerenciador;

    //construtor
    public Estoque () {
        this.gerenciador = new GerenciadorArquivo();
        this.produtos = new HashMap<>();
    }

        //Novos métodos para persistência
        public void carregarDadosDoArquivo() {
            this.produtos = gerenciador.carregarProdutos();
            System.out.println("Dados de estoque carregados.");
        }

        public void salvarDadosNoArquivo() {
            gerenciador.salvarProdutos(this.produtos);
            System.out.println("Dados de estoque salvos com sucesso");
        }



    // Criar / Adicionar Produto
    @Override
    public void criar() {
        System.out.println("Criando estoque! (Nome e Quantidade do Produto): ");
    }

    //Atulizar Estoque
    @Override
    public void atualizar(){
        System.out.println("Atulizando Estoque! (Código e Quantidade do Produto): ");
    }

    //Deletar Produto do Estoque
    @Override
    public void deletar(){
        System.out.println("Deletando Produto! (Código de Produto): ");
    }

    //Listar Produtos
    @Override
    public void listar(){
        listarProdutos();
    }


    public void cadastrarProduto(String nome , int quantidade) {
        Produto p = new Produto(nome, quantidade);
        if(produtos.containsKey(p.getCodigo())) {
            System.out.println("Produto existente");
        } else {
            produtos.put(p.getCodigo(), p);
            System.out.println("Produto adicionado com sucesso. Codígo :  " + p.getCodigo());
            salvarDadosNoArquivo();
        }
    }

    public void atualizarEstoque (String codigo, int  quantidade) {
        Produto p = produtos.get(codigo);
        if (p == null){
            System.out.println("Produto não encontrado.");
            return;
        }
        if (quantidade >= 0){
            p.adicionarQuantidade(quantidade);
            System.out.println("Estoque Atualizado (Entrada)" + quantidade + " Unidades adicionadas.");
        }else{
            p.removerQuantidade(-quantidade);
            System.out.println("Estoque Atualizado(Saida)" +(-quantidade) +" Unidades removidas.");
            salvarDadosNoArquivo();
        }
    }

    public void deletarProduto(String codigo){
        if (produtos.remove(codigo) != null) {
            System.out.println("Produto removido com sucesso: " + codigo);
            salvarDadosNoArquivo();
        } else {
            System.out.println("Produto não encontrado: " + codigo);
        }
    }

    public void listarProdutos(){
        if(produtos.isEmpty()){
            System.out.println("Nenhum produto encontrado");
        }else {
            System.out.println("=== Lista de Produtos ===");
            for (Produto p : produtos.values()){
                System.out.println(p);
            }
        }
    }
    public void buscarProdutoPorCodigo(String codigo) {
        for (Produto p : produtos.values()) {
            if (p.getCodigo().equals(codigo)) {
                System.out.println("Produto encontrado: " + p);
                return;
            }

        }
        System.out.println("Produto com código " + codigo + " não encontrado.");
    }
    public void buscarProdutoPorNome(String nome) {
        boolean encontrado = false;
        for (Produto p : produtos.values()) {
            if (p.getNome().equalsIgnoreCase(nome)){
                System.out.println("Produto encontrado: " + p);
                encontrado = true;

            }
        }
        if (!encontrado) {
            System.out.println("Produto com nome '" + nome + "' não encontrado");
        }
    }
    public Produto buscarProduto(String codigo){
        return produtos.get(codigo);
    }
}

