package com.example.javaspringtbootjpa.exception;

import java.util.NoSuchElementException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  /**
   * Leverage Exception Handler framework for resource not found Exception.
   *
   * @param ex ResourceNotFoundException
   * @param request WebRequest
   * @return http response
   */
  @ExceptionHandler(ResourceNotFoundException.class)
  public final ResponseEntity<Object> handleResourceNotFoundException(
      ResourceNotFoundException ex, WebRequest request) {

    ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    return createResponseEntity(pd, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

  /**
   * Leverage Exception Handler framework for id not found Exception.
   *
   * @param ex NoSuchElementException
   * @param request WebRequest
   * @return http response
   */
  @ExceptionHandler(NoSuchElementException.class)
  public final ResponseEntity<Object> handleNoSuchElementException(
      NoSuchElementException ex, WebRequest request) {

    ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    return createResponseEntity(pd, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

  /**
   * Leverage Exception Handler framework for unexpected exceptions.
   *
   * @param ex general exception i.e. Exception
   * @param request to access request params
   * @return http response
   */
  @ExceptionHandler(Exception.class)
  public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
    ProblemDetail pd =
        ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    return createResponseEntity(pd, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
  }
}
