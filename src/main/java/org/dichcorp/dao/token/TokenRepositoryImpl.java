package org.dichcorp.dao.token;

import java.util.Date;
import java.util.List;

import org.dichcorp.model.users.PersistentLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class TokenRepositoryImpl implements PersistentTokenRepository {

    @Autowired
    private HibernateTemplate template;

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
	PersistentLogin persistentLogin = new PersistentLogin();
	persistentLogin.setSeries(token.getSeries());
	persistentLogin.setUsername(token.getUsername());
	persistentLogin.setToken(token.getTokenValue());
	persistentLogin.setLast_used(token.getDate());
	template.save(persistentLogin);

    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
	PersistentLogin persistentLogin = template.get(PersistentLogin.class, series);
	if (persistentLogin == null) {
	    return;
	}

	persistentLogin.setToken(tokenValue);
	persistentLogin.setLast_used(lastUsed);
	template.update(persistentLogin);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
	PersistentLogin persistentLogin = template.get(PersistentLogin.class, seriesId);
	return persistentLogin == null ? null : persistentLogin.createPersistentRemeberMeToken();
    }

    @Override
    public void removeUserTokens(String username) {
	List<?> allTokens = template.find("FROM PersistentLogin WHERE username = ?", username);
	if (!allTokens.isEmpty()) {
	    template.deleteAll(allTokens);
	}
    }

}
