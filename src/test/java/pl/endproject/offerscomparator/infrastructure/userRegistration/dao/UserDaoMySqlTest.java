package pl.endproject.offerscomparator.infrastructure.userRegistration.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class UserDaoMySqlTest {

    @Autowired
    private UserDao userDao;

//    @Before
//    public void init() {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("user_access_test");
//        EntityManager em = emf.createEntityManager();
//
//
//    }



    @Test
    public void findUserByLoginAndEmail() {
        System.out.println(userDao.findAll());
        Assert.assertEquals(3,userDao.findAll().size());
    }

    @Test
    public void findUserByLoginOrEmailAndPassword() {
    }
}