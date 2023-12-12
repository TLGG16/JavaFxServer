package condorcet.Utility;

import condorcet.Models.Entities.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {
    public static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null){

            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Person.class);
            configuration.addAnnotatedClass(Client.class);
            configuration.addAnnotatedClass(Supplier.class);
            configuration.addAnnotatedClass(Product.class);
            configuration.addAnnotatedClass(Report.class);
            configuration.addAnnotatedClass(Review.class);
            configuration.addAnnotatedClass(Order.class);
            configuration.addAnnotatedClass(FinalOrder.class);
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            return sessionFactory;
        }else{
            return null;
        }

    }
}
