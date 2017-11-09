package org.dichcorp.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.dichcorp.model.users.User;
import org.dichcorp.service.users.UserService;
import org.dichcorp.util.AuthenticationUtil;
import org.dichcorp.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private AuthenticationUtil authenticationUtil;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationPage(Model model) {
	if (authenticationUtil.isLogged()) {
	    return "redirect:/user";
	}

	model.addAttribute("userForm", new User());

	return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registrationPage(@Validated @ModelAttribute("userForm") User newUser, BindingResult bindingResult,
	    HttpServletRequest request, Model model) {
	if (authenticationUtil.isLogged()) {
	    return "redirect:/user";
	}

	userValidator.validate(newUser, bindingResult);

	if (bindingResult.hasErrors()) {
	    return "registration";
	}

	userService.saveNewUser(newUser);
	authenticationUtil.autologin(newUser, request);

	return "redirect:/user";
    }

}
