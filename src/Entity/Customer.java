package Entity;

import java.io.Serializable;
import java.time.LocalDate;

public class Customer implements Serializable {
      /**
      *
      */
      private static final long serialVersionUID = -1213624173774781671L;
      private String name;
      private String dateOfBirth;
      private String id;
      private String checkInTime;
      private String checkOutTime;

      public String getName() {
            return name;
      }

      public void setName(String name) {
            this.name = name;
      }

      public String getDateOfBirth() {
            return dateOfBirth;
      }

      public void setDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
      }

      public String getId() {
            return id;
      }

      public void setId(String id) {
            this.id = id;
      }

      public Customer(String name, String dateOfBirth, String id) {
            this.name = name;
            this.dateOfBirth = dateOfBirth;
            this.id = id;
      }

      public LocalDate getCheckInTime() {
            return LocalDate.parse(checkInTime);
      }

      public void setCheckInTime(String checkInTime) {
            this.checkInTime = checkInTime;
      }

      public LocalDate getCheckOutTime() {
            return LocalDate.parse(checkOutTime);
      }

      public void setCheckOutTime(String checkOutTime) {
            this.checkOutTime = checkOutTime;
      }
}

