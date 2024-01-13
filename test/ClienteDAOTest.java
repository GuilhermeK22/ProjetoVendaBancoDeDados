import dao.ClienteDAO;
import dao.IClienteDAO;
import domain.Cliente;
import exceptions.DAOException;
import exceptions.MaisDeUmRegistroException;
import exceptions.TableException;
import exceptions.TipoChaveNaoEncontradaException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertTrue;

public class ClienteDAOTest {
    private IClienteDAO clienteDao;

    public ClienteDAOTest() {
        clienteDao = new ClienteDAO();
    }

    @After
    public void end() throws DAOException {
        Collection<Cliente> list = clienteDao.buscarTodos();
        list.forEach(cli -> {
            try {
                clienteDao.excluir(cli.getCpf());
            } catch (DAOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }
    //After faz a exclusao dos dados do banco de dados para cada teste

    @Test
    public void pesquisarCliente() throws MaisDeUmRegistroException, TableException, TipoChaveNaoEncontradaException, DAOException {
        Cliente cliente = new Cliente();
        cliente.setCpf(123456789L);
        cliente.setNome("Guilherme");
        cliente.setCidade("Lagoa Vermelha");
        cliente.setEnd("Endereco");
        cliente.setEstado("LV");
        cliente.setNumero(100);
        cliente.setTel(99999999L);
        cliente.setEmail("email@email");
        clienteDao.cadastrar(cliente);

        Cliente clienteConsultado = clienteDao.consultar(cliente.getCpf());
        Assert.assertNotNull(clienteConsultado);

        clienteDao.excluir(cliente.getCpf());
    }

    @Test
    public void salvarCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        Cliente cliente = new Cliente();
        cliente.setCpf(123456789L);
        cliente.setNome("Guilherme");
        cliente.setCidade("Lagoa Vermelha");
        cliente.setEnd("End");
        cliente.setEstado("RS");
        cliente.setNumero(100);
        cliente.setTel(99999999L);
        cliente.setEmail("email@email");
        Boolean retorno = clienteDao.cadastrar(cliente);
        Assert.assertTrue(retorno);

        Cliente clienteConsultado = clienteDao.consultar(cliente.getCpf());
        Assert.assertNotNull(clienteConsultado);

        clienteDao.excluir(cliente.getCpf());
    }


    @Test
    public void excluirCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        Cliente cliente = new Cliente();
        cliente.setCpf(123456789L);
        cliente.setNome("Guilherme");
        cliente.setCidade("Lagoa Vermelha");
        cliente.setEnd("Endereco");
        cliente.setEstado("LV");
        cliente.setNumero(100);
        cliente.setTel(99999999L);
        cliente.setEmail("email@email");
        Boolean retorno = clienteDao.cadastrar(cliente);
        Assert.assertTrue(retorno);

        Cliente clienteConsultado = clienteDao.consultar(cliente.getCpf());
        Assert.assertNotNull(clienteConsultado);

        clienteDao.excluir(cliente.getCpf());
        clienteConsultado = clienteDao.consultar(cliente.getCpf());
        Assert.assertNull(clienteConsultado);
    }

    @Test
    public void alterarCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        Cliente cliente = new Cliente();
        cliente.setCpf(123456789L);
        cliente.setNome("Guilherme");
        cliente.setCidade("Lagoa Vermelha");
        cliente.setEnd("Endereco");
        cliente.setEstado("LV");
        cliente.setNumero(100);
        cliente.setTel(99999999L);
        cliente.setEmail("email@email");
        Boolean retorno = clienteDao.cadastrar(cliente);
        Assert.assertTrue(retorno);

        Cliente clienteConsultado = clienteDao.consultar(cliente.getCpf());
        Assert.assertNotNull(clienteConsultado);

        clienteConsultado.setNome("Rodrigo Pires");
        clienteDao.alterar(clienteConsultado);

        Cliente clienteAlterado = clienteDao.consultar(clienteConsultado.getCpf());
        Assert.assertNotNull(clienteAlterado);
        Assert.assertEquals("Rodrigo Pires", clienteAlterado.getNome());

        clienteDao.excluir(cliente.getCpf());
        clienteConsultado = clienteDao.consultar(cliente.getCpf());
        Assert.assertNull(clienteConsultado);
    }

    @Test
    public void buscarTodos() throws TipoChaveNaoEncontradaException, DAOException {
        Cliente cliente = new Cliente();
        cliente.setCpf(123456789L);
        cliente.setNome("Guilherme");
        cliente.setCidade("Lagoa Vermelha");
        cliente.setEnd("Endereco");
        cliente.setEstado("LV");
        cliente.setNumero(100);
        cliente.setTel(99999999L);
        cliente.setEmail("email@email");
        Boolean retorno = clienteDao.cadastrar(cliente);
        Assert.assertTrue(retorno);

        Cliente cliente1 = new Cliente();
        cliente1.setCpf(00000001L);
        cliente1.setNome("Guilherme Kappaun");
        cliente1.setCidade("Florianopolis");
        cliente1.setEnd("End");
        cliente1.setEstado("SC");
        cliente1.setNumero(200);
        cliente1.setTel(1199999999L);
        cliente.setEmail("email1@email1");
        Boolean retorno1 = clienteDao.cadastrar(cliente1);
        Assert.assertTrue(retorno1);

        Collection<Cliente> list = clienteDao.buscarTodos();
        assertTrue(list != null);
        assertTrue(list.size() == 2);

        list.forEach(cli -> {
            try {
                clienteDao.excluir(cli.getCpf());
            } catch (DAOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        Collection<Cliente> list1 = clienteDao.buscarTodos();
        assertTrue(list1 != null);
        assertTrue(list1.size() == 0);
    }
}

