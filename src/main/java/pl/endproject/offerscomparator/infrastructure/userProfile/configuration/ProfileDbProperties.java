package pl.endproject.offerscomparator.infrastructure.userProfile.configuration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class ProfileDbProperties {

    protected ProfileDbProperties() {
    }

    private static final String password="Ey5swfdMGk9u4r3N";
    private static final String dbname="user";

    ConnectionString connString = new ConnectionString(
            "mongodb+srv://CaptainTest:"+password+"@offerscomparatorsuserpr.smya1.mongodb.net/"+dbname+"?retryWrites=true&w=majority"

    );
    MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(connString)
            .retryWrites(true)
            .build();
    MongoClient mongoClient = MongoClients.create(settings);
    MongoDatabase database = mongoClient.getDatabase(dbname);
}
