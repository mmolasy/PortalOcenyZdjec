package pl.molasym.photoGrade.serviceimpl;

import org.eclipse.jetty.server.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.molasym.photoGrade.entities.Invitation;
import pl.molasym.photoGrade.entities.User;
import pl.molasym.photoGrade.exceptions.*;
import pl.molasym.photoGrade.repository.InvitationRepository;
import pl.molasym.photoGrade.repository.UserRepository;
import pl.molasym.photoGrade.service.InvitationService;
import pl.molasym.photoGrade.service.UserService;

import java.util.List;

/**
 * Created by moliq on 20.11.16.
 */

@Service
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

        for(Invitation invitation: userTo.getReceivedInvitations()) {
         if(invitation.getFrom().getUserId().equals(userFrom.getUserId()))
            throw new InvitationAlreadySent();
        }
        System.out.println(userFrom.getReceivedInvitations());
        System.out.println(userTo.getReceivedInvitations());

        for(Invitation invitation: userFrom.getReceivedInvitations())
        if(invitation.getFrom().getUserId().equals(userTo.getUserId())) {
            Invitation invitationx = invitationRepository.getInvitationByUsers(userTo, userFrom);
            if(getStatusOfInvitation(invitationx.getId()).equals("ACCEPTED"))
                throw new InvitationAlreadyAccepted();
            acceptInvitation(invitationx);
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
    public Invitation getInvitationById(Long id){
        return invitationRepository.getInvitationById(id);
    }
    public List<Invitation> getInvitationToUser(User user){
        return invitationRepository.getInvitationToUser(user);
    }

}
