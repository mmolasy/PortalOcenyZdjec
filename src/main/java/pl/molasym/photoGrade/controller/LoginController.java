package pl.molasym.photoGrade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.molasym.photoGrade.VO.Login;
import pl.molasym.photoGrade.repository.UserRepository;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by root on 22.10.16.
 */

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    //@Autowired
    //UserService userServiceImpl;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showLoginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("loginView");

        return modelAndView;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView doLogin(@Valid Login login, BindingResult result){

        ModelAndView modelAndView = new ModelAndView();

        if(result.hasErrors()){
            return new ModelAndView("redirect:/login/");
        }


        return modelAndView;
    }


}
