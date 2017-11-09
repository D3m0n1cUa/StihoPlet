package org.dichcorp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such rifma")
public class NoFoundRifmaException extends RuntimeException {

    private static final long serialVersionUID = -3977155918529940228L;

}
