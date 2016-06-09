/*
 * The MIT License
 *
 * Copyright 2016 oncore.
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
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.oncore.chhs.web.utils;

import java.math.BigDecimal;

/**
 *
 * @author OnCore LLC
 */
public final class GpsUtils {

    /**
     * The calculateDistance method determines the distance between two GPS
     * cordinates.
     *
     * @param latCordinate1 latitude of first coordinate
     * @param longCordinate1 longitude of first coordinate
     * @param latCordinate2 latitude of second coordinate
     * @param longCordinate2 longitude of second coordinate
     * @return
     */
    public static double calculateDistance(Double latCordinate1, Double longCordinate1, Double latCordinate2, Double longCordinate2) {

        Double distance = 0d;

        Double radius = 6371 * 0.621371;
        Double calculatedLatitude = (latCordinate2 - latCordinate1) * Math.PI / 180;
        Double calculatedLongitude = (longCordinate2 - longCordinate1) * Math.PI / 180;

        Double arc = Math.sin(calculatedLatitude / 2) * Math.sin(calculatedLatitude / 2)
                + Math.cos(latCordinate1 * Math.PI / 180) * Math.cos(latCordinate2 * Math.PI / 180)
                * Math.sin(calculatedLongitude / 2) * Math.sin(calculatedLongitude / 2);

        Double curve = 2 * Math.atan2(Math.sqrt(arc), Math.sqrt(1 - arc));

        distance = radius * curve;

       // distance = (distance / 10);

        return new BigDecimal(distance).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
