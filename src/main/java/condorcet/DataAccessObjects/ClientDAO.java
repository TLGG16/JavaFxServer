package condorcet.DataAccessObjects;
import java.util.List;

import condorcet.Interfaces.DAO;
import condorcet.Models.Entities.Client;
import condorcet.Models.Entities.Person;
import condorcet.Utility.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ClientDAO implements DAO {
    @Override
    public Client findById(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Client client = session.get(Client.class, id);
        session.close();
        return client;
    }

    @Override
    public void saveEntity(Object entity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Client client = (Client) entity;
        Transaction tx1 = session.beginTransaction();
        session.save(client);
        tx1.commit();
        session.close();
    }

    @Override
    public List<Client> findAll() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        List<Client> clients = (List<Client>)session.createQuery("from Client", Client.class).list();
        session.close();
        return clients;
    }

    @Override
    public void deleteEntity(Object entity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Client client = (Client) entity;
        Transaction tr1 = session.beginTransaction();
        session.delete(client);
        tr1.commit();
        session.close();
    }

    @Override
    public void updateEntity(Object entity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Client client = (Client) entity;
        Transaction tr1 = session.beginTransaction();
        session.update(client);
        tr1.commit();
        session.close();
    }
}
