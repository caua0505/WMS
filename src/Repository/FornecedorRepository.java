/*
 * Repositório de Fornecedores (CORRIGIDO).
 * Responsável por gerenciar a lista de fornecedores.
 */
package Repository;

import Model.Fornecedor;
import Model.GerenciadorArquivo; // Importa o gerenciador
import java.util.List;

public class FornecedorRepository {

    // MUDANÇA: A lista não é mais 'static'
    private List<Fornecedor> fornecedores;

    // NOVO: Instância do gerenciador
    private GerenciadorArquivo gerenciador;

    // NOVO: Construtor que carrega dados do "fornecedores.txt"
    public FornecedorRepository() {
        // Aponta para o GerenciadorArquivo no pacote Model
        this.gerenciador = new Model.GerenciadorArquivo();
        this.fornecedores = gerenciador.carregarFornecedores();
    }

    /**
     * Adiciona um novo fornecedor à lista e salva no arquivo.
     */
    public void adicionar(Fornecedor fornecedor) {
        fornecedores.add(fornecedor);
        // NOVO: Salva a lista atualizada no arquivo
        gerenciador.salvarFornecedores(fornecedores);
    }

    /**
     * Retorna la lista de todos os fornecedores cadastrados.
     */
    public List<Fornecedor> listar() {
        return fornecedores;
    }

    /**
     * Atualiza um fornecedor existente na lista e salva no arquivo.
     */
    public void atualizar(int id, Fornecedor fornecedorAtualizado) {
        for (int i = 0; i < fornecedores.size(); i++) {
            if (fornecedores.get(i).getId() == id) {
                fornecedorAtualizado.setId(id);
                fornecedores.set(i, fornecedorAtualizado);
                // NOVO: Salva a lista atualizada no arquivo
                gerenciador.salvarFornecedores(fornecedores);
                return;
            }
        }
    }

    /**
     * Remove um fornecedor da lista e salva no arquivo.
     */
    public void remover(int id) {
        if (fornecedores.removeIf(fornecedor -> fornecedor.getId() == id)) {
            // NOVO: Salva a lista atualizada no arquivo
            gerenciador.salvarFornecedores(fornecedores);
        }
    }
}