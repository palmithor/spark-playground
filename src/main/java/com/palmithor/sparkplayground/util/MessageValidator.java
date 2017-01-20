package com.palmithor.sparkplayground.util;

import com.palmithor.sparkplayground.http.response.ApiError;
import com.palmithor.sparkplayground.http.response.ErrorResponse;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Optional;
import java.util.Set;

/**
 * @author palmithor
 * @since 17.1.2017.
 */
public class MessageValidator<T> {

    private final Validator validator;

    public MessageValidator() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }


    public Optional<ErrorResponse> validate(final T objToValidate) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(objToValidate);
        if (constraintViolations.size() > 0) {
            return Optional.of(ErrorResponse.create()
                    .withApiError(ApiError.BAD_REQUEST)
                    .withMessage(constraintViolations.iterator().next().getMessage()).build());
        } else {
            return Optional.empty();
        }
    }
}
