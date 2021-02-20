package Entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Room implements Serializable {
      private int roomType;
      private double roomRate;
      public ArrayList<Customer> customers = new ArrayList<Customer>();
      
      public int getRoomType() {
            return roomType;
      }

      public void setRoomType(int roomType) {
            this.roomType = roomType;
      }

      public double getRoomRate() {
            return roomRate;
      }

      public void setRoomRate(double roomRate) {
            this.roomRate = roomRate;
      }

      public Room(int roomType, double roomRate) {
            this.roomType = roomType;
            this.roomRate = roomRate;
      }
}