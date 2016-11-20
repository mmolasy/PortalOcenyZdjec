package pl.molasym.photoGrade.repositoryImpl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import pl.molasym.photoGrade.config.HibernateUtil;
import pl.molasym.photoGrade.entities.Photo;
import pl.molasym.photoGrade.entities.User;
import pl.molasym.photoGrade.enums.Visibility;
import pl.molasym.photoGrade.repository.PhotoRepository;
import pl.molasym.photoGrade.sql.PhotoFilesSQL;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by moliq on 19.11.16.
 */

@Repository
@Transactional
public class PhotoRepositoryImpl implements PhotoRepository {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public void addPhotoToUser(User user,Photo photo) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.save(photo);
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    public List<Photo> getPhotoFromUserByVisibility(User user, Visibility visibility) {
        Session session = sessionFactory.openSession();
        List<Photo> result = new ArrayList<Photo>();
        Query query = session.createQuery(PhotoFilesSQL.PhotoFromUser);
        query.setParameter("user", user);
        query.setParameter("visibility", visibility.name());
        result = (List<Photo>) query.list();
        session.close();
        return result;
    }


    public Photo getPhotoById(Long id) {
        Session session = sessionFactory.openSession();
        Photo photo = null;
        Query query = session.createQuery(PhotoFilesSQL.PhotoById);
        query.setParameter("photoId", id);
        photo = (Photo) query.uniqueResult();
        session.close();
        return photo;
    }
    public List<Photo> getAllPhotoFromUser(User user){
        Session session = sessionFactory.openSession();
        List<Photo> result = new ArrayList<Photo>();
        Query query = session.createQuery(PhotoFilesSQL.AllPhotoFromUser);
        query.setParameter("user", user);
        result = (List<Photo>) query.list();
        session.close();
        return result;
    }
}
