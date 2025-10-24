/*
 * Repositório de Clientes.
 * Mesma lógica do ProdutoRepository.
 */
package Repository;

import Model.Cliente;
import Model.GerenciadorArquivo;
import java.util.List;

public class ClienteRepository {

    private List<Cliente> clientes; // Lista em memória
    private GerenciadorArquivo gerenciador;

    /**
     * Construtor.
     * Carrega os clientes do "clientes.txt" ao iniciar.
     */
    public ClienteRepository() {
        this.gerenciador = new Model.GerenciadorArquivo();
        this.clientes = gerenciador.carregarClientes();
    }

    /**
     * Adiciona um novo cliente à lista e salva no arquivo.
     */
    public void adicionar(Cliente cliente) {
        clientes.add(cliente); // Adiciona na memória

        // --- "COMMIT" (Objetivo 2): A GRAVAÇÃO ---
        gerenciador.salvarClientes(clientes); // Salva no TXT
    }

    /**
     * Atualiza um cliente existente e salva no arquivo.
     */
    public void atulizar(int id, Cliente clienteAtualizado) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == id) {
                clienteAtualizado.setId(id);
                clientes.set(i, clienteAtualizado); // Atualiza na memória

                // --- "COMMIT" (Objetivo 2): A GRAVAÇÃO ---
                gerenciador.salvarClientes(clientes); // Salva no TXT
                return;
            }
        }
    }

    /**
     * Remove um cliente da lista e salva no arquivo.
     */
    public void remover(int id) {
        if (clientes.removeIf(cliente -> cliente.getId() == id)) { // Remove da memória
            // --- "COMMIT" (Objetivo 2): A GRAVAÇÃO ---
            gerenciador.salvarClientes(clientes); // Salva no TXT
        }
    }

    // --- Métodos de Leitura ---

    public List<Cliente> listar() {
        return clientes;
    }

    public Cliente buscarCliente(String nome) {
        for (Cliente cliente : clientes) {
            if (cliente.getNome().equalsIgnoreCase(nome)) {
                return cliente;
            }
        }
        return null;
    }
}