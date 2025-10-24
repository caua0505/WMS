/*
 * Controller de Fornecedor.
 * Recebe o repositório via Injeção de Dependência.
 */
package Controller;

import java.util.List;
import Model.Fornecedor;
import Repository.FornecedorRepository;

public class FornecedorController {

    private FornecedorRepository fornecedorRepository;

    /**
     * Construtor de Injeção de Dependência.
     */
    public FornecedorController(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    // --- Métodos que delegam para o Repositório ---

    public void cadastrarFornecedor(Fornecedor fornecedor) {
        fornecedorRepository.adicionar(fornecedor);
    }

    public List<Fornecedor> listarFornecedores() {
        return fornecedorRepository.listar();
    }

    public void atualizarFornecedor(int id, Fornecedor fornecedorAtualizado) {
        fornecedorRepository.atualizar(id, fornecedorAtualizado);
    }

    public void removerFornecedor(int id) {
        fornecedorRepository.remover(id);
    }
}