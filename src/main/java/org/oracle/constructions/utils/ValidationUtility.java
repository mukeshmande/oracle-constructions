package org.oracle.constructions.utils;

import org.oracle.constructions.exception.CustomerDetailsException;

import java.util.regex.Pattern;

public class ValidationUtility {

    private static Pattern contractIdPattern = Pattern.compile("^\\d{4}$");
    private static Pattern customerIdPattern = Pattern.compile("^\\d{7}$");
    private static Pattern geoZonePattern = Pattern.compile("^[a-z]{2}_[a-z]{4,5}");

    private static Pattern teamProjectCodePattern = Pattern.compile("^[A-Za-z0-9]{1,20}");
    private static Pattern buildDurationPattern = Pattern.compile("^\\d+[s]$");

    private static final String INVALID_INPUT = "Invalid Input";


    public boolean customerIdValidation(String str) throws CustomerDetailsException {
        if (str == null || !customerIdPattern.matcher(str).matches()) {
            throw new CustomerDetailsException(INVALID_INPUT);
        }
        return true;
    }

    public boolean contractIdValidation(String str) throws CustomerDetailsException {
        if (str == null || !contractIdPattern.matcher(str).matches()) {
            throw new CustomerDetailsException(INVALID_INPUT);
        }
        return true;
    }

    public boolean geoZoneValidation(String str) throws CustomerDetailsException {
        if (str == null || !geoZonePattern.matcher(str).matches()) {
            throw new CustomerDetailsException(INVALID_INPUT);
        }
        return true;
    }

    public boolean teamProjectCodeValidation(String str) throws CustomerDetailsException {
        if (str == null || !teamProjectCodePattern.matcher(str).matches()) {
            throw new CustomerDetailsException(INVALID_INPUT);
        }
        return true;
    }

    public boolean buildDurationValidation(String str) throws CustomerDetailsException {
        if (str == null) {
            throw new CustomerDetailsException(INVALID_INPUT);
        }
        return buildDurationPattern.matcher(str).matches();
    }
}
