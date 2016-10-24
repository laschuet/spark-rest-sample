package com.laschuet.jersey.spark;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext servletContext = event.getServletContext();
        servletContext.setAttribute("sparkSession", new Object());
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
    }
}
