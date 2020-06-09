package pl.endproject.offerscomparator.infrastructure.userRegistration.util;

import org.junit.Test;
import pl.endproject.offerscomparator.infrastructure.userRegistration.configuration.MailTrapProperties;

import javax.mail.Session;

import static org.junit.Assert.*;

public class EmailUtilTest {

    @Test
    public void sendActivationEmail() {
        //given
        String email="test@test.pl";
        String token="testToken";
        String contextPath="/testPath";
        Session mailProperties = MailTrapProperties.config();
        //when
        EmailUtil.sendActivationEmail(email,token,contextPath, mailProperties);
    }
}