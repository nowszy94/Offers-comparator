package pl.endproject.offerscomparator.infrastructure.userProfile.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BasicInformation {
   private String firstName;
   private String lastName;
   private String gender;
   private LocalDate birthDate;
   private String phoneNumber;
}
