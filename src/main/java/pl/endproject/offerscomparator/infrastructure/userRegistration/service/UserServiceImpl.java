package pl.endproject.offerscomparator.infrastructure.userRegistration.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;
import pl.endproject.offerscomparator.infrastructure.userRegistration.configuration.MailYandexProperties;
import pl.endproject.offerscomparator.infrastructure.userRegistration.dao.UserDao;
import pl.endproject.offerscomparator.infrastructure.userRegistration.model.User;
import pl.endproject.offerscomparator.infrastructure.userRegistration.util.EmailUtil;
import pl.endproject.offerscomparator.infrastructure.userRegistration.util.PasswordUtil;
import pl.endproject.offerscomparator.infrastructure.userRegistration.util.TextValidator;

import javax.mail.Session;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private User user;
    private Session switchMailSource = MailYandexProperties.config();
    private String failureCause;

    public String getFailureCause() {
        return failureCause;
    }

    public void setFailureCause(String failureCause) {
        this.failureCause = failureCause;
    }

    public void setSwitchMailSource(Session switchMailSource) {
        this.switchMailSource = switchMailSource;
    }


    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public boolean isUserValid(String login, String email, String password) {
        this.user = userDao.findUserByLoginOrEmail(toLowerCase(login), toLowerCase(email));

        return user != null && PasswordUtil.checkPassword(password, user.getPassword()) && user.getActive();
    }

    private static String toLowerCase(String text) {
        try {
            return text.toLowerCase();
        } catch (NullPointerException e) {
            return text;
        }
    }

    @Override
    public boolean registerUser(String login, String email, String password, String path) {
        String token = uniqueTokenGenerator();
        String newLogin = toLowerCase(login);
        String newEmail = toLowerCase(email);
        TextValidator textValidator = new TextValidator();

        try {
            if (textValidator.loginValidate(newLogin) & textValidator.emailValidate(newEmail)) {
                if (userDao.findUserByLoginOrEmail(newLogin, newEmail) == null) {
                    userDao.save(new User(newLogin, PasswordUtil.hashPassword(password), newEmail, token));
                    EmailUtil.sendActivationEmail(newEmail, token, path, switchMailSource);
                    return true;
                } else {
                    if (userDao.existsByLogin(toLowerCase(login))) {
                        setFailureCause("loginExistInDB");
                    } else {
                        setFailureCause("emailExistInDB");
                    }
                    return false;
                }
            }
            setFailureCause("illegalCharactersUsed");
            return false;

        } catch (IncorrectResultSizeDataAccessException e) {
            setFailureCause("loginAndEmailExistInDB");
            return false;
        }
    }

    private String uniqueTokenGenerator() {
        int tokenLength = 50;
        String uniqueToken;
        do {
            uniqueToken = RandomStringUtils.randomAlphanumeric(tokenLength);
        } while (userDao.findUserByToken(uniqueToken) != null);
        return uniqueToken;
    }

    @Override
    public boolean activateUser(String token) {
        this.user = userDao.findUserByToken(token);
        if (user != null && !user.getActive()) {

                user.setActive(true);
                userDao.save(user);
                return true;
            }
        return false;
    }

    @Override
    public long getUserId(String loginOrEmail) {
        return userDao.findUserByLoginOrEmail(loginOrEmail, loginOrEmail).getUser_id();
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
