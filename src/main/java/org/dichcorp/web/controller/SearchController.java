package org.dichcorp.web.controller;

import java.security.Principal;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.dichcorp.model.users.User;
import org.dichcorp.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(@RequestParam String search, Principal principal, Model model, HttpServletRequest request) {
	if (search.isEmpty()) {
	    return "redirect:" + request.getHeader("referer");
	}

	User loggedUser = userService.getUserByLogin(principal.getName(), false, true);

	Set<User> foundUsers = userService.findUser(search);

	model.addAttribute("principal", loggedUser);
	model.addAttribute("listOfUsers", foundUsers);
	model.addAttribute("title", "Result for: " + search);

	if (foundUsers.isEmpty()) {
	    model.addAttribute("textError", "We are so sorry, but we have found nothing.");
	}

	return "listPage";
    }
}
