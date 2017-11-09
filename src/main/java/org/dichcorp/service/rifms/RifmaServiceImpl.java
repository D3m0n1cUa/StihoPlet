package org.dichcorp.service.rifms;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dichcorp.dao.rifms.RifmaDao;
import org.dichcorp.exception.NoFoundRifmaException;
import org.dichcorp.model.rifms.Rifma;
import org.dichcorp.model.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RifmaServiceImpl implements RifmaService {

    @Autowired
    private RifmaDao rifmaDao;

    @Override
    public List<Rifma> getUserRifms(User user) {
	if (user == null) {
	    throw new IllegalArgumentException("User can not be null.");
	}

	return rifmaDao.findAllRifmsByUser(user);
    }

    @Override
    public void saveRifma(Rifma rifma) {
	if (rifma == null) {
	    throw new IllegalArgumentException("Rime can not be null.");
	}

	if (rifma.getIdParentRifma() != null) {
	    rifma.setParentRifma(rifmaDao.findRifmaById(rifma.getIdParentRifma()));
	}

	rifma.setCreateDate(new Date());
	rifmaDao.saveRifma(rifma);
    }

    @Override
    public Rifma getRifmaById(Integer id) {
	if (id == null) {
	    throw new IllegalArgumentException("Id can not be null.");
	}

	Rifma rifma = rifmaDao.findRifmaById(id);

	if (rifma == null) {
	    throw new NoFoundRifmaException();
	}

	return rifma;
    }

    @Override
    public List<Rifma> getAllRifmsByUsers(User user) {
	if (user == null) {
	    throw new IllegalArgumentException("User can not be null.");
	}

	List<Rifma> rifms = rifmaDao.findAllRifmsByUsers(getLogins(user));
	return rifms;
    }

    private Set<String> getLogins(User user) {
	Set<String> logins = new HashSet<>();
	logins.add(user.getLogin());
	for (User u : user.getFollowing()) {
	    logins.add(u.getLogin());
	}

	return logins;
    }

}
