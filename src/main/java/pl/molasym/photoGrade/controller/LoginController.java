package pl.molasym.photoGrade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.molasym.photoGrade.dto.UserLoginDTO;
import pl.molasym.photoGrade.entities.User;
import pl.molasym.photoGrade.exceptions.UserWrongCredentials;
import pl.molasym.photoGrade.service.UserService;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by root on 22.10.16.
 */

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    UserService userServiceImpl;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView showLoginPage(@ModelAttribute(value = "loginDto") @Valid UserLoginDTO loginDto, BindingResult result, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) session.getAttribute("USER");
        if(user == null){
            System.out.println("Not logged");
            modelAndView.addObject("loginDto", new UserLoginDTO());
            modelAndView.setViewName("loginView");
        }
        else{
            return new ModelAndView("redirect:/users/me");
        }
        return modelAndView;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelAndView doLogin(HttpSession session, @ModelAttribute(value = "loginDto") @Valid UserLoginDTO loginDto, BindingResult result){

        ModelAndView modelAndView = new ModelAndView();

        if(result.hasErrors()){
            System.out.println(result.getAllErrors());
            modelAndView.addObject("error","error");
            modelAndView.addObject("loginDto", loginDto);
            modelAndView.setViewName("loginView");
            return modelAndView;
        }

        User user;

        try {
            user = userServiceImpl.validateLoginUser(loginDto.getEmail(), loginDto.getPassword());
        } catch (UserWrongCredentials userWrongCredentials) {
            modelAndView.addObject("error", "Wrong credentials, please try again");
            modelAndView.addObject("loginDto", loginDto);
            modelAndView.setViewName("loginView");
            return modelAndView;
        }

        session.setAttribute("USER", user);
        return new ModelAndView("redirect:/users/me");

    }

    @RequestMapping("/logout")
    public String doLogout(HttpSession session){
        User u = (User) session.getAttribute("USER");
        if(u != null)
            session.invalidate();

        return "forward:/login";
    }


}
