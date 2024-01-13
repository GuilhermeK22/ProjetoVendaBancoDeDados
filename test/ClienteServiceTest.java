import dao.ClienteDAOMock;
import dao.IClienteDAO;
import domain.Cliente;
import exceptions.DAOException;
import exceptions.TipoChaveNaoEncontradaException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import services.ClienteService;
import services.IClienteService;

public class ClienteServiceTest {
    private IClienteService clienteService;
    private Cliente cliente;
    public ClienteServiceTest() {
        IClienteDAO dao = new ClienteDAOMock();
        clienteService = new ClienteService(dao);
    }
    @Before
    public void init() {
        cliente = new Cliente();
        cliente.setCpf(123456789L);
        cliente.setNome("Guilherme");
        cliente.setCidade("Lagoa Vermelha");
        cliente.setEnd("Endereco");
        cliente.setEstado("LV");
        cliente.setNumero(100);
        cliente.setTel(99999999L);
        cliente.setEmail("guilherme@guilherme");
    }
    @Test
    public void pesquisarCliente() throws DAOException {
        Cliente clienteConsultado = clienteService.buscarPorCPF(cliente.getCpf());
        Assert.assertNotNull(clienteConsultado);
    }
    @Test
    public void salvarCliente() throws TipoChaveNaoEncontradaException, DAOException {
        Boolean retorno = clienteService.cadastrar(cliente);
        Assert.assertTrue(retorno);
    }
    @Test
    public void excluirCliente() throws DAOException {
        clienteService.excluir(cliente.getCpf());
    }
    @Test
    public void alterarCliente() throws TipoChaveNaoEncontradaException, DAOException {
        cliente.setNome("Guilherme Kappaun");
        clienteService.alterar(cliente);
        Assert.assertEquals("Guilherme Kappaun", cliente.getNome());
    }
}
