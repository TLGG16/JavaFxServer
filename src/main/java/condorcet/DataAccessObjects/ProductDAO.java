package condorcet.DataAccessObjects;

import condorcet.Interfaces.DAO;
import condorcet.Models.Entities.Client;
import condorcet.Models.Entities.Product;
import condorcet.Utility.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProductDAO implements DAO {
    @Override
    public Product findById(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Product product = session.get(Product.class, id);
        session.close();
        return product;
    }

    @Override
    public void saveEntity(Object entity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Product product = (Product) entity;
        Transaction tx1 = session.beginTransaction();
        session.save(product);
        tx1.commit();
        session.close();
    }

    @Override
    public List<Product> findAll() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        List<Product> products = (List<Product>)session.createQuery("from Product", Product.class).list();
        session.close();
        return products;
    }

    @Override
    public void deleteEntity(Object entity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Product product = (Product) entity;
        Transaction tr1 = session.beginTransaction();
        session.delete(product);
        tr1.commit();
        session.close();
    }

    @Override
    public void updateEntity(Object entity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Product product = (Product) entity;
        Transaction tr1 = session.beginTransaction();
        session.update(product);
        tr1.commit();
        session.close();
    }
}
