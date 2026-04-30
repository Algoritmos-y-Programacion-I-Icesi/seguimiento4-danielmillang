package model;
import java.util.ArrayList;
import java.time.LocalDateTime;
import model.Event;

public class Device {
        private String deviceID;
        private double consumption;
        private String description;
        private ArrayList<Event> events;
    

        public Device(String deviceID, double consumption, String description){
            this.deviceID = deviceID;
            this.consumption = consumption;
            this.description = description;
            this.events = new ArrayList<Event>();
        }

        public String getDeviceID() {
            return deviceID;
        }

        public void setDeviceID(String deviceID) {
            this.deviceID = deviceID;
        }

        public double getConsumption() {
            return consumption;
        }

        public void setConsumption(double consumption) {
            this.consumption = consumption;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
}
