package com.invoice.backend.controllers.exceptions;

import java.time.Instant;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.invoice.backend.exceptions.ResourceNotFoundException;
import com.invoice.backend.models.exceptions.ResponseError;
import com.invoice.backend.utils.MessagesConstants;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ResponseError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		HttpStatus NOT_FOUND = HttpStatus.NOT_FOUND;
		ResponseError error = new ResponseError();

		error.setTimestamp(Instant.now());
		error.setStatus(NOT_FOUND.value());
		error.setError(MessagesConstants.NOT_FOUND);
		error.setMessage(e.getLocalizedMessage());
		error.setPath(request.getRequestURI());
		return ResponseEntity.status(NOT_FOUND.value()).body(error);
	}

}
