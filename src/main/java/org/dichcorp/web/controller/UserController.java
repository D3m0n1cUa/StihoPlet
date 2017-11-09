package org.dichcorp.web.controller;

import java.security.Principal;
import java.util.List;

import org.dichcorp.model.rifms.Rifma;
import org.dichcorp.model.users.User;
import org.dichcorp.service.rifms.RifmaService;
import org.dichcorp.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RifmaService rifmaService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String userPage(Principal principal) {
	return "redirect:/user/" + principal.getName();
    }

    @RequestMapping(value = "/user/{login}", method = RequestMethod.GET)
    public String userPage(@PathVariable String login, Principal principal, Model model) {
	User user = userService.getUserByLogin(login);

	User loggedUser = userService.getUserByLogin(principal.getName(), false, true);

	List<Rifma> rifms = loggedUser.getLogin().equals(login) ? rifmaService.getAllRifmsByUsers(loggedUser)
		: rifmaService.getUserRifms(user);

	model.addAttribute("principal", loggedUser);
	model.addAttribute("user", user);
	model.addAttribute("rifms", rifms);

	model.addAttribute("rifmaForm", new Rifma());

	return "user";
    }
}
