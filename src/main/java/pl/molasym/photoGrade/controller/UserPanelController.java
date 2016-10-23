package pl.molasym.photoGrade.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by moliq on 23.10.16.
 */

@Controller
@RequestMapping("/users")
public class UserPanelController {

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
