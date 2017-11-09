package org.dichcorp.service.rifms;

import java.util.List;

import org.dichcorp.model.rifms.Rifma;
import org.dichcorp.model.users.User;

public interface RifmaService {
    List<Rifma> getUserRifms(User user);

    List<Rifma> getAllRifmsByUsers(User user);

    Rifma getRifmaById(Integer id);

    void saveRifma(Rifma rifma);
}
