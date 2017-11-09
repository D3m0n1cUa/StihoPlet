package org.dichcorp.service.users;

import java.util.Set;

import org.dichcorp.model.users.User;

public interface UserService {

    User getUserByLogin(String login);

    User getUserByLogin(String login, boolean loadFollowers, boolean loadFollowing);

    Set<User> findUser(String searchRequest);

    boolean isUserWithLogin(String login);

    boolean isUserWithEmail(String email);

    void saveNewUser(User user);

    void addFollower(User follower, User following);

    void removeFollower(User follower, User following);
}
