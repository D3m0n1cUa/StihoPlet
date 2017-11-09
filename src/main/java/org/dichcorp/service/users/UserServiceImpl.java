package org.dichcorp.service.users;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.dichcorp.dao.users.UserDao;
import org.dichcorp.exception.NoFoundUserException;
import org.dichcorp.model.users.User;
import org.dichcorp.model.users.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User getUserByLogin(String login) {
	if (login == null || login.isEmpty()) {
	    throw new IllegalArgumentException("Login can not be null or empty.");
	}

	User user = userDao.findUserByLogin(login, false, false);

	if (user == null) {
	    throw new NoFoundUserException();
	}

	return user;
    }

    @Override
    public User getUserByLogin(String login, boolean loadFollowers, boolean loadFollowing) {
	if (login == null || login.isEmpty()) {
	    throw new IllegalArgumentException("Login can not be null or empty.");
	}

	User user = userDao.findUserByLogin(login, loadFollowers, loadFollowing);

	if (user == null) {
	    throw new NoFoundUserException();
	}

	return user;
    }

    @Override
    public boolean isUserWithLogin(String login) {
	if (login == null || login.isEmpty()) {
	    throw new IllegalArgumentException("Login can not be null or empty.");
	}

	User user = userDao.findUserByLogin(login, false, false);
	return user != null;
    }

    @Override
    public boolean isUserWithEmail(String email) {
	if (email == null || email.isEmpty()) {
	    throw new IllegalArgumentException("Email can not be null or empty.");
	}

	User user = userDao.findUserByEmail(email);
	return user != null;
    }

    @Override
    public void saveNewUser(User user) {
	if (user == null) {
	    throw new IllegalArgumentException("User can not be null.");
	}

	UserRole userRole = new UserRole(user, "ROLE_USER");
	user.getUserRole().add(userRole);
	user.setEnabled(true);
	user = encodePassword(user);
	userDao.saveUser(user);
    }

    private User encodePassword(User user) {
	user.setPassword(passwordEncoder.encode(user.getPassword()));
	return user;
    }

    @Override
    public void addFollower(User follower, User following) {
	if (follower == null || following == null) {
	    throw new IllegalArgumentException("Follower or following can not be null.");
	}

	follower.getFollowing().add(following);
	userDao.updateUser(follower);

    }

    @Override
    public void removeFollower(User follower, User following) {
	if (follower == null || following == null) {
	    throw new IllegalArgumentException("Follower or following can not be null.");
	}

	follower.getFollowing().remove(following);
	userDao.updateUser(follower);

    }

    @Override
    public Set<User> findUser(String searchRequest) {
	if (searchRequest == null || searchRequest.isEmpty()) {
	    throw new IllegalArgumentException("Search request is null or empty");
	}

	return userDao.searchUsers(getSearchStrings(searchRequest));
    }

    private Set<String> getSearchStrings(String searchRequest) {
	return new HashSet<>(Arrays.asList(searchRequest.split(" ")));
    }

}
