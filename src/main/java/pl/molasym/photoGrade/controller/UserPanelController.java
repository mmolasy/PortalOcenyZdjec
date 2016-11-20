package pl.molasym.photoGrade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import pl.molasym.photoGrade.entities.Photo;
import pl.molasym.photoGrade.entities.User;
import pl.molasym.photoGrade.enums.Visibility;
import pl.molasym.photoGrade.repository.PhotoRepository;
import pl.molasym.photoGrade.repository.UserRepository;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by moliq on 23.10.16.
 */

@SessionAttributes(value = "USER")
@Controller
@RequestMapping("/users")
public class UserPanelController {

    @Autowired
    private UserRepository userDAO;

    @Autowired
    private PhotoRepository photoDAO;

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

    @RequestMapping(value = "/allPhotos", method = RequestMethod.GET)
    public ModelAndView showAllPublicPhotos() {
        return null;
    }

    @RequestMapping(value = "/friendPhotos", method = RequestMethod.GET)
    public ModelAndView showFriendsPhotos() {
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView showUserProfile(long id) {
        return null;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showAllUsers() {
        return null;
    }

    @RequestMapping(value = "/{id}/photos/{photoId}", method = RequestMethod.GET)
    public ModelAndView showPhotoByIdAndUserId(){
        return null;
    }

    @RequestMapping(value = "/{id}/photos", method = RequestMethod.POST)
    public ModelAndView addNewPhoto(){
        return null;
    }

    @RequestMapping(value = "/{id}/photos/{photoId}/comments", method = RequestMethod.POST)
    public ModelAndView addComment(){
        return null;
    }

}
