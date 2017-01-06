package pl.molasym.photoGrade.repositoryImpl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import pl.molasym.photoGrade.config.HibernateUtil;
import pl.molasym.photoGrade.entities.Grade;
import pl.molasym.photoGrade.entities.Photo;
import pl.molasym.photoGrade.entities.User;
import pl.molasym.photoGrade.enums.Visibility;
import pl.molasym.photoGrade.repository.PhotoRepository;
import pl.molasym.photoGrade.sql.GradeSQL;
import pl.molasym.photoGrade.sql.PhotoFilesSQL;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void addPhotoGrade(Photo photo, Grade grade){
        Session session = sessionFactory.openSession();
        Photo founded = getPhotoById(photo.getPhotoId());
        founded.getGrades().add(grade);
        session.getTransaction().begin();
        session.merge(founded);
        session.getTransaction().commit();
        session.close();
    }

    public void updatePhotoGrade(Photo photo, Grade newGrade){
        Session session = sessionFactory.openSession();
        Photo founded = getPhotoById(photo.getPhotoId());
        System.out.println(founded.getGrades());
        Grade grade = founded.getGrades().stream().filter(x -> x.getUser().getUserId().equals(newGrade.getUser().getUserId())).findFirst().get();
        grade.setValue(newGrade.getValue());
        session.getTransaction().begin();
        session.merge(founded);
        session.getTransaction().commit();
        session.close();
    }

    public void removePhotoGrade(Photo photo, Grade newGrade){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(GradeSQL.removeGradeByPhotoAndUser);
        query.setParameter("photoo", photo);
        query.setParameter("userr", newGrade.getUser());
        session.getTransaction().begin();
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
    public Map<Integer, Long> getGradesByPhoto(Photo photo){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(GradeSQL.getGradesByPhotoId);
        query.setParameter("photoo", photo);

        List<List<Object>> permission= query.setResultTransformer(Transformers.TO_LIST).list();
        HashMap<Integer,Long> map= new HashMap<>();
        for(List<Object> x: permission){
            map.put((Integer) x.get(0), (Long)x.get(1));
        }
        session.close();
        return map;
    }
    public Integer getGradeByPhotoAndUser(Photo photo, User user){
        Integer result=null;
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(GradeSQL.getGradeByPhotoAndUser);
        query.setParameter("photoo", photo);
        query.setParameter("userr", user);
        result = (Integer) query.uniqueResult();
        session.close();
        return result;
    }
    public void removePhoto(Photo photo){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.delete(photo);
        session.getTransaction().commit();
        session.close();
    }



}
