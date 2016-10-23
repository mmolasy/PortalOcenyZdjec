package pl.molasym.photoGrade.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.molasym.photoGrade.entities.User;

import javax.validation.Valid;

/**
 * Created by moliq on 23.10.16.
 */

@Controller
@RequestMapping("/register")
public class RegisterController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showRegisterFormular(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registerFormular");

        return modelAndView;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView sentRegisterFormular(@Valid User user, BindingResult result){
        ModelAndView modelAndView = new ModelAndView();

        if (result.hasErrors())
        {
            modelAndView.setViewName("");
            return modelAndView;
        }



        return modelAndView;
    }

}
