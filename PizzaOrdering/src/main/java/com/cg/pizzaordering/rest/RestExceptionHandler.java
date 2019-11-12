/**
 * 
 */
package com.cg.pizzaordering.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.pizzaordering.exception.PizzaException;
import com.cg.pizzaordering.exception.ResourceNotFoundException;

/**
 * @author Saurabh
 *
 */
@ControllerAdvice
public class RestExceptionHandler {
	
	/**
	 * @author Saurabh
	 * Description: To display custom error response in case of resource not found.
	 * @param exception
	 * @return
	 */
	@ExceptionHandler
	public ResponseEntity<CustomErrorResponse> handleException(ResourceNotFoundException exception){
		CustomErrorResponse customErrorResponse = new CustomErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage(), System.currentTimeMillis());
		return new ResponseEntity<>(customErrorResponse,HttpStatus.NOT_FOUND);
	}
	
	/**
	 * @author Saurabh
	 * Description: To display custom error response in case of PizzaException.
	 * @param exception
	 * @return
	 */
	@ExceptionHandler
	public ResponseEntity<CustomErrorResponse> handleException(PizzaException exception){
		CustomErrorResponse customErrorResponse = new CustomErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), System.currentTimeMillis());
		return new ResponseEntity<>(customErrorResponse,HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * @author Saurabh
	 * Description: To display custom error response in case of any exception.
	 * @param exception
	 * @return
	 */
	@ExceptionHandler
	public ResponseEntity<CustomErrorResponse> handleException(Exception exception){
		CustomErrorResponse customErrorResponse = new CustomErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), System.currentTimeMillis());
		return new ResponseEntity<>(customErrorResponse,HttpStatus.BAD_REQUEST);
	}

}
