package pl.molasym.photoGrade.repository;

import pl.molasym.photoGrade.entities.Invitation;
import pl.molasym.photoGrade.entities.User;

import java.util.List;

/**
 * Created by moliq on 20.11.16.
 */
public interface InvitationRepository {
    public Invitation createNewInvitation(User userOne, User userTwo);
    public String getStatusOfInvitation(Long id);
    public void acceptInvitation(Invitation invitation);
    public Invitation getInvitationByUsers(User userFrom, User userTo);
    public Invitation getInvitationById(Long id);
    public List<Invitation> getInvitationToUser(User user);


}
