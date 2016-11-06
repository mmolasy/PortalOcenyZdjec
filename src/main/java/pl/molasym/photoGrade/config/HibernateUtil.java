package pl.molasym.photoGrade.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import pl.molasym.photoGrade.entities.Comment;
import pl.molasym.photoGrade.entities.Photo;
import pl.molasym.photoGrade.entities.User;

public class HibernateUtil {

	private static SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {

		try {
			Configuration configuration = new Configuration();

			configuration.addAnnotatedClass(User.class);
			configuration.addAnnotatedClass(Photo.class);
			configuration.addAnnotatedClass(Comment.class);

			return configuration.buildSessionFactory(new StandardServiceRegistryBuilder().build());

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("There was an error while creation session factory");
		}
	}


	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
