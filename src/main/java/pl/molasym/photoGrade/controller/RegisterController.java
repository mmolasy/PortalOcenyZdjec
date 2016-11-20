package pl.molasym.photoGrade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.molasym.photoGrade.dto.UserLoginDTO;
import pl.molasym.photoGrade.entities.User;
import pl.molasym.photoGrade.exceptions.UserAlreadyRegistered;
import pl.molasym.photoGrade.exceptions.UserException;
import pl.molasym.photoGrade.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;

/**
 * Created by moliq on 23.10.16.
 */

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView showRegisterFormular(@ModelAttribute("newUser") User newUser, HttpServletResponse response, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("newUser", new User());
        modelAndView.setViewName("registerFormular");

        return modelAndView;
    }


    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelAndView sentRegisterFormular(@ModelAttribute("newUser") @Valid User newUser, BindingResult result, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws UserException {

        ModelAndView modelAndView = new ModelAndView();

//        if (result.hasErrors())
//        {
//            modelAndView.addObject("newUser", newUser);
//            modelAndView.setViewName("registerFormular");
//            return modelAndView;
//        }

        System.out.println(newUser);
        User user = new User();
        user.setNickName(newUser.getNickName());
        user.setPassword(newUser.getPassword());
        user.setBirthDate(newUser.getBirthDate());
        user.setEmail(newUser.getEmail());
        user.setCreatedDate(new Date());


        try {
            userService.registerNewUser(user);
        } catch (UserAlreadyRegistered e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            modelAndView.addObject("newUser", user);
            modelAndView.addObject("error","E-mail already exists in DB");
            modelAndView.setViewName("registerFormular");
            return  modelAndView;
        }

        response.setStatus(HttpServletResponse.SC_CREATED);
        modelAndView.addObject("event", "Account has been created. Please log in");
        modelAndView.addObject("loginDto", new UserLoginDTO());
        modelAndView.setViewName("redirect:/login");
        return modelAndView;

    }

}
