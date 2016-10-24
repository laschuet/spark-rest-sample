package com.laschuet.jersey.spark;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.spark.sql.SparkSession;

@Path("example")
public class Example {
    @Context
    private ServletContext context = null;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getSparkSessionInfo() {
        SparkSession sparkSession = (SparkSession) context.getAttribute("sparkSession");
        return sparkSession.version();
    }
}
