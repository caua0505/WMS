package Repository;

import Model.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepository {
    // Lista para simular um banco de dados em memória para os clientes.
    private static List<Cliente> clientes = new ArrayList<>();

    /**
     * Adiciona um novo cliente à lista.
     * Este é o método que estava faltando.
     */
    public void adicionar(Cliente cliente) {
        clientes.add(cliente);
    }

    /**
     * Retorna a lista de todos os clientes cadastrados.
     * (Chamado por 'listarCliente' no seu controller).
     */
    public List<Cliente> listar() {
        return clientes;
    }

    /**
     * Atualiza um cliente existente na lista.
     * (Chamado por 'atualizarCliente' no seu controller. Note o nome 'atulizar').
     */
    public void atulizar(int id, Cliente clienteAtualizado) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == id) {
                clienteAtualizado.setId(id); // Garante que o ID seja mantido
                clientes.set(i, clienteAtualizado);
                return; // Encerra o método após encontrar e atualizar.
            }
        }
    }

    /**
     * Remove um cliente da lista pelo seu ID.
     * (Chamado por 'removerCliente' no seu controller).
     */
    public void remover(int id) {
        clientes.removeIf(cliente -> cliente.getId() == id);
    }

    /**
     * Busca um cliente pelo nome.
     * (Chamado por 'buscarCliente' no seu controller).
     */
    public Cliente buscarCliente(String nome) {
        for (Cliente cliente : clientes) {
            // Compara os nomes ignorando maiúsculas/minúsculas.
            if (cliente.getNome().equalsIgnoreCase(nome)) {
                return cliente;
            }
        }
        return null; // Retorna nulo se nenhum cliente for encontrado com esse nome.
    }
}