package com.laschuet.spark.rest.sample;

import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.linalg.Vectors;
import org.apache.spark.ml.regression.LinearRegression;
import org.apache.spark.ml.regression.LinearRegressionModel;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

@Path("pipeline")
public class Pipeline {
    @Context
    ServletContext servletContext = null;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response run() {
        SparkSession sparkSession = (SparkSession) servletContext.getAttribute("sparkSession");

        List<Row> trainingData = Arrays.asList(
            RowFactory.create(Vectors.dense(0.0), 0.0),
            RowFactory.create(Vectors.dense(1.0), 1.0),
            RowFactory.create(Vectors.dense(2.0), 3.0),
            RowFactory.create(Vectors.dense(3.0), 3.0),
            RowFactory.create(Vectors.dense(4.0), 3.5)
        );
        StructType trainingDataSchema = new StructType(new StructField[] {
            new StructField("features", new VectorUDT(), false, Metadata.empty()),
            new StructField("label", DataTypes.DoubleType, false, Metadata.empty())
        });
        Dataset<Row> trainingDataset = sparkSession.createDataFrame(trainingData,
                trainingDataSchema);

        LinearRegression linearRegression = new LinearRegression();
        linearRegression.setFitIntercept(true);

        LinearRegressionModel linearRegressionModel = linearRegression.fit(trainingDataset);
        Vector coefficients = linearRegressionModel.coefficients();
        double intercept = linearRegressionModel.intercept();

        String jsonString = "{\"model\":\"" + linearRegressionModel.uid() + "\","
                    + "\"coefficients\":" + coefficients.toString() + ","
                    + "\"intercept\":" + intercept + "}";
        return Response.ok(jsonString).build();
    }
}
