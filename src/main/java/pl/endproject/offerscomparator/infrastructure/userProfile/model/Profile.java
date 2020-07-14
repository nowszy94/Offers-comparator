package pl.endproject.offerscomparator.infrastructure.userProfile.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@Document
public class Profile {
    @Id
    private String id;
    private String email;
    private String login;
    private String role;
    private BasicInformation basicInformation;
    private Address address;
}
