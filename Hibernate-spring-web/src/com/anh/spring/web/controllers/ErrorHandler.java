package com.anh.spring.web.controllers;

import java.nio.file.AccessDeniedException;

import org.springframework.dao.DataAccessException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {
	@ExceptionHandler(DataAccessException.class)
	public String handleDatabaseException(DataAccessException ex) {
		ex.printStackTrace();
		return "error";
	}

	@ExceptionHandler(AccessDeniedException.class)
	public String handleAccessDeniedException(AccessDeniedHandler ex) {
		return "denied";
	}
}
