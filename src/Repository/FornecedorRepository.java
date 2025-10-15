package Repository;

import Model.Fornecedor;
import java.util.ArrayList;
import java.util.List;

public class FornecedorRepository {
    // Lista para simular um banco de dados em memória para os fornecedores.
    private static List<Fornecedor> fornecedores = new ArrayList<>();

    /**
     * Adiciona um novo fornecedor à lista.
     * Este é o método que estava faltando.
     */
    public void adicionar(Fornecedor fornecedor) {
        fornecedores.add(fornecedor);
    }

    /**
     * Retorna la lista de todos os fornecedores cadastrados.
     * (Chamado por 'listarFornecedores' no seu controller).
     */
    public List<Fornecedor> listar() {
        return fornecedores;
    }

    /**
     * Atualiza um fornecedor existente na lista.
     * (Chamado por 'atualizarFornecedor' no seu controller).
     */
    public void atualizar(int id, Fornecedor fornecedorAtualizado) {
        for (int i = 0; i < fornecedores.size(); i++) {
            if (fornecedores.get(i).getId() == id) {
                fornecedorAtualizado.setId(id); // Garante que o ID seja mantido
                fornecedores.set(i, fornecedorAtualizado);
                return; // Encerra o método após a atualização.
            }
        }
    }

    /**
     * Remove um fornecedor da lista pelo seu ID.
     * (Chamado por 'removerFornecedor' no seu controller).
     */
    public void remover(int id) {
        fornecedores.removeIf(fornecedor -> fornecedor.getId() == id);
    }
}