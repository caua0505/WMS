package Repository;

import Model.Cliente;
import Model.GerenciadorArquivo;
import java.util.List;

public class ClienteRepository {

    private List<Cliente> clientes;
    private GerenciadorArquivo gerenciador;

    public ClienteRepository() {
        this.gerenciador = new Model.GerenciadorArquivo();
        this.clientes = gerenciador.carregarClientes();
    }

    // ---- Adicionar ----
    public void adicionar(Cliente cliente) {
        clientes.add(cliente);
        gerenciador.salvarClientes(clientes);
    }

    // ---- Atualizar ----
    public void atualizar(String id, Cliente clienteAtualizado) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId().equals(id)) {
                clienteAtualizado.setId(id);
                clienteAtualizado.setTelefone(clienteAtualizado.getTelefone());
                clienteAtualizado.setCpf(clienteAtualizado.getCpf());
                clienteAtualizado.setEndereco(clienteAtualizado.getEndereco());
                clientes.set(i, clienteAtualizado);

                gerenciador.salvarClientes(clientes);
                System.out.println("🔄 Cliente atualizado com sucesso!");
                return;
            }
        }
        System.out.println("⚠️ Cliente não encontrado para atualização.");
    }

    // ---- Remover ----
    public void remover(String id) {
        if (clientes.removeIf(cliente -> cliente.getId().equals(id))) {
            gerenciador.salvarClientes(clientes);
            System.out.println("🗑️ Cliente removido com sucesso!");
        } else {
            System.out.println("⚠️ Cliente não encontrado para remoção.");
        }
    }

    // ---- Listar ----
    public List<Cliente> listar() {
        return clientes;
    }

    // ---- Buscar ----
    public Cliente buscarCliente(String nome) {
        for (Cliente cliente : clientes) {
            if (cliente.getNome().equalsIgnoreCase(nome)) {
                return cliente;
            }
        }
        return null;
    }
}
