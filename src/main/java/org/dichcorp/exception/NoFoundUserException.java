package org.dichcorp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such user")
public class NoFoundUserException extends RuntimeException {

    private static final long serialVersionUID = 7762457735697148407L;

}
