package Controller;

import java.util.List;
import Model.Fornecedor;
import Repository.FornecedorRepository;

public class FornecedorController {

    private FornecedorRepository fornecedorRepository;

    public FornecedorController() {
        this.fornecedorRepository = new FornecedorRepository();
    }

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
