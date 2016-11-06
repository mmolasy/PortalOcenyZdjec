package pl.molasym.photoGrade.config;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import pl.molasym.photoGrade.controller.LoginController;
import pl.molasym.photoGrade.controller.RegisterController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by moliq on 24.10.16.
 */
public class SessionValidator extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (!((((HandlerMethod)handler).getBean() instanceof LoginController) ||  ((HandlerMethod)handler).getBean() instanceof RegisterController)) {
            if (session == null || session.getAttribute("USER") == null) {
                throw new Exception("Invalid session please login");
            }
        }
        return true;
    }

}