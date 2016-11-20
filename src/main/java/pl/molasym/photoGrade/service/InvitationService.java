package pl.molasym.photoGrade.service;

import pl.molasym.photoGrade.entities.Invitation;
import pl.molasym.photoGrade.entities.User;
import pl.molasym.photoGrade.exceptions.*;

import java.util.List;

/**
 * Created by moliq on 20.11.16.
 */
public interface InvitationService {
    public void createNewInvitation(User userOne, User userTwo) throws UserNotFoundException, ServerException, UserAlreadyFriends, InvitationAlreadySent, InvitationAlreadyAccepted;
    public String getStatusOfInvitation(Long id);
    public void acceptInvitation(Invitation invitation) throws InvitationAlreadyAccepted;
    public Invitation getInvitationById(Long id);
    public List<Invitation> getInvitationToUser(User user);
}
