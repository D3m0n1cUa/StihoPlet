package org.dichcorp.userdetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dichcorp.model.users.User;
import org.dichcorp.model.users.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

    private static final long serialVersionUID = 3147320544294294468L;

    private User user;
    private List<GrantedAuthority> authority;

    public UserDetails(User user) {
	super();
	this.user = user;
	authority = buildUserAuthority();
    }

    private List<GrantedAuthority> buildUserAuthority() {
	Set<GrantedAuthority> setAuth = new HashSet<GrantedAuthority>();

	for (UserRole userRole : user.getUserRole()) {
	    setAuth.add(new SimpleGrantedAuthority(userRole.getRole()));
	}

	List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(setAuth);

	return result;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
	return authority;
    }

    @Override
    public String getPassword() {
	return user.getPassword();
    }

    @Override
    public String getUsername() {
	return user.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
	return true;
    }

    @Override
    public boolean isAccountNonLocked() {
	return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
	return true;
    }

    @Override
    public boolean isEnabled() {
	return user.isEnabled();
    }

}
