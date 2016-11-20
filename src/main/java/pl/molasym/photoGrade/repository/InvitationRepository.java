package pl.molasym.photoGrade.repository;

import pl.molasym.photoGrade.entities.Invitation;
import pl.molasym.photoGrade.entities.User;

import java.util.List;

/**
 * Created by moliq on 20.11.16.
 */
public interface InvitationRepository {
    public Invitation createNewInvitation(User userOne, User userTwo);
    public List<Invitation> getReceivedInvitations(User user);
    public List<Invitation> getSentInvitations(User user);
    public String getStatusOfInvitation(Long id);
    public void updateInvitation(Invitation invitation);
}
