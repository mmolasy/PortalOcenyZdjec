package pl.molasym.photoGrade.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
 
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
 
public class WebAppInitializer implements WebApplicationInitializer {
 
    public void onStartup(ServletContext container) {
    	
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
 
        container.addListener(new ContextLoaderListener(rootContext));
 
        AnnotationConfigWebApplicationContext dispatcherServlet = new AnnotationConfigWebApplicationContext();
        dispatcherServlet.register(ApplicationConfig.class);
             
        ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", new DispatcherServlet(dispatcherServlet));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
         
    }
 
 }