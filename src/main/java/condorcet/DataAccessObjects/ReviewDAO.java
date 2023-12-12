package condorcet.DataAccessObjects;

import condorcet.Interfaces.DAO;
import condorcet.Models.Entities.Client;
import condorcet.Models.Entities.Review;
import condorcet.Utility.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ReviewDAO implements DAO {
    @Override
    public Review findById(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Review review = session.get(Review.class, id);
        session.close();
        return review;
    }

    @Override
    public void saveEntity(Object entity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Review review = (Review) entity;
        Transaction tx1 = session.beginTransaction();
        session.save(review);
        tx1.commit();
        session.close();
    }

    @Override
    public List<Review> findAll() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        List<Review> reviews = (List<Review>)session.createQuery("from Review", Review.class).list();
        session.close();
        return reviews;
    }

    @Override
    public void deleteEntity(Object entity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Review review = (Review) entity;
        Transaction tr1 = session.beginTransaction();
        session.delete(review);
        tr1.commit();
        session.close();
    }

    @Override
    public void updateEntity(Object entity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Review review = (Review) entity;
        Transaction tr1 = session.beginTransaction();
        session.update(review);
        tr1.commit();
        session.close();
    }
}
