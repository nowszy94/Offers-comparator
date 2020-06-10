package pl.endproject.offerscomparator.infrastructure.userRegistration.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pl.endproject.offerscomparator.OffersComparatorApplication;
import pl.endproject.offerscomparator.infrastructure.userRegistration.config.H2TestProfileJPAConfig;
import pl.endproject.offerscomparator.infrastructure.userRegistration.configuration.MailTrapProperties;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        OffersComparatorApplication.class,
        H2TestProfileJPAConfig.class})
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Before
    public void setUp() {
        userService.setSwitchMailSource(MailTrapProperties.config());
    }

    @Test
    public void isUserValid() {
                //then
        for (TestCase singleTest : testCasesSelectedParametersAreCovered()) {
            assertThat(userService.isUserValid(singleTest.login,singleTest.email,singleTest.password1)).isEqualTo(singleTest.result);
        }
    }

    @Test
    public void registerUser() {
        String login = "Anna";
        String email = "j@test.pl";
        String password="testPassword";
        String path="/randomPath";

        Boolean actual = userService.registerUser(login,email,password,path);
        assertThat(actual).isTrue();
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

    private List<TestCase> testCasesSelectedParametersAreCovered() {
        return Arrays.asList(
                new TestCase("Marek", "Jan",null,true),
                new TestCase("Marek", "Nowak","mj@test.pl",false),
                new TestCase(null, "Jan","mj@test.pl",true),
                new TestCase("Marek", "Jan","mj@test.pl",true),
                new TestCase("Marek", "Jan","Marek",true),
                new TestCase("mj@test.pl", "Jan","mj@test.pl",true),
                new TestCase("Marek", "Jan",null,true),
                new TestCase(null, "Jan",null,false),
                new TestCase("Jan", "Jan",null,false),
                new TestCase(null, "Janek","mj@test.pl",false)
        );
    }

    private List<TestCase> testCasesRegistrationFailed() {
        return Arrays.asList(
                new TestCase("Marek", "mj@test.pl","userExistInDB"),
                new TestCase("Marek", "difmj@test.pl","loginExistInDB"),
                new TestCase("RandomName", "mj@test.pl","emailExistInDB"),
                new TestCase("Anna", "mj@test.pl","loginAndEmailExistInDB"),
                new TestCase("Marek", "difmj@test.pl","loginExistInDB"),
                new TestCase("Mar ek", "difmj@test.pl","loginContain")

        );
    }


    private static class TestCase {
        private String login;
        private String password1;
        private String email;
        private String password2;
        private Boolean result;
        private String registrationStatus;


        public TestCase(String login, String password1, String email,Boolean result) {
            this.login = login;
            this.password1 = password1;
            this.email = email;
            this.result=result;
        }

        public TestCase(String login, String email, String registrationStatus) {
            this.login = login;
            this.email = email;
            this.registrationStatus = registrationStatus;
        }
    }
}