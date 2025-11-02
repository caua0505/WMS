/*
 * Controller de Cliente.
 * Recebe o repositório via Injeção de Dependência.
 */
package Controller;

import java.util.List;
import Model.Cliente;
import Repository.ClienteRepository;

public class ClienteController {

    private ClienteRepository clienteRepository;

    /**
     * Construtor de Injeção de Dependência.
     */
    public ClienteController(ClienteRepository clienteRepository) {

        this.clienteRepository = clienteRepository;
    }

    // ---- Métodos que delegam para o Repositório ----

    //CREATE
    public void cadastroCliente(Cliente cliente) {
        clienteRepository.adicionar(cliente);
    }
    //READ
    public List<Cliente> listarCliente() {
        return clienteRepository.listar();
    }
    //UPDATE
    public void atualizarCliente(String id, Cliente clienteAtualizado) {
        clienteRepository.atualizar(id, clienteAtualizado);
    }
    //DELETE
    public void removerCliente(String id) {
        clienteRepository.remover(String.valueOf(id));
    }
    //METODO DE NEGOCIO
    public Cliente buscarCliente(String nome) {
        return clienteRepository.buscarCliente(nome);
    }
}