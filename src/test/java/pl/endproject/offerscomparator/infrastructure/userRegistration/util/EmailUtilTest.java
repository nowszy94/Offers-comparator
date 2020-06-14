package pl.endproject.offerscomparator.infrastructure.userRegistration.util;

import org.junit.Test;
import pl.endproject.offerscomparator.infrastructure.userRegistration.configuration.MailTrapProperties;

import javax.mail.Session;

public class EmailUtilTest {

    @Test
    public void sendActivationEmail() {
        //given
        String email="test@test.com";
        String token="testToken";
        String contextPath="http://localhost:8080/signup";
        Session mailProperties = MailTrapProperties.config();
        //when
        EmailUtil.sendActivationEmail(email,token,contextPath, mailProperties);
    }
}