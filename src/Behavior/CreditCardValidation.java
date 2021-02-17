package Behavior;

import java.util.ArrayList;
import java.util.Scanner;

public class CreditCardValidation {
      String creditCardNumber;
      String issuedCompany;
      static Scanner sc = new Scanner(System.in);

      public boolean validate() {
            boolean cardIsValid;
            setCreditCardNumber();
            int counter1 = creditCardNumber.length() - 1;
            int counter2 = creditCardNumber.length() - 2;
            int sum1 = 0;
            int sum2 = 0;
            ArrayList<Integer> outcome = new ArrayList<>();
            while (counter1 >= 0) {
                  sum1 += Character.getNumericValue(creditCardNumber.charAt(counter1));
                  counter1 = counter1 - 2;
            }
            while (counter2 >= 0) {
                  if (Character.getNumericValue(creditCardNumber.charAt(counter2)) * 2 > 9) {
                        outcome.add(Character.getNumericValue(creditCardNumber.charAt(counter2)) * 2 - 9);
                  }
                  else {
                        outcome.add(Character.getNumericValue(creditCardNumber.charAt(counter2)) * 2);
                  }
                  counter2 -= 2;
            }
            for (int i = 0; i < outcome.size(); i++) {
                  sum2 += outcome.get(i);
            }

            if ((sum1+sum2) % 10 == 0) {
                  cardIsValid = true;
            }
            else {
                  cardIsValid = false;
            }
            return cardIsValid;

      }

      public String getCreditCardNumber() {
            return creditCardNumber;
      }

      public void setCreditCardNumber() {
            System.out.println("Enter credit card number");
            String userInput = sc.nextLine();
            this.creditCardNumber = userInput;
      }

      public String getIssuedCompany() {
            switch (Character.getNumericValue(creditCardNumber.charAt(0))) {
                  case 3:
                        issuedCompany = "American Express";
                        break;
                  case 4:
                        issuedCompany = "Visa";
                        break;
                  case 5:
                        issuedCompany = "MasterCard";
                        break;
                  case 6:
                        issuedCompany = "Discover";
                        break;
                  default:
                        issuedCompany = "UNRECOGNIZED";
            }
            return issuedCompany;
      }
}

