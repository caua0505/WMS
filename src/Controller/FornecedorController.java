/*
 * Controller de Fornecedor.
 * Recebe o repositório via Injeção de Dependência.
 */
package Controller;

import java.util.List;
import Model.Fornecedor;
import Repository.FornecedorRepository;

public class FornecedorController {

    private final FornecedorRepository fornecedorRepository;

    /**
     * Construtor de Injeção de Dependência.
     */
    public FornecedorController(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    // ---- Métodos que delegam para o Repositório ----

    /**
     * Cadastra um novo fornecedor.
     */
    public void cadastrarFornecedor(Fornecedor fornecedor) {
        fornecedorRepository.adicionar(fornecedor);
    }

    /**
     * Retorna a lista de todos os fornecedores.
     */
    public List<Fornecedor> listarFornecedores() {
        return fornecedorRepository.listar();
    }

    /**
     * Atualiza um fornecedor existente.
     * O ID é passado como String, pois é gerado automaticamente.
     */
    public void atualizarFornecedor(String id, Fornecedor fornecedorAtualizado) {
        fornecedorRepository.atualizar(id, fornecedorAtualizado);
    }

    /**
     * Remove um fornecedor existente pelo ID.
     */
    public void removerFornecedor(String id) {
        fornecedorRepository.remover(id);
    }
}
