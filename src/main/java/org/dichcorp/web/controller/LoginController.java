package org.dichcorp.web.controller;

import org.dichcorp.util.AuthenticationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping
public class LoginController {

    @Autowired
    private AuthenticationUtil authenticationUtil;

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String indexPage(Model model) {
	if (authenticationUtil.isLogged()) {
	    return "redirect:/user";
	}

	return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
	if (authenticationUtil.isLogged()) {
	    return "redirect:/user";
	}

	return "login";
    }

}
