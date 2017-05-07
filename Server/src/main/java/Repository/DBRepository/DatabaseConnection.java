package Repository.DBRepository;

import Domain.AgeCategory;
import Domain.Participant;
import Domain.Trial;
import Domain.User;
import Repository.Exceptions.RepositoryException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by andrei on 2017-05-07.
 */
public class DatabaseConnection {
    private static SessionFactory sessionFactory = null;

    public static Session newSession() throws RepositoryException {
        if (sessionFactory == null) establishConnection();
        return sessionFactory.openSession();
    }

    private static void establishConnection() {
        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Trial.class)
                .addAnnotatedClass(AgeCategory.class)
                .addAnnotatedClass(Participant.class)
                .buildSessionFactory();
    }

    public static void closeConnection(){
        if(sessionFactory == null)
            return;
        sessionFactory.close();
    }
}
