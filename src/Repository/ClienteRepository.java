/*
 * Repositório de Clientes (CORRIGIDO).
 * Responsável por gerenciar a lista de clientes.
 */
package Repository;

import Model.Cliente;
import Model.GerenciadorArquivo; // Importa o gerenciador
import java.util.List;

public class ClienteRepository {

    // MUDANÇA: A lista não é mais 'static'
    private List<Cliente> clientes;

    // NOVO: Instância do gerenciador de arquivos
    private GerenciadorArquivo gerenciador;

    // NOVO: Construtor
    public ClienteRepository() {
        // Aponta para o GerenciadorArquivo no pacote Model
        this.gerenciador = new Model.GerenciadorArquivo();

        // MUDANÇA: A lista é preenchida com os dados do "clientes.txt"
        this.clientes = gerenciador.carregarClientes();
    }

    /**
     * Adiciona um novo cliente à lista e salva no arquivo.
     */
    public void adicionar(Cliente cliente) {
        clientes.add(cliente);
        // NOVO: Salva a lista inteira no TXT
        gerenciador.salvarClientes(clientes);
    }

    /**
     * Retorna a lista de todos os clientes cadastrados.
     */
    public List<Cliente> listar() {
        return clientes;
    }

    /**
     * Atualiza um cliente existente e salva no arquivo.
     */
    public void atulizar(int id, Cliente clienteAtualizado) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == id) {
                clienteAtualizado.setId(id);
                clientes.set(i, clienteAtualizado);
                // NOVO: Salva a lista inteira no TXT
                gerenciador.salvarClientes(clientes);
                return;
            }
        }
    }

    /**
     * Remove um cliente da lista e salva no arquivo.
     */
    public void remover(int id) {
        if (clientes.removeIf(cliente -> cliente.getId() == id)) {
            // NOVO: Salva a lista inteira no TXT
            gerenciador.salvarClientes(clientes);
        }
    }

    /**
     * Busca um cliente pelo nome.
     */
    public Cliente buscarCliente(String nome) {
        for (Cliente cliente : clientes) {
            if (cliente.getNome().equalsIgnoreCase(nome)) {
                return cliente;
            }
        }
        return null;
    }
}