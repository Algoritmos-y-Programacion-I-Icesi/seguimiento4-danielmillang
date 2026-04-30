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

    /**
     * Adds a new device to the system. It checks if the device ID already exists and if there is space on the specified floor before adding the device. If the device ID already exists, it prints a message and does not add the device. If there is no space on the specified floor, it prints a message indicating that the building is full.
     * @param deviceID The unique identifier for the device.
     * @param consumption The consumption value of the device.
     * @param description A brief description of the device.
     * @param floor The floor number where the device is located (0-11).
     */
    public void addDevice (String deviceID, double consumption, String description, int floor) {

       
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


    /**
     * Registers a new event for a specific device. It searches for the device by its ID and, if found, creates a new event with the provided date and duration, and adds it to the device's list of events. If the device is not found, it prints a message indicating that the device with the specified ID was not found.
     * @param deviceID The unique identifier for the device to which the event will be added.
     * @param eventDate The date and time when the event occurred.
     * @param duration The duration of the event in hours.
     */
    public void addEvent(String deviceID, LocalDateTime eventDate, double duration) {
        
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


    /**
     * Updates the consumption value of a specific device. It searches for the device by its ID and, if found, updates its consumption value with the new value provided. If the device is not found, it prints a message indicating that the device with the specified ID was not found.
     * @param deviceID The unique identifier for the device whose consumption value will be updated.
     * @param newConsumption The new consumption value to be set for the device.
     */
    public void updateComsumptionValue (String deviceID, double newConsumption) {
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


    /**
     * Calculates the total consumption for a specific device based on its events. It searches for the device by its ID and, if found, iterates through its list of events to calculate the total consumption by multiplying the duration of each event by the device's consumption value. Finally, it prints the total consumption for the device. If the device is not found, it prints a message indicating that the device with the specified ID was not found.
     * @param deviceID The unique identifier for the device whose total consumption will be calculated.
     */
    public void calculateDeviceConsumption (String deviceID) {
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


    /**
     * Identifies worn-out devices based on a specified consumption threshold. It iterates through all devices in the building and calculates their total consumption by summing the product of the duration of each event and the device's consumption value. If the total consumption exceeds the given threshold, it prints a message indicating that the device is worn out along with its total consumption. The threshold is set to 0.3 in this implementation.
     * @param threshold The consumption threshold used to determine if a device is worn out. Devices with total consumption exceeding this threshold will be identified as worn out.
     */
    public void identifyWornOutDevices (double threshold) {

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
