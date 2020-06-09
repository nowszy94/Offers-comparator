package pl.endproject.offerscomparator.infrastructure.userRegistration.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import pl.endproject.offerscomparator.infrastructure.userRegistration.configuration.MailTrapProperties;
import pl.endproject.offerscomparator.infrastructure.userRegistration.dao.UserDao;
import pl.endproject.offerscomparator.infrastructure.userRegistration.model.User;
import pl.endproject.offerscomparator.infrastructure.userRegistration.util.EmailUtil;
import pl.endproject.offerscomparator.infrastructure.userRegistration.util.PasswordUtil;

import javax.mail.Session;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private User user;
    private Session switchMailSource= MailTrapProperties.config();

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public boolean isUserValid(String login, String email, String password) {
        this.user = userDao.findUserByLoginOrEmail(login, email);

        return user != null && PasswordUtil.checkPassword(password, user.getPassword()) && user.getActive();
    }

    @Override
    public boolean registerUser(String login, String email, String password, String path) {

        int tokenLength = 50;
        String token = RandomStringUtils.randomAlphanumeric(tokenLength);
        if(userDao.findUserByLoginOrEmail(login,email)!=null){
        userDao.save(new User(login,PasswordUtil.hashPassword(password),email,token));
        EmailUtil.sendActivationEmail(login, token, path,switchMailSource);
            return true;
        }
        return false;
    }

    @Override
    public boolean activateUser(String token) {
        this.user = userDao.findUserByToken(token);
        if (user != null) {
            user.setActive(true);
            userDao.save(user);
            return true;
        }
        return false;
    }

    @Override
    public long getUserId(String loginOrEmail) {
        return userDao.findUserByLoginOrEmail(loginOrEmail,loginOrEmail).getUser_id();
    }


    public List<User> getUserList() {
        return userDao.findAll();
    }



    public Optional<User> findById(long id) {
        return userDao.findById(id);
    }

    public void delete(User user) {
        userDao.delete(user);
    }
}
