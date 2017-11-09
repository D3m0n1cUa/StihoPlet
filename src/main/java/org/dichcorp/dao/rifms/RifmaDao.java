package org.dichcorp.dao.rifms;

import java.util.List;
import java.util.Set;

import org.dichcorp.model.rifms.Rifma;
import org.dichcorp.model.users.User;

public interface RifmaDao {
    List<Rifma> findAllRifmsByUser(User user);

    List<Rifma> findAllRifmsByUsers(Set<String> logins);

    Rifma findRifmaById(Integer id);

    void saveRifma(Rifma rifma);
}
