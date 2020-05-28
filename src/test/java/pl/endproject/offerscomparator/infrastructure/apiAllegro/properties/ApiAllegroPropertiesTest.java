package pl.endproject.offerscomparator.infrastructure.apiAllegro.properties;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;


public class ApiAllegroPropertiesTest {

    @Test(expected = HttpStatusException.class)
    public void shouldThrowHttpStatusExceptionWhileTryingToConnectWithoutParameterGiven() throws IOException {
        //when
        Jsoup.connect(ApiAllegroProperties.getAllegroEndpoint()).execute();
    }
    @Test(expected = HttpStatusException.class)
    public void shouldHttpStatusExceptionWhileTryingToConnectWhenPhraseHasWhitespace() throws IOException {
        //when
        Jsoup.connect(ApiAllegroProperties.getAllegroEndpoint()+" ").execute();
    }

    @Test()
    public void shouldNotHttpStatusExceptionWhileTryingToConnectWhenPhraseHasAnyStringOfCharacters() throws IOException {
        //when
        Assert.assertNotNull(Jsoup.connect(ApiAllegroProperties.getAllegroEndpoint()+"*").execute());
    }

}