package pl.molasym.photoGrade.repositoryImpl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import pl.molasym.photoGrade.config.HibernateUtil;
import pl.molasym.photoGrade.entities.Invitation;
import pl.molasym.photoGrade.entities.Photo;
import pl.molasym.photoGrade.entities.User;
import pl.molasym.photoGrade.repository.InvitationRepository;
import pl.molasym.photoGrade.sql.InvitationSQL;
import pl.molasym.photoGrade.sql.PhotoFilesSQL;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by moliq on 20.11.16.
 */
@Repository
public class InvitationRepositoryImpl implements InvitationRepository {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public Invitation createNewInvitation(User userFrom, User userTo) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Invitation invitation = new Invitation();
        invitation.setFrom(userFrom);
        invitation.setTo(userTo);
        invitation.setStatus("PENDING");
        userTo.getReceivedInvitations().add(invitation);
        session.update(userFrom);
        session.update(userTo);
        session.save(invitation);
        session.getTransaction().commit();
        session.close();

        return invitation;
    }


    @Override
    public String getStatusOfInvitation(Long id) {
        Session session = sessionFactory.openSession();
        String status;
        Query query = session.createQuery(InvitationSQL.getInvitationStatus);
        query.setParameter("invitationId", id);
        status = (String) query.uniqueResult();
        session.close();
        return status;
    }

    @Override
    public void acceptInvitation(Invitation invitation) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        invitation.setStatus("ACCEPTED");
        User userFrom = invitation.getFrom();
        User userTo = invitation.getTo();
        userFrom.getFriends().add(userTo);
        userTo.getReceivedInvitations().remove(invitation);
        userTo.getFriends().add(userFrom);
        session.update(userFrom);
        session.update(userTo);
        session.save(invitation);
        session.getTransaction().commit();
        session.close();
    }
    public Invitation getInvitationByUsers(User userFrom, User userTo){
        Session session = sessionFactory.openSession();
        Invitation invitation;
        Query query = session.createQuery(InvitationSQL.getInvitationByUsers);
        query.setParameter("userFrom", userFrom);
        query.setParameter("userTo", userTo);
        invitation = (Invitation) query.uniqueResult();
        session.close();
        return invitation;
    }
    public Invitation getInvitationById(Long id){
        Session session = sessionFactory.openSession();
        Invitation invitation;
        Query query = session.createQuery(InvitationSQL.getInvitationById);
        query.setParameter("invitationId", id);
        invitation = (Invitation) query.uniqueResult();
        session.close();
        return invitation;
    }



}
