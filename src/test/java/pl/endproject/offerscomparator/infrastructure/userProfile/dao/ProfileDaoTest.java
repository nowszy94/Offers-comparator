package pl.endproject.offerscomparator.infrastructure.userProfile.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProfileDaoTest {

    @Autowired
    private ProfileDao profileDao;

    @Test
    public void findProfileByEmail() {
        //given
        String mail = "gadzinski@test.pl";
        //then
        assertThat(profileDao.findProfileByEmail(mail),hasProperty("role",is("user")));
    }

    @Test
    public void shouldReturnTrueAfterIsGivenExistingMail() {
        //given
        String mail = "gadzinski@test.pl";
        //then
        assertThat(profileDao.existsByEmail(mail),equalTo(true));
    }

    @Test
    public void shouldReturnFalseAfterIsGivenNoExistingMail() {
        //given
        String mail = "noexist@test.pl";
        //then
        assertThat(profileDao.existsByEmail(mail),equalTo(false));
    }

    @Test
    public void shouldGetListOfProfiles() {
        //then
        assertThat(profileDao.findAll().size(),is(greaterThan(0)) );

    }
}