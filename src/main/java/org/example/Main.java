package org.example;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class Main {

    public static void main(String[] args) throws LifecycleException {
        var tomcat = new Tomcat();
        tomcat.getConnector().setPort(8081);
        var tomcatContext = tomcat.addContext("", null);

        var context = new AnnotationConfigWebApplicationContext();
        context.setServletContext(tomcatContext.getServletContext());
        context.scan("org.example");
        context.refresh();

        var dispatcher = new DispatcherServlet(context);
        var dispatcherWrapper = Tomcat.addServlet(tomcatContext, "dispatcher", dispatcher);
        dispatcherWrapper.addMapping("/");
        dispatcherWrapper.setLoadOnStartup(1);

        tomcat.start();
    }
}