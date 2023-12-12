package condorcet.Services;

import condorcet.DataAccessObjects.OrderDAO;
import condorcet.DataAccessObjects.ProductDAO;
import condorcet.Interfaces.Service;
import condorcet.Models.Entities.Order;
import condorcet.Models.Entities.Product;

import java.util.List;

public class ProductService implements Service<Product> {
    private static ProductDAO productDAO;

    public ProductService() {
        productDAO = new ProductDAO();
    }

    @Override
    public Product findEntity(int id) {
        Product product = productDAO.findById(id);
        return product;
    }

    @Override
    public void saveEntity(Product entity) {
        productDAO.saveEntity(entity);

    }

    @Override
    public List<Product> findAllEntities() {
        List<Product> products = productDAO.findAll();
        return products;
    }

    @Override
    public void deleteEntity(Product entity) {
        productDAO.deleteEntity(entity);
    }

    @Override
    public void updateEntity(Product entity) {
        productDAO.updateEntity(entity);
    }
}
