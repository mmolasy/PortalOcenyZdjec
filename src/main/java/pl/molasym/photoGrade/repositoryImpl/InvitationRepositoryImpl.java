package pl.molasym.photoGrade.repositoryImpl;

import pl.molasym.photoGrade.entities.Invitation;
import pl.molasym.photoGrade.entities.User;
import pl.molasym.photoGrade.repository.InvitationRepository;

import java.util.List;

/**
 * Created by moliq on 20.11.16.
 */
public class InvitationRepositoryImpl implements InvitationRepository {
    @Override
    public Invitation createNewInvitation(User userOne, User userTwo) {
        return null;
    }

    @Override
    public List<Invitation> getReceivedInvitations(User user) {
        return null;
    }

    @Override
    public List<Invitation> getSentInvitations(User user) {
        return null;
    }

    @Override
    public String getStatusOfInvitation(Long id) {
        return null;
    }

    @Override
    public void updateInvitation(Invitation invitation) {

    }
}
