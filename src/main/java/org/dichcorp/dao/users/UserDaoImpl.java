package org.dichcorp.dao.users;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dichcorp.model.users.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private HibernateTemplate template;

    @Transactional(readOnly = true)
    @Override
    public User findUserByLogin(String login, boolean loadFollowers, boolean loadFollowing) {
	List<?> users = template.find("FROM User WHERE login=? and enabled=?", login, true);
	if (users.isEmpty()) {
	    return null;
	} else {
	    User user = (User) users.get(0);
	    if (loadFollowers) {
		template.initialize(user.getFollowers());
	    }
	    if (loadFollowing) {
		template.initialize(user.getFollowing());
	    }

	    return user;
	}
    }

    @Transactional(readOnly = true)
    @Override
    public Set<User> searchUsers(Set<String> searchRequest) {

	List<User> users = template.execute(new HibernateCallback<List<User>>() {

	    @Override
	    public List<User> doInHibernate(Session session) throws HibernateException {
		Query query = session
			.createQuery(
				"FROM User user WHERE user.login IN (:request) or user.firstName IN (:request) or user.lastName IN (:request)")
			.setParameterList("request", searchRequest);
		return query.getResultList();
	    }

	});
	return new HashSet<User>(users);
    }

    @Transactional(readOnly = true)
    @Override
    public User findUserByEmail(String email) {
	List<?> users = template.find("FROM User WHERE email=? and enabled=?", email, true);
	if (users.isEmpty()) {
	    return null;
	} else {
	    User user = (User) users.get(0);
	    return user;
	}
    }

    @Transactional
    @Override
    public void saveUser(User user) {
	template.save(user);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
	template.update(user);
    }

}
