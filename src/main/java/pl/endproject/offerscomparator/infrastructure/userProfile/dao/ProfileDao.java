package pl.endproject.offerscomparator.infrastructure.userProfile.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import pl.endproject.offerscomparator.infrastructure.userProfile.model.Profile;

@Component
public interface ProfileDao extends MongoRepository<Profile, String> {
}
