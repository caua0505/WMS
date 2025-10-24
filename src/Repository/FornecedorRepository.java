/*
 * Repositório de Fornecedores.
 * Mesma lógica do ClienteRepository.
 */
package Repository;

import Model.Fornecedor;
import Model.GerenciadorArquivo;
import java.util.List;

public class FornecedorRepository {

    private List<Fornecedor> fornecedores; // Lista em memória
    private GerenciadorArquivo gerenciador;

    /**
     * Construtor.
     * Carrega os fornecedores do "fornecedores.txt" ao iniciar.
     */
    public FornecedorRepository() {
        this.gerenciador = new Model.GerenciadorArquivo();
        this.fornecedores = gerenciador.carregarFornecedores();
    }

    /**
     * Adiciona um novo fornecedor à lista e salva no arquivo.
     */
    public void adicionar(Fornecedor fornecedor) {
        fornecedores.add(fornecedor); // Adiciona na memória

        // --- "COMMIT" (Objetivo 2): A GRAVAÇÃO ---
        gerenciador.salvarFornecedores(fornecedores); // Salva no TXT
    }

    /**
     * Atualiza um fornecedor existente na lista e salva no arquivo.
     */
    public void atualizar(int id, Fornecedor fornecedorAtualizado) {
        for (int i = 0; i < fornecedores.size(); i++) {
            if (fornecedores.get(i).getId() == id) {
                fornecedorAtualizado.setId(id);
                fornecedores.set(i, fornecedorAtualizado); // Atualiza na memória

                // --- "COMMIT" (Objetivo 2): A GRAVAÇÃO ---
                gerenciador.salvarFornecedores(fornecedores); // Salva no TXT
                return;
            }
        }
    }

    /**
     * Remove um fornecedor da lista e salva no arquivo.
     */
    public void remover(int id) {
        if (fornecedores.removeIf(fornecedor -> fornecedor.getId() == id)) { // Remove da memória
            // --- "COMMIT" (Objetivo 2): A GRAVAÇÃO ---
            gerenciador.salvarFornecedores(fornecedores); // Salva no TXT
        }
    }

    // --- Métodos de Leitura ---

    public List<Fornecedor> listar() {
        return fornecedores;
    }
}