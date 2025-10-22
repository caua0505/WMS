/*
 * Controller de Cliente (CORRIGIDO).
 * Recebe o repositório via Injeção de Dependência.
 */
package Controller;

import java.util.List;
import Model.Cliente;
import Repository.ClienteRepository;

public class ClienteController {

    // MUDANÇA: Apenas declara o repositório
    private ClienteRepository clienteRepository;

    /**
     * MUDANÇA: Construtor de Injeção de Dependência.
     * O controller 'recebe' a instância do repositório criada na Main.
     * @param clienteRepository A instância única do repositório.
     */
    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // O RESTO DA CLASSE NÃO MUDA

    //CREATE
    public void cadastroCliente(Cliente cliente) {
        clienteRepository.adicionar(cliente);
    }
    //READ
    public List<Cliente> listarCliente() {
        return clienteRepository.listar();
    }
    //UPDATE
    public void atualizarCliente(int id, Cliente clienteAtualizado) {
        clienteRepository.atulizar(id, clienteAtualizado);
    }
    //DELETE
    public void removerCliente(int id) {
        clienteRepository.remover(id);
    }
    //METODO DE NEGOCIO
    public Cliente buscarCliente(String nome) {
        return clienteRepository.buscarCliente(nome);
    }
}