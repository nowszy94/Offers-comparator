package pl.endproject.offerscomparator.infrastructure.apiAllegro.properties;

public class ApiAllegroProperties {
    private ApiAllegroProperties() {
    }

    private static final String allegroEndpoint = "https://allegro.pl/listing?string=";

    public static String getAllegroEndpoint() {
        return allegroEndpoint;
    }
}
