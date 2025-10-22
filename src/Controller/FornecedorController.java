/*
 * Controller de Fornecedor (CORRIGIDO).
 * Recebe o repositório via Injeção de Dependência.
 */
package Controller;

import java.util.List;
import Model.Fornecedor;
import Repository.FornecedorRepository;

public class FornecedorController {

    // MUDANÇA: Apenas declara o repositório
    private FornecedorRepository fornecedorRepository;

    /**
     * MUDANÇA: Construtor de Injeção de Dependência.
     * Recebe a instância única do repositório criada na Main.
     * @param fornecedorRepository A instância única do repositório.
     */
    public FornecedorController(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    // O RESTO DA CLASSE NÃO MUDA

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