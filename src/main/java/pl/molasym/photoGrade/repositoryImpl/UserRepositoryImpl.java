package pl.molasym.photoGrade.repositoryImpl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import pl.molasym.photoGrade.config.HibernateUtil;
import pl.molasym.photoGrade.entities.User;
import pl.molasym.photoGrade.exceptions.UserNotFoundException;
import pl.molasym.photoGrade.repository.UserRepository;
import pl.molasym.photoGrade.sql.UserInformationSQL;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by moliq on 23.10.16.
 */

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();


    public User getUserByUserId(long id) {
        Session session = sessionFactory.openSession();
        User user=null;
        session.getTransaction().begin();
        Query query = session.createQuery(UserInformationSQL.GET_USER_BY_ID);
        query.setParameter("id", id);
        user = (User) query.uniqueResult();
        session.close();

        return user;
    }

    public User validateLoginUser(String email, String password) {
        Session session = sessionFactory.openSession();
        User user = null;
        Query query = session.createQuery(UserInformationSQL.GET_USER_BY_EMAIL_AND_PASSWORD);
        query.setParameter("email", email);
        query.setParameter("password", password);
        user = (User) query.uniqueResult();
        session.close();
        return user;
    }

    public User getUserByEmail(String mail){
        Session session = sessionFactory.openSession();
        User user = null;
        Query query = session.createQuery(UserInformationSQL.GET_USER_BY_EMAIL);
        query.setParameter("mail", mail);
        user = (User) query.uniqueResult();
        session.close();
        return user;
    }

    public void registerNewUser(User user){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }
    public List<User> getUserFriends(User user){
        Session session = sessionFactory.openSession();
        List<User> friends=null;
        session.getTransaction().begin();
        Query query = session.createQuery(UserInformationSQL.GET_USER_FRIENDS);
        query.setParameter("user", user);
        friends = (List<User>) query.list();
        session.close();

        return friends;
    }
}
