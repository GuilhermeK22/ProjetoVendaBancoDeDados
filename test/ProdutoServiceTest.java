import dao.IProdutoDAO;
import dao.ProdutoDAOMock;
import domain.Produto;
import exceptions.DAOException;
import exceptions.TipoChaveNaoEncontradaException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import services.IProdutoService;
import services.ProdutoService;

import java.math.BigDecimal;

public class ProdutoServiceTest {
    private IProdutoService produtoService;
    private Produto produto;
    public ProdutoServiceTest() {
        IProdutoDAO dao = new ProdutoDAOMock();
        produtoService = new ProdutoService(dao);
    }
    @Before
    public void init() {
        produto = new Produto();
        produto.setCodigo("A1");
        produto.setDescricao("Produto 1");
        produto.setNome("Produto 1");
        produto.setValor(BigDecimal.TEN);
        produto.setPeso(2);
    }
    @Test
    public void pesquisar() throws DAOException {
        Produto produtor = this.produtoService.consultar(produto.getCodigo());
        Assert.assertNotNull(produtor);
    }
    @Test
    public void salvar() throws TipoChaveNaoEncontradaException, DAOException {
        Boolean retorno = produtoService.cadastrar(produto);
        Assert.assertTrue(retorno);
    }
    @Test
    public void excluir() throws DAOException {
        produtoService.excluir(produto.getCodigo());
    }
    @Test
    public void alterarCliente() throws TipoChaveNaoEncontradaException, DAOException {
        produto.setNome("Guilherme Kappaun");
        produtoService.alterar(produto);

        Assert.assertEquals("Guilherme Kappaun", produto.getNome());
    }
}
