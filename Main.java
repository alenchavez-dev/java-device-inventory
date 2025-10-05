import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        // Scanner object to handle input 
        Scanner scanner = new Scanner(System.in);

        // Create an instance of DeviceManager to manage all device operations
        DeviceManager deviceManager = new DeviceManager();

        // Load initial sample devices into the system
        deviceManager.loadSampleDevices();

        int option; // Variable to store user-selected menu option

        // Main loop to display menu and process user commands
        do {
            // Display the main menu options for the library device checkout system
            System.out.println("\n--- Library Device Checkout System ---");
            System.out.println("1. List Devices by Title");
            System.out.println("2. Add New Devices");
            System.out.println("3. Edit Device Information");
            System.out.println("4. Search by Device Name");
            System.out.println("5. Check Out Devices");
            System.out.println("6. Check In Devices");
            System.out.println("7. Exit");
            System.out.print("Select an option: ");

            // Read the user's choice from the input
            option = scanner.nextInt();
            scanner.nextLine(); // Consume the remaining newline character after integer input

            // Switch statement to handle each menu option
            switch (option) {
                case 1: // List all devices
                    deviceManager.displayDevices();
                    break;
                case 2: // Add a new device
                    deviceManager.addDevice(scanner);
                    break;
                case 3: // Edit an existing device
                    deviceManager.editDevice(scanner);
                    break;
                case 4: // Search for a device by name
                    deviceManager.searchDevices(scanner);
                    break;
                case 5: // Check out a device
                    deviceManager.checkOutDevice(scanner);
                    break;
                case 6: // Check in a device
                    deviceManager.checkInDevice(scanner);
                    break;
                case 7: // Exit the program
                    System.exit(0);
                    break;
                default: // Handle invalid options
                    System.out.println("Invalid option, please try again.");
            }
        } while (option != 7); // Continue until the user chooses to exit

        // Close the scanner object to free up resources
        scanner.close();
    }
}

