/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016-present Lars Schütz
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.laschuet.spark.rest.sample;

import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
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
    public LinearRegressionModelBean run() {
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

        return new LinearRegressionModelBean(linearRegressionModel.uid(), coefficients.toArray(),
                intercept);
    }
}
