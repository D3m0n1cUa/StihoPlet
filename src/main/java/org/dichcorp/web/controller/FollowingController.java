package org.dichcorp.web.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.dichcorp.model.users.User;
import org.dichcorp.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FollowingController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/follow/{login}", method = RequestMethod.POST)
    public String follow(@PathVariable String login, Principal principal, Model model, HttpServletRequest request) {
	User follower = userService.getUserByLogin(principal.getName(), false, true);
	User following = userService.getUserByLogin(login);

	if (following.equals(follower)) {
	    throw new IllegalArgumentException("Follower and following are the same.");
	}

	userService.addFollower(follower, following);

	return "redirect:" + request.getHeader("referer");
    }

    @RequestMapping(value = "/unfollow/{login}", method = RequestMethod.POST)
    public String unfollow(@PathVariable String login, Principal principal, Model model, HttpServletRequest request) {
	User follower = userService.getUserByLogin(principal.getName(), false, true);
	User following = userService.getUserByLogin(login);

	if (following.equals(follower)) {
	    throw new IllegalArgumentException("Follower and following are the same.");
	}

	userService.removeFollower(follower, following);

	return "redirect:" + request.getHeader("referer");
    }

    @RequestMapping(value = "/followers/{login}", method = RequestMethod.GET)
    public String followers(@PathVariable String login, Principal principal, Model model) {
	User user = userService.getUserByLogin(login, true, false);

	User loggedUser = userService.getUserByLogin(principal.getName(), false, true);
	model.addAttribute("title", login + "’s followers:");
	model.addAttribute("principal", loggedUser);
	model.addAttribute("listOfUsers", user.getFollowers());

	if (user.getFollowers().isEmpty()) {
	    model.addAttribute("textError", login + " has no followers.");
	}

	return "listPage";
    }

    @RequestMapping(value = "/following/{login}", method = RequestMethod.GET)
    public String following(@PathVariable String login, Principal principal, Model model) {
	User user = userService.getUserByLogin(login, false, true);

	User loggedUser = userService.getUserByLogin(principal.getName(), false, true);
	model.addAttribute("title", login + " follows:");
	model.addAttribute("principal", loggedUser);
	model.addAttribute("listOfUsers", user.getFollowing());

	if (user.getFollowing().isEmpty()) {
	    model.addAttribute("textError", login + " is following nobody.");
	}

	return "listPage";
    }

}
