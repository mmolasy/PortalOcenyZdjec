package pl.molasym.photoGrade.repositoryImpl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.molasym.photoGrade.config.HibernateUtil;
import pl.molasym.photoGrade.entities.User;
import pl.molasym.photoGrade.exceptions.UserNotFoundException;
import pl.molasym.photoGrade.repository.UserRepository;
import pl.molasym.photoGrade.sql.UserInformationSQL;

/**
 * Created by moliq on 23.10.16.
 */
public class UserRepositoryImpl implements UserRepository {

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();


    public User getUserByUserId(long id) throws UserNotFoundException {
        Session session = sessionFactory.openSession();
        User user;
        session.getTransaction().begin();
        Query query = session.createQuery(UserInformationSQL.GET_USER_BY_ID);
        query.setParameter("id", id);
        user = (User) query.uniqueResult();
        if (user == null)
            throw new UserNotFoundException();
        session.close();

        return user;
    }

    public boolean validateLoginUser(String login, String password) {
        return false;
    }
}
