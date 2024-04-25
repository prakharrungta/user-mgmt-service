package com.usermgmt.config;

import java.io.Serializable;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@ControllerAdvice
public class UserExceptionHandler {

	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<UserExceptionHandler.MyException> handleBadInputException(InvalidInputException ex) {
		ex.printStackTrace();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new MyException(ex.getMessage(), ex.getClass().getName()));
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<UserExceptionHandler.MyException> handleBadInputException(NoSuchElementException ex) {
		ex.printStackTrace();
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new MyException(ex.getMessage(), ex.getClass().getName()));
	}

	@AllArgsConstructor
	@Getter
	private class MyException implements Serializable {
		/**
		* 
		*/
		private static final long serialVersionUID = -6463811542308424620L;
		private String errorMessage;
		private String errorType;

	}
}

