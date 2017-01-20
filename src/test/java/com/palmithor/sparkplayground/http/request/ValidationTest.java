package com.palmithor.sparkplayground.http.request;

import org.junit.Before;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author palmithor
 * @since 17.1.2017.
 */
abstract class ValidationTest<T> {

    private Validator validator;

    @Before
    public void setUp() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    Set<ConstraintViolation<T>> validate(final T objToValidate) {
        return validator.validate(objToValidate);
    }
}
