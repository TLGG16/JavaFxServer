package condorcet.DataAccessObjects;

import condorcet.Interfaces.DAO;
import condorcet.Models.Entities.Client;
import condorcet.Models.Entities.Order;
import condorcet.Utility.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.PreparedStatement;
import java.util.List;

public class OrderDAO implements DAO {
    @Override
    public Order findById(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Order order = session.get(Order.class, id);
        session.close();
        return order;
    }

    @Override
    public void saveEntity(Object entity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Order order = (Order) entity;
        Transaction tx1 = session.beginTransaction();
        session.save(order);
        tx1.commit();
        session.close();
    }

    @Override
    public List<Order> findAll() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        List<Order> orders = (List<Order>)session.createQuery("from Order", Order.class).list();
        session.close();
        return orders;
    }

    @Override
    public void deleteEntity(Object entity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Order order = (Order) entity;
        Transaction tr1 = session.beginTransaction();
        session.delete(order);
        tr1.commit();
        session.close();
    }

    @Override
    public void updateEntity(Object entity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Order order = (Order) entity;
        Transaction tr1 = session.beginTransaction();
        session.update(order);
        tr1.commit();
        session.close();
    }
}
