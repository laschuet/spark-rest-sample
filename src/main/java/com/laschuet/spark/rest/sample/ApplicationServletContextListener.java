package com.laschuet.spark.rest.sample;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.spark.sql.SparkSession;

public class ApplicationServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        SparkSession sparkSession = SparkSession.builder()
                .appName("")
                .config("spark.master", "local[4]")
                .getOrCreate();

        ServletContext servletContext = event.getServletContext();
        servletContext.setAttribute("sparkSession", sparkSession);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
    }
}
