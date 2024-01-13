import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ClienteDAOTest.class, ClienteServiceTest.class, ProdutoServiceTest.class, ProdutoDAOTest.class, VendaDAOTest.class})
public class AllTests {
}
