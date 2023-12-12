package condorcet.DataAccessObjects;

import condorcet.Interfaces.DAO;
import condorcet.Models.Entities.FinalOrder;
import condorcet.Models.Entities.Order;
import condorcet.Utility.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class FinalOrderDAO implements DAO {
    @Override
    public FinalOrder findById(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        FinalOrder order = session.get(FinalOrder.class, id);
        session.close();
        return order;
    }

    @Override
    public void saveEntity(Object entity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        FinalOrder order = (FinalOrder) entity;
        Transaction tx1 = session.beginTransaction();
        session.persist(order);
        tx1.commit();
        session.close();
    }

    @Override
    public List<FinalOrder> findAll() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        List<FinalOrder> orders = (List<FinalOrder>)session.createQuery("from FinalOrder", FinalOrder.class).list();
        session.close();
        return orders;
    }

    @Override
    public void deleteEntity(Object entity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        FinalOrder order = (FinalOrder) entity;
        Transaction tr1 = session.beginTransaction();
        session.delete(order);
        tr1.commit();
        session.close();
    }

    @Override
    public void updateEntity(Object entity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        FinalOrder order = (FinalOrder) entity;
        Transaction tr1 = session.beginTransaction();
        session.update(order);
        tr1.commit();
        session.close();
    }
}
