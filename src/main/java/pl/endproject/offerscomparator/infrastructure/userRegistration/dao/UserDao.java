package pl.endproject.offerscomparator.infrastructure.userRegistration.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.endproject.offerscomparator.infrastructure.userRegistration.model.User;

public interface UserDao extends JpaRepository<User,Long> {

    User findUserByLoginAndEmail(String login, String email);
    User findUserByLoginOrEmailAndPassword(String login, String email, String password);
//    boolean createUser(String login, String email, String password, String activationToken);
//    boolean activateUser(String token);
}
