package condorcet.DataAccessObjects;

import condorcet.Interfaces.DAO;
import condorcet.Models.Entities.Client;
import condorcet.Models.Entities.Report;
import condorcet.Utility.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ReportDAO implements DAO {
    @Override
    public Report findById(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Report report = session.get(Report.class, id);
        session.close();
        return report;
    }

    @Override
    public void saveEntity(Object entity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Report report = (Report) entity;
        Transaction tx1 = session.beginTransaction();
        session.save(report);
        tx1.commit();
        session.close();
    }

    @Override
    public List<Report> findAll() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        List<Report> reports = (List<Report>)session.createQuery("from Report", Report.class).list();
        session.close();
        return reports;
    }

    @Override
    public void deleteEntity(Object entity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Report report = (Report) entity;
        Transaction tr1 = session.beginTransaction();
        session.delete(report);
        tr1.commit();
        session.close();
    }

    @Override
    public void updateEntity(Object entity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Report report = (Report) entity;
        Transaction tr1 = session.beginTransaction();
        session.update(report);
        tr1.commit();
        session.close();
    }
}
