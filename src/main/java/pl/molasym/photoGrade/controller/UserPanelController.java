package pl.molasym.photoGrade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import pl.molasym.photoGrade.entities.Invitation;
import pl.molasym.photoGrade.entities.Photo;
import pl.molasym.photoGrade.entities.User;
import pl.molasym.photoGrade.enums.Visibility;
import pl.molasym.photoGrade.exceptions.*;
import pl.molasym.photoGrade.repository.PhotoRepository;
import pl.molasym.photoGrade.repository.UserRepository;
import pl.molasym.photoGrade.service.InvitationService;
import pl.molasym.photoGrade.service.PhotoService;
import pl.molasym.photoGrade.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by moliq on 23.10.16.
 */

@SessionAttributes(value = "USER")
@Controller
@RequestMapping("/users")
public class UserPanelController {

    @Autowired
    private UserService userDAO;

    @Autowired
    private PhotoService photoDAO;

    @Autowired
    private InvitationService invitationService;

    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public ModelAndView showMyProfile(HttpServletResponse response, HttpSession session) {
        User user = (User) session.getAttribute("USER");
        if(user == null)
            return new ModelAndView("redirect:/login");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userprofile");
        modelAndView.addObject("user", user);
        System.out.println(photoDAO.getPhotoFromUserByVisibility(user, Visibility.PUBLIC));
        modelAndView.addObject("photoList", photoDAO.getPhotoFromUserByVisibility(user, Visibility.PUBLIC));
        return modelAndView;
    }

    @RequestMapping(value = "/addPhoto", method = RequestMethod.GET)
    public ModelAndView showAddPhoto(@ModelAttribute("newPhoto") Photo newPhoto, HttpServletResponse response, HttpSession session) {
        User user = (User) session.getAttribute("USER");
        if(user == null)
            return new ModelAndView("redirect:/login");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("newPhoto", new Photo());
        modelAndView.setViewName("addPhoto");

        return modelAndView;
    }

    @RequestMapping(value = "/addPhoto", method = RequestMethod.POST)
    public ModelAndView postAddPhoto(@RequestParam CommonsMultipartFile fileUpload, @RequestParam String description, @RequestParam String visibility, HttpServletResponse response, HttpSession session) {
        User user = (User) session.getAttribute("USER");
        if(user == null)
            return new ModelAndView("redirect:/login");

        ModelAndView modelAndView = new ModelAndView();

        Photo newPhoto = new Photo();

        newPhoto.setImage(fileUpload.getBytes());
        newPhoto.setDescription(description);
        newPhoto.setVisibility(Visibility.valueOf(visibility).name());
        newPhoto.setCreatedDate(new Date());
        newPhoto.setUser(user);
        newPhoto.setImageName(fileUpload.getOriginalFilename());
        user.getPhotos().add(newPhoto);
        user.setPhotosQuantity(user.getPhotosQuantity()+1);
        photoDAO.addPhotoToUser(user, newPhoto);


        modelAndView.setViewName("userprofile");
        modelAndView.addObject("user", user);
        System.out.println(photoDAO.getPhotoFromUserByVisibility(user, Visibility.PUBLIC));
        modelAndView.addObject("photoList", photoDAO.getPhotoFromUserByVisibility(user, Visibility.PUBLIC));
        return modelAndView;

    }

    @RequestMapping(value = "/invite/{id}", method = RequestMethod.POST)
    public ModelAndView inviteToFriends(@PathVariable String id, HttpServletResponse response, HttpSession session) {
        User user = (User) session.getAttribute("USER");
        if(user == null)
            return new ModelAndView("redirect:/login");

        User userToInvite;

        try {
            userToInvite = userDAO.getUserByUserId(Long.valueOf(id));
        } catch (UserNotFoundException e) {
            System.out.println("UserNotFound");
            return new ModelAndView("redirect:/users/me");
        }
        try {
            invitationService.createNewInvitation(user, userToInvite);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            System.out.println("UserNotFound");

        } catch (ServerException e) {
            e.printStackTrace();
            System.out.println("ServerException");

        } catch (UserAlreadyFriends userAlreadyFriends) {
            userAlreadyFriends.printStackTrace();
            System.out.println("UserAlreadyFriends");

        } catch (InvitationAlreadySent invitationAlreadySent) {
            invitationAlreadySent.printStackTrace();
            System.out.println("InvitationAlreadySent");

        } catch (InvitationAlreadyAccepted invitationAlreadyAccepted) {
            invitationAlreadyAccepted.printStackTrace();
            System.out.println("InvitationAlreadyAccepted");

        }


        return new ModelAndView("redirect:/users/"+id);
    }

    @RequestMapping(value = "/invitations", method = RequestMethod.GET)
    public ModelAndView getInvitations(HttpServletResponse response, HttpSession session) {
        User user = (User) session.getAttribute("USER");
        if(user == null)
            return new ModelAndView("redirect:/login");
        List<Invitation> list = invitationService.getInvitationToUser(user);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("invitations");
        modelAndView.addObject("invitations", list);

        return modelAndView;
    }

    @RequestMapping(value = "/acceptInvitation/{id}", method = RequestMethod.POST)
    public ModelAndView acceptInvitation(@PathVariable String id, HttpServletResponse response, HttpSession session) {
        User user = (User) session.getAttribute("USER");
        if(user == null)
            return new ModelAndView("redirect:/login");

        Invitation invitation = invitationService.getInvitationById(Long.valueOf(id));
        if(!invitation.getTo().getUserId().equals(user.getUserId())){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            System.out.println("No permission");
            return new ModelAndView("redirect:/users/me");
        }

        try {
            invitationService.acceptInvitation(invitation);
        } catch (InvitationAlreadyAccepted invitationAlreadyAccepted) {
            invitationAlreadyAccepted.printStackTrace();
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            System.out.println("Invitation not found");
            return new ModelAndView("redirect:/users/me");
        }
        response.setStatus(HttpServletResponse.SC_CREATED);
        return new ModelAndView("redirect:/users/"+invitation.getFrom().getUserId());

    }

    @RequestMapping(value = "/allPhotos", method = RequestMethod.GET)
    public ModelAndView showAllPublicPhotos() {
        return null;
    }

    @RequestMapping(value = "/friendPhotos", method = RequestMethod.GET)
    public ModelAndView showFriendsPhotos() {
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView showUserProfile(@PathVariable Long id, HttpServletResponse response, HttpSession session) {

        User user = (User) session.getAttribute("USER");
        if(user == null)
            return new ModelAndView("redirect:/login");

        User userToShow = null;
        try {
            userToShow = userDAO.getUserByUserId(id);
        } catch (UserNotFoundException e) {
            System.out.println("UserNotFound");
            return new ModelAndView("redirect:/users/me");
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userprofile");
        modelAndView.addObject("user", userToShow);
        modelAndView.addObject("photoList", photoDAO.getPhotoFromUserByVisibility(userToShow, Visibility.PUBLIC));
        return modelAndView;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showAllUsers() {
        return null;
    }

    @RequestMapping(value = "/{id}/photos/{photoId}", method = RequestMethod.GET)
    public ModelAndView showPhotoByIdAndUserId(){
        return null;
    }

    @RequestMapping(value = "/{id}/photos/{photoId}/comments", method = RequestMethod.POST)
    public ModelAndView addComment(){
        return null;
    }

}
