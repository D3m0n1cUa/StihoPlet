package org.dichcorp.userdetails;

import org.dichcorp.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	org.dichcorp.model.users.User user = userService.getUserByLogin(username);
	return new org.dichcorp.userdetails.UserDetails(user);
    }

}
