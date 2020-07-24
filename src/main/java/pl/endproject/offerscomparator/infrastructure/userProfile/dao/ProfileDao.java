package pl.endproject.offerscomparator.infrastructure.userProfile.dao;


import org.springframework.data.mongodb.repository.MongoRepository;
import pl.endproject.offerscomparator.infrastructure.userProfile.model.Profile;

import java.util.List;


public interface ProfileDao extends MongoRepository<Profile, String> {
    Profile findProfileByEmail(String email);
    Boolean existsByEmail(String email);

    @Override
    List<Profile> findAll();
}
