package com.advice;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.exception.SoccerManagementException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(SoccerManagementException.class)
	public ResponseEntity<String> userSoccerManagementExceptionHandler(RuntimeException exception) {
		JSONObject response = new JSONObject();
		response.put("message", exception.getMessage());
		return new ResponseEntity<String>(response.toString(), HttpStatus.PRECONDITION_FAILED);
	}

	@ExceptionHandler(SQLException.class)
	public ResponseEntity<String> handleSQLException(HttpServletRequest request, Exception exception) {
		JSONObject response = new JSONObject();
		response.put("message", "Facing issue while hitting database");
		return new ResponseEntity<String>(response.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> exceptionHandler(Exception exception) {
		JSONObject response = new JSONObject();
		response.put("message", exception.getMessage());
		return new ResponseEntity<String>(response.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
