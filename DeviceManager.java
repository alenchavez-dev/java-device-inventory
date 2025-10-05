import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


 // Array managing listing, adding, editing,searching, checking out, and checking in devices.
public class DeviceManager {
    private List<Device> devices = new ArrayList<>(); // Holds all the devices

    //Hardcoded devices 
    public void loadSampleDevices() {
        // Each device added here is predefined with a SKU, name, and availability status
        devices.add(new Device("6757A", "Apple 9.7-inch iPad Pro", true));
        devices.add(new Device("93P51B", "Amazon Kindle Fire Kids Edition", true));
        devices.add(new Device("10N8C", "LeapFrog Epic Learning Tablet", true));
        devices.add(new Device("85U20", "Amazon Kindle Fire HD 8", false));
        devices.add(new Device("91H2D", "HP Envy 8 Note", true));
    }

    
    //Displays all devices/ statuses
    
    public void displayDevices() {
        System.out.println("\nLibrary Device Checkout System\n"); 

        //Checks if there are no devices to display
        if (devices.isEmpty()) {
            System.out.println("No devices available.");
            return;
        }

        //Column headers for the device list
        System.out.printf("%-4s %-10s %-40s %-15s\n", "#", "SKU", "Name", "Status");

        // Iterates over the list of devices and prints each one with its status
        for (int i = 0; i < devices.size(); i++) {
            Device device = devices.get(i);
            String availability = device.isAvailable() ? "Available" : "Checked Out";
            System.out.printf("%-4d %-10s %-40s %-15s\n", i + 1, device.getSku(), device.getName(), availability);
        }

        // Prompt the user to press Enter to continue
        promptContinue();
    }

    //Allows adding a new device to the system.
     
    public void addDevice(Scanner scanner) {
        System.out.println("\nLibrary Device Checkout System\n"); 

        //SKU input from the user
        System.out.print("Enter SKU: ");
        String sku = scanner.nextLine().toUpperCase(); // SKU input, converted to uppercase

        //Name input from the user
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        //Adding the new device to the list, assumed to be available
        devices.add(new Device(sku, name, true));
        System.out.println("Device added successfully.");

        // Prompt to continue
        promptContinue();
    }

    //Allows editing the details of an existing device based on its index.
     
    public void editDevice(Scanner scanner) {
        displayDevices(); // First display the existing devices

        System.out.print("Enter Device number to edit (1-" + devices.size() + "): ");
        int deviceNumber = scanner.nextInt();
        scanner.nextLine(); // Consuming the leftover newline character

        // Validate the device number input
        if (deviceNumber > 0 && deviceNumber <= devices.size()) {
            Device device = devices.get(deviceNumber - 1); // Getting the device object

            // User inputs for new SKU and name
            System.out.print("Enter new SKU: ");
            String newSku = scanner.nextLine().toUpperCase();
            System.out.print("Enter new name: ");
            String newName = scanner.nextLine();

            // Setting the new SKU and name
            device.setSku(newSku);
            device.setName(newName);

            System.out.println("Device updated successfully."); // Confirmation of update
        } else {
            System.out.println("Invalid device number."); // Error message if input is invalid
        }

        // Prompt to continue
        promptContinue();
    }

    //Searches for devices by substring.
    
    public void searchDevices(Scanner scanner) {
        System.out.println("\nLibrary Device Checkout System\n"); 

        // Asking the user for the search term
        System.out.print("Enter device name to search for: ");
        String searchQuery = scanner.nextLine().toLowerCase(); // Convert search input to lowercase

        // Flag to check if any device was found
        boolean found = false;

        // Displaying search results header
        System.out.printf("%-4s %-10s %-40s\n", "#", "SKU", "Name");

        // Loop through devices and display those that contain the search term in their name
        for (int i = 0; i < devices.size(); i++) {
            Device device = devices.get(i);
            if (device.getName().toLowerCase().contains(searchQuery)) {
                System.out.printf("%-4d %-10s %-40s\n", i + 1, device.getSku(), device.getName());
                found = true;
            }
        }

        // If no devices match the search term, inform the user
        if (!found) {
            System.out.println("No matching devices found.");
        }

        // Prompt to continue
        promptContinue();
    }

    // Handles checking out a device based on its index from a list of available devices.
    
    public void checkOutDevice(Scanner scanner) {
        System.out.println("\nLibrary Device Checkout System\n");
        System.out.printf("%-4s %-10s %-40s\n", "#", "SKU", "Name");

        // List to track indices of available devices
        List<Integer> availableIndices = new ArrayList<>();
        for (int i = 0; i < devices.size(); i++) {
            Device device = devices.get(i);
            if (device.isAvailable()) {
                System.out.printf("%-4d %-10s %-40s\n", i + 1, device.getSku(), device.getName());
                availableIndices.add(i);
            }
        }

        // Check if there are any available devices - if no
        if (availableIndices.isEmpty()) {
            System.out.println("No devices available for checkout.");
        } else { // if yes
            System.out.print("Enter Device number to check out: ");
            int deviceNumber = scanner.nextInt();
            scanner.nextLine();

            // Validate the device number and check out the device if valid
            if (deviceNumber < 1 || deviceNumber > devices.size() || !devices.get(deviceNumber - 1).isAvailable()) {
                System.out.println("Invalid device number or device not available.");
            } else {
                Device device = devices.get(deviceNumber - 1);
                device.setAvailable(false);
                System.out.println("Device Checked Out.");
            }
        }

        // Prompt to continue
        promptContinue();
    }

    ///Handles checking in a device based on its index from a list of checked-out devices.
     
    public void checkInDevice(Scanner scanner) {
        System.out.println("\nLibrary Device Checkout System\n");
        System.out.printf("%-4s %-10s %-40s\n", "#", "SKU", "Name");

        // List to track indices of checked-out devices
        List<Integer> checkedOutIndices = new ArrayList<>();
        for (int i = 0; i < devices.size(); i++) {
            Device device = devices.get(i);
            if (!device.isAvailable()) {
                System.out.printf("%-4d %-10s %-40s\n", i + 1, device.getSku(), device.getName());
                checkedOutIndices.add(i);
            }
        }

        // Check if there are any checked-out devices
        if (checkedOutIndices.isEmpty()) {
            System.out.println("No devices are currently checked out.");
        } else {
            System.out.print("Enter Device number to check in: ");
            int deviceNumber = scanner.nextInt();
            scanner.nextLine();

            // Validate the device number and check in the device if valid
            if (deviceNumber < 1 || deviceNumber > devices.size() || devices.get(deviceNumber - 1).isAvailable()) {
                System.out.println("Invalid device number or device not currently checked out.");
            } else {
                Device device = devices.get(deviceNumber - 1);
                device.setAvailable(true);
                System.out.println("Device Checked In.");
            }
        }

        // Prompt to continue
        promptContinue();
    }

    // Prompts the user to press Enter to continue. This method ensures that the user
    //has a chance to read the output before the program proceeds.
     
    private void promptContinue() {
        System.out.println("Press Enter to continue...");
        try {
            System.in.read(); // Reads a single character from the console
        } catch (Exception e) {
            System.out.println("Error reading input."); // Error handling
        }
    }
}

