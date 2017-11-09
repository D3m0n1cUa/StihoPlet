package org.dichcorp.web.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.dichcorp.model.rifms.Rifma;
import org.dichcorp.model.users.User;
import org.dichcorp.service.rifms.RifmaService;
import org.dichcorp.service.users.UserService;
import org.dichcorp.validators.RifmaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RifmaController {

    @Autowired
    private RifmaValidator rifmaValidator;

    @Autowired
    private RifmaService rifmaService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/rifma/{id}", method = RequestMethod.GET)
    public String profilePage(@PathVariable Integer id, Principal principal, Model model) {
	User loggedUser = userService.getUserByLogin(principal.getName());

	Rifma rifma = rifmaService.getRifmaById(id);

	model.addAttribute("principal", loggedUser);
	model.addAttribute("rifma", rifma);
	model.addAttribute("rifmaForm", new Rifma());

	return "rifma";
    }

    @RequestMapping(value = "/rifma/add", method = RequestMethod.POST)
    public String registrationPage(@Validated @ModelAttribute("rifmaForm") Rifma newRifma, BindingResult bindingResult,
	    Principal principal, HttpServletRequest request) {
	User loggedUser = userService.getUserByLogin(principal.getName());
	rifmaValidator.validate(newRifma, bindingResult);

	if (bindingResult.hasErrors()) {
	    return "redirect:/user/" + loggedUser.getLogin();
	}

	newRifma.setUser(loggedUser);
	rifmaService.saveRifma(newRifma);

	return "redirect:/rifma/" + newRifma.getIdRifm();
    }
}
