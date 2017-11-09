package org.dichcorp.dao.users;

import java.util.Set;

import org.dichcorp.model.users.User;

public interface UserDao {
    User findUserByLogin(String login, boolean loadFollowers, boolean loadFollowing);

    Set<User> searchUsers(Set<String> searchRequest);

    User findUserByEmail(String email);

    void saveUser(User user);

    void updateUser(User user);
}
