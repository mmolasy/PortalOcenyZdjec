package pl.molasym.photoGrade.serviceimpl;

import org.eclipse.jetty.server.Server;
import org.springframework.beans.factory.annotation.Autowired;
import pl.molasym.photoGrade.entities.Invitation;
import pl.molasym.photoGrade.entities.User;
import pl.molasym.photoGrade.exceptions.*;
import pl.molasym.photoGrade.repository.InvitationRepository;
import pl.molasym.photoGrade.repository.UserRepository;
import pl.molasym.photoGrade.service.InvitationService;
import pl.molasym.photoGrade.service.UserService;

/**
 * Created by moliq on 20.11.16.
 */
public class InvitationServiceImpl implements InvitationService {

    @Autowired
    private InvitationRepository invitationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Override
    public void createNewInvitation(User userOne, User userTwo) throws UserNotFoundException, ServerException, UserAlreadyFriends, InvitationAlreadySent, InvitationAlreadyAccepted {
        if(userOne == null || userTwo == null)
            throw new ServerException();

        User userFrom = userRepository.getUserByUserId(userOne.getUserId());
        User userTo = userRepository.getUserByUserId(userTwo.getUserId());
        if(userFrom == null || userTo == null)
            throw new UserNotFoundException();

        if(userService.areFriends(userFrom, userTo))
            throw new UserAlreadyFriends();

        if(userFrom.getReceivedInvitations().stream().anyMatch(x -> x.equals(userTo)))
            throw new InvitationAlreadySent();

        if(userFrom.getReceivedInvitations().stream().anyMatch(x -> x.equals(userTo))) {
            Invitation invitation = invitationRepository.getInvitationByUsers(userFrom, userTo);
            if(getStatusOfInvitation(invitation.getId()).equals("ACCEPTED"))
                throw new InvitationAlreadyAccepted();
            acceptInvitation(invitation);
            return;
        }
        invitationRepository.createNewInvitation(userFrom, userTo);
    }

    @Override
    public String getStatusOfInvitation(Long id) {
        return invitationRepository.getStatusOfInvitation(id);
    }

    @Override
    public void acceptInvitation(Invitation invitation) throws InvitationAlreadyAccepted {
        Invitation invitationFound = invitationRepository.getInvitationById(invitation.getId());
        if(invitationFound.getStatus().equals("ACCEPTED"))
            throw new InvitationAlreadyAccepted();

        invitationRepository.acceptInvitation(invitationFound);

    }
}
