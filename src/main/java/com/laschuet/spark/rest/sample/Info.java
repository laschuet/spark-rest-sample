package com.laschuet.spark.rest.sample;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.spark.sql.SparkSession;

@Path("info")
public class Info {
    @Context
    private ServletContext context = null;

    @Path("version")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getSparkSessionVersion() {
        SparkSession sparkSession = (SparkSession) context.getAttribute("sparkSession");
        return sparkSession.version();
    }
}
