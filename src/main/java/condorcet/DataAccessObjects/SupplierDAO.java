package condorcet.DataAccessObjects;

import condorcet.Interfaces.DAO;
import condorcet.Models.Entities.Client;
import condorcet.Models.Entities.Supplier;
import condorcet.Utility.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SupplierDAO implements DAO {
    @Override
    public Supplier findById(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Supplier supplier = session.get(Supplier.class, id);
        session.close();
        return supplier;
    }

    @Override
    public void saveEntity(Object entity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Supplier supplier = (Supplier) entity;
        Transaction tx1 = session.beginTransaction();
        session.save(supplier);
        tx1.commit();
        session.close();
    }

    @Override
    public List<Supplier> findAll() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        List<Supplier> suppliers = (List<Supplier>)session.createQuery("from Supplier", Supplier.class).list();
        session.close();
        return suppliers;
    }

    @Override
    public void deleteEntity(Object entity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Supplier supplier = (Supplier) entity;
        Transaction tr1 = session.beginTransaction();
        session.delete(supplier);
        tr1.commit();
        session.close();
    }

    @Override
    public void updateEntity(Object entity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Supplier supplier = (Supplier) entity;
        Transaction tr1 = session.beginTransaction();
        session.update(supplier);
        tr1.commit();
        session.close();
    }

}
