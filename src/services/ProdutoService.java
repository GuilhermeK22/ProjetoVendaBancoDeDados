package services;

import dao.IProdutoDAO;
import dao.generic.IGenericDAO;
import domain.Produto;
import services.generic.GenericService;

public class ProdutoService extends GenericService<Produto, String> implements IProdutoService {
    public ProdutoService(IProdutoDAO dao) {
        super(dao);
    }
}
