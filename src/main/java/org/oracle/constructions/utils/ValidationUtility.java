package org.oracle.constructions.utils;

import java.util.regex.Pattern;

public class ValidationUtility {

    private static Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
    private static Pattern contractIdPattern = Pattern.compile("^[0-9]{4}$");
    private static Pattern customerIdPattern = Pattern.compile("^[0-9]{7}$");
    private static Pattern geoZonePattern = Pattern.compile("^[a-z]{2}_[a-z]{4,5}");

    private static Pattern teamProjectCodePattern = Pattern.compile("^[A-Za-z0-9]{1,20}");
    private static Pattern buildDurationPattern = Pattern.compile("^[0-9]+[s]$");


    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }

    public static boolean customerIdValidation(String str) {
        if (str == null) {
            return false;
        }
        return customerIdPattern.matcher(str).matches();
    }

    public static boolean contractIdValidation(String str) {
        if (str == null) {
            return false;
        }
        return contractIdPattern.matcher(str).matches();
    }

    public static boolean geoZoneValidation(String str) {
        if (str == null) {
            return false;
        }
        return geoZonePattern.matcher(str).matches();
    }

    public static boolean teamProjectCodeValidation(String str) {
        if (str == null) {
            return false;
        }
        return teamProjectCodePattern.matcher(str).matches();
    }

    public static boolean buildDurationValidation(String str) {
        if (str == null) {
            return false;
        }
        return buildDurationPattern.matcher(str).matches();
    }



}
