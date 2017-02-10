package KKGame;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class Exception402 extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}

