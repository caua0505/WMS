package Repository;

import Model.Produto;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ProdutoRepository {
    private static List<Produto> produtos = new ArrayList<>();
    private static AtomicInteger proximoId = new AtomicInteger(1);

    public void adicionar(Produto produto) {
        produto.setId(proximoId.getAndIncrement()); // Define um ID numérico único.
        produtos.add(produto);
    }

    public List<Produto> listarTodos() {
        return produtos;
    }

    public Produto buscarPorId(int id) {
        for (Produto p : produtos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public void atualizar(Produto produto) {
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId() == produto.getId()) {
                produtos.set(i, produto);
                return;
            }
        }
    }

    public void remover(int id) {
        produtos.removeIf(produto -> produto.getId() == id);
    }
}