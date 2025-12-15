package com.haingue.tp1.CommunityBookstore.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties.Problemdetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.haingue.tp1.CommunityBookstore.exception.BusinessException;
import org.springframework.web.client.HttpClientErrorException;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@RestControllerAdvice
public class RestControllerAdviceConfiguration {

  private static final Logger logger = LoggerFactory.getLogger(RestControllerAdviceConfiguration.class);

  private static ProblemDetail generateProblemDetail(HttpStatus httpStatus, Exception ex) {
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, ex.getMessage());
    problemDetail.setProperties(Map.of(
            "timestamp", Instant.now().toString(),
            "uuid", UUID.randomUUID().toString()
    ));
    logger.error("Exception caught: {}", problemDetail);
    return problemDetail;
  }

  @ExceptionHandler({
          BusinessException.class,
  })
  public ResponseEntity<ProblemDetail> handleBusinessException(RuntimeException ex) {
    return ResponseEntity.badRequest().body(generateProblemDetail(HttpStatus.BAD_REQUEST, ex));
  }

}
