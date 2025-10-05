// Represents a device in the library system. Each device has a SKU, a name, and an availability status.
 
public class Device {
    // Private variables to hold the device's SKU, name, and availability status
    private String sku;
    private String name;
    private boolean isAvailable;

    
     // Constructor to create a new device with specified details.
     //sku The stock keeping unit identifier for the device.
     //name The name of the device.
     //isAvailable The availability status of the device, true if available.
    public Device(String sku, String name, boolean isAvailable) {
        this.sku = sku; // Assign the SKU 
        this.name = name; // Assign the name 
        this.isAvailable = isAvailable; // Set the availability status
    }

    // Gets the SKU of the device and returns The SKU as a String.
    
    public String getSku() {
        return sku;
    }

    //Sets the SKU of the device.
     
    public void setSku(String sku) {
        this.sku = sku; // Update the device's SKU
    }

    // Gets the name of the device and return The name of the device as a String.
    
    public String getName() {
        return name;
    }

    //Sets the name of the device.
     
    public void setName(String name) {
        this.name = name; // Update the device's name
    }

    // Checks if the device is available for checkout and returns true if the device is available, false otherwise.
     
    public boolean isAvailable() {
        return isAvailable;
    }

   //Sets the availability of the device.
    
    public void setAvailable(boolean available) {
        this.isAvailable = available; // Update the device's availability status
    }


    @Override
    public String toString() {
        return String.format("SKU: %s - Name: %s - Available: %s", sku, name, isAvailable ? "Yes" : "No");
    }
}
