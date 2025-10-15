package Controller;

import java.security.PublicKey;
import java.util.List;
import Model.Cliente;
import Repository.ClienteRepository;

public class ClienteController {
    private ClienteRepository clienteRepository;

    public ClienteController() {
        this.clienteRepository = new ClienteRepository();
    }
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
    //METODO DE NEGOCIO (opicionais)
    public Cliente buscarCliente(String nome) {
        return clienteRepository.buscarCliente(nome);
    }
}
