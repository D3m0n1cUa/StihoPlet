package org.dichcorp.util;

import javax.servlet.http.HttpServletRequest;

import org.dichcorp.model.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationUtilImpl implements AuthenticationUtil {

    @Autowired
    private AuthenticationManager authenicationManager;

    @Override
    public void autologin(User user, HttpServletRequest request) {
	UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
		user.getLogin(), user.getConfirmPassword());
	Authentication authentication = authenicationManager.authenticate(authenticationToken);
	SecurityContextHolder.getContext().setAuthentication(authentication);
	request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
		SecurityContextHolder.getContext());
    }

    @Override
    public boolean isLogged() {
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	return !(authentication instanceof AnonymousAuthenticationToken);

    }

}
