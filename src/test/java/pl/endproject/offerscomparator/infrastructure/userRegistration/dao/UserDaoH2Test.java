package pl.endproject.offerscomparator.infrastructure.userRegistration.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pl.endproject.offerscomparator.OffersComparatorApplication;
import pl.endproject.offerscomparator.infrastructure.userRegistration.config.H2TestProfileJPAConfig;
import pl.endproject.offerscomparator.infrastructure.userRegistration.model.User;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        OffersComparatorApplication.class,
        H2TestProfileJPAConfig.class})
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class UserDaoH2Test {

    @Autowired
    private UserDao userDao;

    @Test
    public void shouldGetInitializeUsersAfterConnectingTheH2DB() {

        //then
        assertThat(userDao.findAll().size()).isEqualTo(3);
    }

    @Test
    public void shouldAddUserToDBAfterCreatingUser() {
        //given
        User testUser = new User(4L,"NewTestUser","NewTesterPassword","test@email.com","1qazxsw2",false,0, LocalDate.of(2020,5,30),"user");
        //when
        userDao.save(testUser);
        System.out.println(userDao.findAll());
        //then
        assertThat(userDao.findById(4L).get().getLogin()).isEqualTo("NewTestUser");
//        Assert.assertEquals(2,userDao.findAll().size());
    }

    @Test
    public void findUserByLoginOrEmailAndPassword() {
    }
}