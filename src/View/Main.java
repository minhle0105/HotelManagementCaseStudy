package View;

import java.io.Console;
import java.util.Scanner;

import Behavior.Management;
import Behavior.Password;
import Entity.Room;

public class Main {
      static Scanner sc = new Scanner(System.in);
      static Console console = System.console();
      static Password p = new Password();
      
      public static void logIn() {
            boolean notRegistered = true;
            while (notRegistered) {
                  System.out.println("Enter username");
                  String userName = sc.nextLine();
                  System.out.println("Enter password");
                  String userPassword = new String(console.readPassword());
                  if (userName.equals("admin")) {
                        if (p.validatePassword(userPassword)) {
                              notRegistered = false;
                              System.out.println("Successfully Logged In");
                              showMenu();
                        }
                  }
                  else {
                        System.out.println("Incorrect UserName or Password");
                  }
            }
      }

      public static void showMenu() {
            boolean condition = true;
            Management m = new Management();
            while (condition) {
                  try {
                        System.out.println("Select from following");
                        System.out.println("1 - Check In");
                        System.out.println("2 - Show customer list");
                        System.out.println("3 - Check Out");
                        System.out.println("4 - Update customer information");
                        System.out.println("5 - Log out");
                        int userChoice = Integer.parseInt(sc.nextLine());
                        System.out.println("______________");
                        switch (userChoice) {
                              case 1:
                                    m.checkIn();
                                    break;
                              case 2:
                                    Room[] rooms = m.printFile();
                                    m.printRooms(rooms);
                                    break;
                              case 3:
                                    m.checkOut();
                                    break;
                              case 4:
                                    m.changeInfo();
                                    break;
                              case 5:
                                    System.out.println("Logged out");
                                    condition = false;
                        }
                  } catch (Exception e) {
                        System.out.println("Invalid choice");
                        System.out.println(e.getMessage());
                  }
            }
      }

      public static void main(String[] args) {
            logIn();
      }
}
