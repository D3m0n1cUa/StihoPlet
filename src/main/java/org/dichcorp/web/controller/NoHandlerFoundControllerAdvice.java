package org.dichcorp.web.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.dichcorp.exception.NoFoundRifmaException;
import org.dichcorp.exception.NoFoundUserException;
import org.dichcorp.model.users.User;
import org.dichcorp.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class NoHandlerFoundControllerAdvice {

    @Autowired
    private UserService userService;

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNoHandlerFoundException(NoHandlerFoundException ex, Model model, Principal principal) {
	User loggedUser = userService.getUserByLogin(principal.getName(), false, false);

	model.addAttribute("principal", loggedUser);
	model.addAttribute("error", "We are so sorry, such page has not been found.");
	return "404";
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public String handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
	return "redirect:/user/";
    }

    @ExceptionHandler(NoFoundUserException.class)
    public String handleNoFoundUserException(NoFoundUserException ex, Model model, Principal principal) {
	User loggedUser = userService.getUserByLogin(principal.getName(), false, false);

	model.addAttribute("principal", loggedUser);
	model.addAttribute("error", "We are so sorry, such user has not been found.");
	return "404";
    }

    @ExceptionHandler(NoFoundRifmaException.class)
    public String handleNoFoundRifmaException(NoFoundRifmaException ex, Model model, Principal principal) {
	User loggedUser = userService.getUserByLogin(principal.getName(), false, false);

	model.addAttribute("principal", loggedUser);
	model.addAttribute("error", "We are so sorry, such rime has not been found.");

	return "404";
    }

    @ExceptionHandler(Exception.class)
    public String generalHandler(Exception ex, Model model, HttpServletRequest request, Principal principal) {
	if (principal != null) {
	    User loggedUser = userService.getUserByLogin(principal.getName(), false, false);
	    model.addAttribute("principal", loggedUser);
	}

	model.addAttribute("exception", ex);
	model.addAttribute("url", request.getRequestURL());

	return "errorPage";
    }
}
