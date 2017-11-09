package org.dichcorp.util;

import javax.servlet.http.HttpServletRequest;

import org.dichcorp.model.users.User;

public interface AuthenticationUtil {
    void autologin(User user, HttpServletRequest request);

    boolean isLogged();
}
