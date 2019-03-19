package net.fab.the.chemist.springbootrestfullws.user;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * cette classe sert à gere les exexceptions particuliere
 * par exemple si le client envoie des mauvaise de données il doit en etre averti
 * sinon il reçoit un status 200 et pense que tout s'est bien passé
 * @ControllerAdvice pour indiquer que ce controleur doit etre appliqué sur tous les controller de l'application
 */

@ControllerAdvice
@RestController
public class CustomizeResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	//le nom de la méthode doit etre handlerException sinon erreur ???
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handlerException(Exception exception, WebRequest webRequest){
		
		ValidationException validationException = new ValidationException(new Date(), exception.getMessage(), webRequest.getDescription(false));
				
		return  new ResponseEntity(validationException, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@ExceptionHandler(NotFoundUserException.class)
	public final ResponseEntity<Object> handlerUserNotFoundException(NotFoundUserException exception, WebRequest webRequest){
		
		ValidationException validationException = new ValidationException(new Date(), exception.getMessage(), webRequest.getDescription(false));
				
		return  new ResponseEntity(validationException, HttpStatus.NOT_FOUND);
		
	}
	
	@Override
	public final ResponseEntity<Object> handleMethodArgumentNotValid (MethodArgumentNotValidException exception,
			HttpHeaders httpHeaders, HttpStatus httpStatus, WebRequest webRequest){
		
		ValidationException validationException = new ValidationException(new Date(), exception.getMessage(), exception.getBindingResult().toString());
				
		return  new ResponseEntity(validationException, HttpStatus.BAD_REQUEST);
		
	}
	
	
}
