package org.dichcorp.dao.rifms;

import java.util.List;
import java.util.Set;

import org.dichcorp.model.rifms.Rifma;
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
public class RifmaDaoImpl implements RifmaDao {

    @Autowired
    private HibernateTemplate template;

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public List<Rifma> findAllRifmsByUser(User user) {
	List<Rifma> rifmas = (List<Rifma>) template.find("FROM Rifma WHERE user.login=? order by createDate desc",
		user.getLogin());
	return rifmas;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Rifma> findAllRifmsByUsers(Set<String> logins) {
	List<Rifma> rifms = template.execute(new HibernateCallback<List<Rifma>>() {

	    @Override
	    public List<Rifma> doInHibernate(Session session) throws HibernateException {
		Query query = session
			.createQuery("FROM Rifma rifma WHERE rifma.user.login IN (:logins) order by createDate desc")
			.setParameter("logins", logins);
		return query.getResultList();
	    }

	});
	return rifms;
    }

    @Transactional
    @Override
    public void saveRifma(Rifma rifma) {
	template.save(rifma);
    }

    @Transactional(readOnly = true)
    @Override
    public Rifma findRifmaById(Integer id) {
	@SuppressWarnings("unchecked")
	List<Rifma> rifmas = (List<Rifma>) template.find("FROM Rifma WHERE idRifm=?", id);
	if (rifmas.isEmpty()) {
	    return null;
	} else {
	    Rifma rifma = rifmas.get(0);
	    template.initialize(rifma.getChildRifms());
	    return rifma;
	}
    }

}
