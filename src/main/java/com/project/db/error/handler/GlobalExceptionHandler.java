package com.project.db.error.handler;


import com.project.db.error.AlreadyExistsException;
import com.project.db.error.ErrorResponse;
import com.project.db.error.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LogManager.getLogger();

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({
            AlreadyExistsException.class,
            DateTimeParseException.class,
            UnsupportedOperationException.class,
            IllegalArgumentException.class,
            IllegalStateException.class,
            NullPointerException.class,
            RuntimeException.class
    })
    public ResponseEntity<ErrorResponse> handleBadRequest(final RuntimeException e, final WebRequest webRequest){

        return constructExceptionResponse(e,webRequest,HttpStatus.BAD_REQUEST);
    }
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler({
            NotFoundException.class
    })
    public ResponseEntity<ErrorResponse> handleNotFound(final RuntimeException e, final WebRequest webRequest){
        return constructExceptionResponse(e,webRequest,HttpStatus.NOT_FOUND);
    }


    protected ResponseEntity<ErrorResponse> constructExceptionResponse(
            final Exception e, final WebRequest request, final HttpStatus status) {
        final String path = request.getDescription(false);
        String timestamp = LocalDateTime.now().toString();

        LOGGER.error("Failed to request [{}] path. Error:", path, e);
        ErrorResponse errorResponse = new ErrorResponse(status.value(), path, e.getMessage(), timestamp);


        return new ResponseEntity<>(errorResponse,status);
    }

}
