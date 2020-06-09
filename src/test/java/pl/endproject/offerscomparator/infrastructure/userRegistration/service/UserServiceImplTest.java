package pl.endproject.offerscomparator.infrastructure.userRegistration.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pl.endproject.offerscomparator.OffersComparatorApplication;
import pl.endproject.offerscomparator.infrastructure.userRegistration.config.H2TestProfileJPAConfig;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        OffersComparatorApplication.class,
        H2TestProfileJPAConfig.class})
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void isUserValid() {
    }

    @Test
    public void registerUser() {
    }

    @Test
    public void activateUser() {
    }

    @Test
    public void getUserId() {
    }

    @Test
    public void getUserList() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void delete() {
    }
}