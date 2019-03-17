package net.fab.the.chemist.springbootrestfullws.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundUserException extends RuntimeException {

	public NotFoundUserException(String string) {
		super(string);
	}

}
