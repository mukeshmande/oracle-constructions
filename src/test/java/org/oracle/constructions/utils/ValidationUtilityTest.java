package org.oracle.constructions.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.oracle.constructions.exception.CustomerDetailsException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidationUtilityTest {

    private static final String INVALID_INPUT = "Invalid Input";

    @Test
    public void customerIdValidationTest() throws CustomerDetailsException {
        String customerId = "2343225";
        boolean validation = ValidationUtility.customerIdValidation(customerId);
        Assertions.assertSame(validation, true);

    }

    @Test
    public void customerIdValidationExceptionTest() {
        String customerId = "2343225bhjzbc";
        CustomerDetailsException thrown = Assertions.assertThrows(CustomerDetailsException.class, () -> ValidationUtility.customerIdValidation(customerId), INVALID_INPUT);
        assertTrue(thrown.getMessage().contains(INVALID_INPUT));
    }


    @Test
    public void contractIdValidationTest() throws CustomerDetailsException {
        String contractId = "2343";
        boolean validation = ValidationUtility.contractIdValidation(contractId);
        Assertions.assertSame(validation, true);

    }

    @Test
    public void contractIdValidationExceptionTest(){
        String contractId = "2343225bhjzbc";
        CustomerDetailsException thrown = Assertions.assertThrows(CustomerDetailsException.class, () -> ValidationUtility.contractIdValidation(contractId), INVALID_INPUT);
        assertTrue(thrown.getMessage().contains(INVALID_INPUT));
    }


    @Test
    public void geoZoneValidationTest() throws CustomerDetailsException {
        String geoZone = "eu_west";
        boolean validation = ValidationUtility.geoZoneValidation(geoZone);
        Assertions.assertSame(validation, true);

    }

    @Test
    public void geoZoneValidationExceptionTest(){
        String geoZone = "2343225bhjzbc";
        CustomerDetailsException thrown = Assertions.assertThrows(CustomerDetailsException.class, () -> ValidationUtility.geoZoneValidation(geoZone), INVALID_INPUT);
        assertTrue(thrown.getMessage().contains(INVALID_INPUT));
    }


    @Test
    public void teamProjectCodeValidationTest() throws CustomerDetailsException {
        String teamProjectCode = "ProjectApple";
        boolean validation = ValidationUtility.teamProjectCodeValidation(teamProjectCode);
        Assertions.assertSame(validation, true);

    }

    @Test
    public void teamProjectCodeValidationExceptionTest() {
        String teamProjectCode = "234322@5bhjzbc";
        CustomerDetailsException thrown = Assertions.assertThrows(CustomerDetailsException.class, () -> ValidationUtility.teamProjectCodeValidation(teamProjectCode), INVALID_INPUT);
        assertTrue(thrown.getMessage().contains(INVALID_INPUT));
    }

    @Test
    public void buildDurationValidationTest() throws CustomerDetailsException {
        String buildDuration = "3445s";
        boolean validation = ValidationUtility.buildDurationValidation(buildDuration);
        Assertions.assertSame(validation, true);

    }

    @Test
    public void buildDurationValidationExceptionTest() {
        String buildDuration = "234322@5bhjzbc";
        CustomerDetailsException thrown = Assertions.assertThrows(CustomerDetailsException.class, () -> ValidationUtility.buildDurationValidation(buildDuration), INVALID_INPUT);
        assertTrue(thrown.getMessage().contains(INVALID_INPUT));
    }

}
