package pl.endproject.offerscomparator.infrastructure.userRegistration.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import pl.endproject.offerscomparator.infrastructure.userRegistration.dao.UserDao;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public boolean isUserValid(String username, String email, String password) {
        return false;
    }

    @Override
    public boolean registerUser(String username, String email, String password, String path) {

        int tokenLength = 50;
        String token = RandomStringUtils.randomAlphanumeric(tokenLength);

//        if (userDao.createUser(username, email, password, token)) {
//
//            return true;
//        }

        return false;
    }

    @Override
    public boolean activateUser(String token) {
        return false;
    }

    @Override
    public long getUserId() {
        return 0;
    }
}
