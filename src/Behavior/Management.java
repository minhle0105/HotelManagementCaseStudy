package Behavior;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.Duration;
import java.util.Scanner;

import Entity.Customer;
import Entity.Room;

public class Management {
      static Room[] rooms = new Room[3];
      Scanner sc = new Scanner(System.in);
      CreditCardValidation ccv = new CreditCardValidation();
      final String filePath = "rooms.txt";
      static {
            rooms[0] = new Room(1, 500000);
            rooms[1] = new Room(2, 1000000);
            rooms[2] = new Room(3, 1500000);
      }

      public void UpdateFile() throws IOException {
            File file = new File(filePath);
            if (!file.exists()) {
                  file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            try {
                  for (Room room : rooms) {
                        objectOutputStream.writeObject(room);
                  }
            } catch (Exception e) {
                  System.out.println(e.getMessage());
                  System.out.println("Error");
            }

            objectOutputStream.close();
            fileOutputStream.close();
      }

      public void checkIn() throws IOException {
            System.out.println("Enter customer name");
            String customerName = sc.nextLine();
            System.out.println("Enter customer date of birth. For example, May 1st 2020, please enter 2020-05-01");
            String customerDoB = sc.nextLine();
            System.out.println("Enter customer ID");
            String customerID = sc.nextLine();
            System.out.println("Enter check in date (YYYY-MM-DD). For example, May 1st 2020, please enter 2020-05-01");
            String checkInDate = sc.nextLine();
            Customer c = new Customer(customerName, customerDoB, customerID);
            c.setCheckInTime(checkInDate);
            System.out.println("Select room type");
            Room room = selectRoom();
            room.customers.add(c);
            UpdateFile();
      }

      public Room selectRoom() {
            try {
                  System.out.println("1 - Standard - Rate 500,000");
                  System.out.println("2 - VIP - Rate 1,000,000");
                  System.out.println("3 - Luxury - Rate 1,500,000");
                  int roomType = Integer.parseInt(sc.nextLine());
                  switch (roomType) {
                        case 1:
                              return rooms[0];
                        case 2:
                              return rooms[1];
                        case 3:
                              return rooms[2];

                  }
            } catch (Exception e) {
                  System.out.println("Invalid option");
                  selectRoom();
            }
            return null;
      }

      public void printRooms(Room[] rooms) {
            System.out.println("Room list");
            for (Room room : rooms) {
                  System.out.println("___________________");
                  System.out.println("Room " + room.getRoomType());
                  if (room.customers.size() == 0) {
                        System.out.println("(Empty)");
                  } else {
                        for (Customer customer : room.customers) {
                              System.out.println("Name: " + customer.getName());
                              System.out.println("Date of Birth: " + customer.getDateOfBirth());
                              System.out.println("ID: " + customer.getId());
                              System.out.println("Check in date: " + customer.getCheckInTime());
                              System.out.println("----");
                        }
                  }

            }
      }

      public Room[] printFile() {
            try {
                  FileInputStream fileInputStream = new FileInputStream(filePath);
                  ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                  for (int i = 0; i < rooms.length; i++) {
                        rooms[i] = (Room) objectInputStream.readObject();
                  }

                  objectInputStream.close();
                  fileInputStream.close();
                  return rooms;

            } catch (Exception e) {
                  e.printStackTrace();
                  return null;
            }
      }

      public void checkOut() throws IOException {
            rooms = this.printFile();
            System.out.println("Which room is checked out?");
            Room roomCheckOut = selectRoom();

            if (roomCheckOut.customers.size() == 0) {
                  System.out.println("Empty room");
                  return;
            }
            System.out.println("Enter check out date (YYYY-MM-DD)");
            String checkOutDate = sc.nextLine();
            Customer customerCheckingOut = roomCheckOut.customers.get(0);
            customerCheckingOut.setCheckOutTime(checkOutDate);

            Duration stayDuration = Duration.between(customerCheckingOut.getCheckInTime().atStartOfDay(),
                        customerCheckingOut.getCheckOutTime().atStartOfDay());

            long dateStay = stayDuration.toDays();
            double totalFee = dateStay * roomCheckOut.getRoomRate();
            String totalFeeString = String.format("%,.2f", totalFee);

            System.out.println("Total fee is " + totalFeeString);
            System.out.println("Please pay with your credit card");
            while (true) {
                  boolean customerCardValid = ccv.validate();
                  if (customerCardValid) {
                        String issuedCompany = ccv.getIssuedCompany();
                        System.out.printf("Your payment made with %s credit card has been approved. Thank you",
                                    issuedCompany);
                        System.out.println();
                        break;
                  } else {
                        System.out.println("Your card number is invalid. Please try again");
                  }
            }

            roomCheckOut.customers.clear();
            System.out.printf("Room %d has been checked out successfully.", roomCheckOut.getRoomType());
            System.out.println();
            UpdateFile();
      }

      public void changeInfo() throws IOException {
            rooms = this.printFile();
            System.out.println("Enter room that has customer to change information");
            Room roomHasCustomer = selectRoom();
            System.out.println("Enter customer ID");
            String customerIDToChange = sc.nextLine();
            int customerChanged = 0;
            for (Customer customer : roomHasCustomer.customers) {
                  if (customerIDToChange.equals(customer.getId())) {
                        System.out.println("Enter new information");
                        System.out.println("Enter customer name");
                        String newCustomerName = sc.nextLine();
                        System.out.println("Enter customer date of birth");
                        String newCustomerDoB = sc.nextLine();
                        System.out.println("Enter customer ID");
                        String newCustomerID = sc.nextLine();
                        System.out.println("Enter check in date (YYYY-MM-DD)");
                        String newCheckInDate = sc.nextLine();
                        customer.setName(newCustomerName);
                        customer.setId(newCustomerID);
                        customer.setDateOfBirth(newCustomerDoB);
                        customer.setCheckInTime(newCheckInDate);
                        customerChanged += 1;
                  }
            
            }
            if (customerChanged == 0) {
                  System.out.println("No customer found");
            } else {
                  System.out.println("Customer information updated successfully");
            }
            UpdateFile();
      }
}
