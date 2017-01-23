package com.palmithor.sparkplayground.http;

import com.google.gson.Gson;
import com.palmithor.sparkplayground.http.exceptions.BadRequestException;
import com.palmithor.sparkplayground.http.request.EmptyRequest;
import com.palmithor.sparkplayground.http.response.ApiError;
import com.palmithor.sparkplayground.http.response.BaseResponse;
import com.palmithor.sparkplayground.http.response.ErrorResponse;
import spark.Request;
import spark.Response;
import spark.Route;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Base functionality for all api routes
 * <p>
 * Functionality such as validation, request / response mapping etc..
 *
 * @author palmithor
 * @since 17.1.2017.
 */
public abstract class ApiRoute<RequestType, ResponseType extends BaseResponse> implements RequestHandler<RequestType>, Route {

    private final Class<RequestType> requestClass;
    private final Validator validator;
    private final Gson gson;


    public ApiRoute(final Class<RequestType> requestClass, final Gson gson) {
        this.gson = gson;
        this.requestClass = requestClass;
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public Object handle(final Request request, final Response response) throws Exception {

        RequestType requestBody = null;
        if (hasPayload()) {
            requestBody = gson.fromJson(request.body(), requestClass);
        }
        final Map<String, String[]> queryParams = request.queryMap().toMap();

        return process(requestBody, request.params(), queryParams);
    }

    protected abstract ResponseType processImpl(final RequestType payload, final Map<String, String> pathParams, final Map<String, String[]> queryParams) throws Exception;

    public ResponseType process(final RequestType value, final Map<String, String> pathParams, Map<String, String[]> queryParams ) throws Exception {
        if (value != null) {
            Optional<ErrorResponse> validationResult = validate(value);
            if (validationResult.isPresent()) {
                throw new BadRequestException(validationResult.get());
            }
        }
        return processImpl(value, pathParams, queryParams);
    }

    private boolean hasPayload() {
        return requestClass != EmptyRequest.class;
    }


    private Optional<ErrorResponse> validate(final RequestType objToValidate) {
        Set<ConstraintViolation<RequestType>> constraintViolations = validator.validate(objToValidate);
        if (constraintViolations.size() > 0) {
            ConstraintViolation<RequestType> violation = constraintViolations.iterator().next();
            return Optional.of(ErrorResponse.create()
                    .withApiError(ApiError.BAD_REQUEST)
                    .withMessage(violation.getPropertyPath() + " " + violation.getMessage()).build());
        } else {
            return Optional.empty();
        }
    }
}
