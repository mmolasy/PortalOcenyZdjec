package pl.molasym.photoGrade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import pl.molasym.photoGrade.entities.Invitation;
import pl.molasym.photoGrade.entities.Photo;
import pl.molasym.photoGrade.entities.User;
import pl.molasym.photoGrade.enums.Visibility;
import pl.molasym.photoGrade.exceptions.*;
import pl.molasym.photoGrade.service.InvitationService;
import pl.molasym.photoGrade.service.PhotoService;
import pl.molasym.photoGrade.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by moliq on 23.10.16.
 */

@SessionAttributes(value = "USER")
@Controller
@RequestMapping("/users")
public class UserPanelController {

    @Autowired
    private UserService userService;

    @Autowired
    private PhotoService photoService;

    @Autowired
    private InvitationService invitationService;

    @RequestMapping(value = "/addPhoto", method = RequestMethod.GET)
    public ModelAndView showAddPhoto(@ModelAttribute("newPhoto") Photo newPhoto, HttpServletResponse response, HttpSession session) {
        User user = (User) session.getAttribute("USER");
        if(user == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return new ModelAndView("redirect:/login");
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("newPhoto", new Photo());
        modelAndView.setViewName("addPhoto");

        return modelAndView;
    }

    @RequestMapping(value = "/addPhoto", method = RequestMethod.POST)
    public ModelAndView postAddPhoto(@RequestParam CommonsMultipartFile fileUpload, @RequestParam String description, @RequestParam String visibility, HttpServletResponse response, HttpSession session) {
        User user = (User) session.getAttribute("USER");
        if(user == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return new ModelAndView("redirect:/login");
        }

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
        try {
            photoService.addPhotoToUser(user, newPhoto);
        } catch (UserNotFoundException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return new ModelAndView("redirect:/users/notfound");
        } catch (ServerException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new ModelAndView("redirect:/logout");
        }


        modelAndView.setViewName("userprofile");
        modelAndView.addObject("user", user);

        List<Photo> photoList;
        try {
            photoList = photoService.getAllPhotoFromUser(user);
        } catch (UserNotFoundException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new ModelAndView("redirect:/users");
        }

        modelAndView.addObject("photoList", photoList);
        modelAndView.addObject("relationShip", "SESSION");
        return modelAndView;

    }

    @RequestMapping(value = "/invite/{id}", method = RequestMethod.POST)
    public ModelAndView inviteToFriends(@PathVariable String id, HttpServletResponse response, HttpSession session) {
        User user = (User) session.getAttribute("USER");
        if(user == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return new ModelAndView("redirect:/login");
        }
        User userToInvite;

        try {
            userToInvite = userService.getUserByUserId(Long.valueOf(id));
        } catch (UserNotFoundException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return new ModelAndView("redirect:/users/notfound");
        }
        try {
            invitationService.createNewInvitation(user, userToInvite);
        } catch (UserNotFoundException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return new ModelAndView("redirect:/users/notfound");

        } catch (ServerException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new ModelAndView("redirect:/logout");

        } catch (UserAlreadyFriends userAlreadyFriends) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            ModelAndView model = new ModelAndView();
            model.addObject("error", "Already friends");
            model.setViewName("forward:/users/"+userToInvite.getUserId());
            return model;

        } catch (InvitationAlreadySent invitationAlreadySent) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            ModelAndView model = new ModelAndView();
            model.addObject("error", "Invitation already sent");
            model.setViewName("forward:/users/"+userToInvite.getUserId());
            return model;
        } catch (InvitationAlreadyAccepted invitationAlreadyAccepted) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            ModelAndView model = new ModelAndView();
            model.addObject("error", "Already friends");
            model.setViewName("forward:/users/"+userToInvite.getUserId());
            return model;
        }
        ModelAndView model = new ModelAndView();
        model.addObject("relationShip", "FRIENDS");
        model.setViewName("redirect:/users/"+userToInvite.getUserId());
        return model;
    }

    @RequestMapping(value = "/invitations", method = RequestMethod.GET)
    public ModelAndView getInvitations(HttpServletResponse response, HttpSession session) {
        User user = (User) session.getAttribute("USER");
        if(user == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return new ModelAndView("redirect:/login");
        }
        List<Invitation> list = invitationService.getInvitationToUser(user);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("invitations");
        modelAndView.addObject("invitations", list);

        return modelAndView;
    }

    @RequestMapping(value = "/acceptInvitation/{id}", method = RequestMethod.POST)
    public ModelAndView acceptInvitation(@PathVariable String id, HttpServletResponse response, HttpSession session) {
        User user = (User) session.getAttribute("USER");
        if(user == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return new ModelAndView("redirect:/login");
        }
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
            return new ModelAndView("redirect:/users/notfound");
        }
        response.setStatus(HttpServletResponse.SC_CREATED);
        return new ModelAndView("redirect:/users/"+invitation.getFrom().getUserId());

    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView showUserProfile(@PathVariable Long id, HttpServletResponse response, HttpSession session) throws PhotoNotFound {
        User user = (User) session.getAttribute("USER");
        if(user == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return new ModelAndView("redirect:/login");
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userprofile");

        User userById = null;
        try {
            userById = userService.getUserByUserId(id);
        } catch (UserNotFoundException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return new ModelAndView("redirect:/users/notfound");
        }

        List<Photo> photoList;
        try {
            photoList = photoService.getAllPhotoFromUser(userById);
            for(Photo photo: photoList) {
                    photo.setCurrentUserGrade(photoService.getGradeByPhotoAndUser(photo, user));
            }
        } catch (UserNotFoundException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new ModelAndView("redirect:/users");
        }
        userById.setPhotosQuantity(photoList.size());
        if(userById.getUserId().equals(user.getUserId()))
        {
            modelAndView.addObject("user", userById);
            modelAndView.addObject("photoList", photoList);
            modelAndView.addObject("relationShip", "SESSION");
        } else try {
            if (userService.areFriends(user, userById)) {
                modelAndView.addObject("user", userById);
                modelAndView.addObject("photoList", photoList);
                modelAndView.addObject("relationShip", "FRIEND");
            }
            else{
                List<Photo> photoListNotFriend;
                try {
                    photoListNotFriend = photoService.getPhotoFromUserByVisibility(userById, Visibility.PUBLIC);
                    for(Photo photo: photoListNotFriend) {
                        photo.setCurrentUserGrade(photoService.getGradeByPhotoAndUser(photo, user));
                    }
                } catch (UserNotFoundException e) {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    return new ModelAndView("redirect:/users");
                }
                userById.setPhotosQuantity(photoListNotFriend.size());
                modelAndView.addObject("user", userById);
                modelAndView.addObject("photoList", photoListNotFriend);
                modelAndView.addObject("relationShip", "NOTFRIEND");
            }
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        return modelAndView;
    }

    @Transactional
    @RequestMapping(value = "/{photoId}/grade/{value}", method = RequestMethod.GET)
    public ModelAndView addGradeForPhoto(@PathVariable Long photoId, @PathVariable String value, HttpServletResponse response, HttpSession session) throws Exception {
        User user = (User) session.getAttribute("USER");
        if(user == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return new ModelAndView("redirect:/login");
        }
        photoService.addPhotoGrade(user, photoId, value );
        return new ModelAndView("redirect:/users/"+ photoService.getPhotoById(photoId).getUser().getUserId());
    }

    @RequestMapping(value = "/{photoId}/remove", method = RequestMethod.POST)
    public ModelAndView addGradeForPhoto(@PathVariable Long photoId, HttpServletResponse response, HttpSession session) throws Exception {
        User user = (User) session.getAttribute("USER");
        if(user == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return new ModelAndView("redirect:/login");
        }
        photoService.removePhoto(user, photoId);
        return new ModelAndView("redirect:/users/"+user.getUserId());
    }

    @RequestMapping(value = "/notfound", method = RequestMethod.GET)
    public ModelAndView notFoundView(){
        return new ModelAndView("userNotFound");
    }

//    @RequestMapping(value = "/grade/{photoId}", method = RequestMethod.GET)
//    public void getGradesByPhoto(@PathVariable String photoId) throws PhotoNotFound {
//        photoService.getPhotoGrades(Long.valueOf(photoId));
//    }

    @RequestMapping(value = "{userId}/friends", method = RequestMethod.GET)
    public ModelAndView getUserFriends(@PathVariable Long userId, HttpServletResponse response, HttpSession session) throws PhotoNotFound {
        User user = (User) session.getAttribute("USER");
        if(user == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return new ModelAndView("redirect:/login");
        }
        ModelAndView modelAndView = new ModelAndView();
        List<User> friends = null;
        try {
            friends = userService.getUserFriends(userId);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        modelAndView.addObject("userId", userId);
        modelAndView.addObject("friends", friends);
        modelAndView.setViewName("friends");
        return modelAndView;
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public ModelAndView getUsersByEmail(@RequestParam(value="mail", required=true) String mail, HttpServletResponse response, HttpSession session) throws PhotoNotFound {
        User user = (User) session.getAttribute("USER");
        if(user == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return new ModelAndView("redirect:/login");
        }
        System.out.println(mail);
        User userByEmail = null;
        try {
            userByEmail = userService.getUserByEmail(mail);
        } catch (UserNotFoundException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return new ModelAndView("redirect:/users/notfound");
        }

        return new ModelAndView("redirect:/users/"+userByEmail.getUserId());
    }
    @RequestMapping(value = "me", method = RequestMethod.GET)
    public ModelAndView getMyProfile(HttpServletResponse response, HttpSession session) {
        User user = (User) session.getAttribute("USER");
        if(user == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return new ModelAndView("redirect:/login");
        }
        return new ModelAndView("redirect:/users/"+user.getUserId());
    }
    @RequestMapping(value="**",method = RequestMethod.GET)
    public ModelAndView getAnythingelse() {
        return new ModelAndView("userNotFound");
    }
}
