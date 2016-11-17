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

import javax.xml.bind.annotation.XmlRootElement;

/***/
@XmlRootElement
public class LinearRegressionModelBean {
    private String uid = null;
    double[] coefficients = null;
    private double intercept = 0.0;

    /***/
    public LinearRegressionModelBean() {
    }

    /***/
    public LinearRegressionModelBean(String uid, double[] coefficients, double intercept) {
        this.uid = uid;
        this.coefficients = coefficients.clone();
        this.intercept = intercept;
    }

    /***/
    public String getUid() {
        return uid;
    }

    /***/
    public double[] getCoefficients() {
        return coefficients;
    }

    /***/
    public double getIntercept() {
        return intercept;
    }

    /***/
    public void setUid(String uid) {
        this.uid = uid;
    }

    /***/
    public void setCoefficients(double[] coefficients) {
        this.coefficients = coefficients;
    }

    /***/
    public void setIntercept(double intercept) {
        this.intercept = intercept;
    }
}
