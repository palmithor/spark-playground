package com.palmithor.sparkplayground.http.request;

import org.junit.Test;

import javax.validation.ConstraintViolation;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * @author palmithor
 * @since 17.1.2017.
 */
public class TodoRequestValidationTest extends ValidationTest<TodoRequest> {

    @Test
    public void testMissingTitle() {
        final TodoRequest request = new TodoRequest();
        Set<ConstraintViolation<TodoRequest>> validationResult = validate(request);
        assertThat(validationResult.size(), is(1));
        ConstraintViolation<TodoRequest> c = validationResult.iterator().next();
        assertThat(c.getMessage(), is("may not be null"));
        assertThat(c.getPropertyPath().iterator().next().getName(), is("title"));
    }

    @Test
    public void testValid() {
        final TodoRequest request = new TodoRequest("title", null, null);
        Set<ConstraintViolation<TodoRequest>> validationResult = validate(request);
        assertThat(validationResult.size(), is(0));
    }

}