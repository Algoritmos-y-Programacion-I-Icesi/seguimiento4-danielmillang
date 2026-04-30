package model;
import java.time.LocalDateTime;
import java.util.ArrayList;
public class Controller {

    private final int FLOORS = 12;
    private final int DEVICES_PER_FLOOR = 4;
    private Device[][] devices;

    public Controller() {
        this.devices = new Device[FLOORS][DEVICES_PER_FLOOR];
    }

    public static void addDevice (String deviceID, double consumption, String description, int floor) {

       
        for (int j = 0; j < DEVICES_PER_FLOOR; j++) {
            if (devices[floor][j] != null && devices[floor][j].getDeviceID().equals(deviceID)) {
                System.out.println("Device with ID " + deviceID + " already exists.");
                return;
            }
        }
    

        Device newDevice = new Device(deviceID, consumption, description);
    
        for (int j = 0; j < DEVICES_PER_FLOOR; j++) {
            if (devices[floor][j] == null) {
                devices[floor][j] = newDevice;
                return;
            }
        }
        
        System.out.println("Cannot add more devices, the building is full.");
    }


    public static void addEvent(String deviceID, LocalDateTime eventDate, double duration) {
        
        for (int i = 0; i < FLOORS; i++) {
            for (int j = 0; j < DEVICES_PER_FLOOR; j++) {
                if (devices[i][j] != null && devices[i][j].getDeviceID().equals(deviceID)) {
                    Event newEvent = new Event(eventDate, duration);
                    devices[i][j].getEvents().add(newEvent);
                    return;
                }
            }
        }
        System.out.println("Device with ID " + deviceID + " not found.");
    }


    public static void updateComsumptionValue (String deviceID, double newConsumption) {
        for (int i = 0; i < FLOORS; i++) {
            for (int j = 0; j < DEVICES_PER_FLOOR; j++) {
                if (devices[i][j] != null && devices[i][j].getDeviceID().equals(deviceID)) {
                    devices[i][j].setConsumption(newConsumption);
                    return;
                }
            }
        }
        System.out.println("Device with ID " + deviceID + " not found.");
    }


    public static void calculateDeviceConsumption (String deviceID) {
        for (int i = 0; i < FLOORS; i++) {
            for (int j = 0; j < DEVICES_PER_FLOOR; j++) {
                if (devices[i][j] != null && devices[i][j].getDeviceID().equals(deviceID)) {
                    double totalConsumption = 0;
                    for (Event event : devices[i][j].getEvents()) {
                        totalConsumption += event.getDuration() * devices[i][j].getConsumption();
                    }
                    System.out.println("Total consumption for device " + deviceID + ": " + totalConsumption);
                    return;

                }
            }
        }
        System.out.println("Device with ID " + deviceID + " not found.");
    }


    public static void identifyWornOutDevices (double threshold) {

        threshold = 0.3;

        for (int i = 0; i < FLOORS; i++) {
            for (int j = 0; j < DEVICES_PER_FLOOR; j++) {
                if (devices[i][j] != null) {
                    double totalConsumption = 0;
                    for (Event event : devices[i][j].getEvents()) {
                        totalConsumption += event.getDuration() * devices[i][j].getConsumption();
                    }
                    if (totalConsumption > threshold) {
                        System.out.println("Device " + devices[i][j].getDeviceID() + " is worn out with total consumption: " + totalConsumption);
                    }
                }
            }
        }
    }

}
