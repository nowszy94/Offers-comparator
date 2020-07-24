package pl.endproject.offerscomparator.infrastructure.userProfile.service;

import org.springframework.stereotype.Component;
import pl.endproject.offerscomparator.infrastructure.userProfile.dao.ProfileDao;
import pl.endproject.offerscomparator.infrastructure.userProfile.model.Profile;

@Component
public class ProfileServiceImpl {
    private ProfileDao profileDao;
    private Profile profile;

    public ProfileServiceImpl(ProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    public Profile getProfile (String email){
        return profileDao.findProfileByEmail(email);
    }

}
