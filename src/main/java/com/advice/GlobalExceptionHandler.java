package com.advice;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.exception.SoccerManagementException;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(SQLException.class)
	public String handleSQLException(HttpServletRequest request, Exception ex) {
		logger.info("SQLException Occured:: URL=" + request.getRequestURL());
		return "database_error";
	}

	@ExceptionHandler(SoccerManagementException.class)
	public ResponseEntity<String> userSoccerManagementExceptionHandler(RuntimeException e) {
		JSONObject response = new JSONObject();
		response.put("message", e.getMessage());
		return new ResponseEntity<String>(response.toString(), HttpStatus.PRECONDITION_FAILED);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> exceptionHandler(RuntimeException e) {
		JSONObject response = new JSONObject();
		response.put("message", e.getMessage());
		return new ResponseEntity<String>(response.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
