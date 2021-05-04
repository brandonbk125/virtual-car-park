import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * The main application class for the Car Park
 * Uses a command line menu
 * @author brb19
 * @version 1
 */

public class Application {
    private String filename;
    private Scanner scan;
    private CarPark carPark;


    private Application() {
        scan = new Scanner(System.in);
        System.out.println("Enter the file name with the car park information: ");
        filename = scan.next();
        carPark = new CarPark();


    }

    private void initialisation() {
        System.out.println("Loading from " + filename);
        try {
            carPark.setZones(filename);
        } catch (FileNotFoundException e) {
            System.err.println("The file " + filename + "does not exist.");
        } catch (IOException e) {
            System.err.println("An unexpected error occurred when trying to open " + filename);
        }


    }


    private void menu() {
        String answer;
        do {
            printMenu();
            System.out.println("Enter choice: ");
            scan = new Scanner(System.in);
            answer = scan.nextLine().toUpperCase();
            switch (answer) {
                case "1":
                    newCustomer();
                    break;
                case "2":
                    returningCustomer();
                    break;
                case "3":
                    attendantLogin();
                    break;
                case "4":
                    displayCarPark();
                    break;
                case "Q":
                    break;
                default:
                    System.out.println("Try again");
            }
        } while (!(answer.equals("Q")));
    }

    private void printMenu() {
        System.out.println("1 - new customer");
        System.out.println("2 - returning customer");
        System.out.println("3 - attendant login");
        System.out.println("4 - display car park status");
        System.out.println("q - quit");

    }

    private void newCustomer() {
        System.out.println("Welcome new customer");
        System.out.println("Enter vehicle registration: ");
        scan = new Scanner(System.in);
        String reg = scan.nextLine();

        String answer;
        boolean again;

        do {
            again = false;
            printVehicleMenu();
            System.out.println("Enter vehicle type: ");
            answer = scan.nextLine();

            switch (answer) {
                case "1":
                    standardSized s = new standardSized(reg);
                    carPark.park(s);
                    break;
                case "2":
                    higherVehicle h = new higherVehicle(reg);
                    carPark.park(h);
                    break;
                case "3":
                    longerVehicle l = new longerVehicle(reg);
                    carPark.park(l);
                    break;
                case "4":
                    Coach c = new Coach(reg);
                    carPark.park(c);
                    break;
                case "5":
                    Motorbike m = new Motorbike(reg);
                    carPark.park(m);
                    break;
                default:
                    System.out.println("Try again");
                    again = true;
            }
        } while (again);




    }

    private void printVehicleMenu() {
        System.out.println("1 - standard sized");
        System.out.println("2 - higher vehicle");
        System.out.println("3 - longer vehicle");
        System.out.println("4 - coach");
        System.out.println("5 - motorbike");

    }

    private void returningCustomer() {
        System.out.println("Enter receipt number: ");
        String receiptNo = scan.nextLine();
        carPark.findReceipt(receiptNo);



    }

    private void attendantLogin() {
        System.out.println("Welcome attendant");
        String choice;
        boolean again = true;
        do {
            attendantMenu();
            System.out.println("Enter choice: ");
            choice = scan.nextLine().toUpperCase();

            switch (choice) {
                case "1":
                    attendantPark();
                    break;
                case "2":
                    returningCustomer();
                    break;
                case "Q":
                    System.out.println("Logging attendant out");
                    again = false;
                    break;
                default:
                    System.out.println("Invalid input");

            }
        } while (again);

    }

    private void attendantPark(){
        System.out.println("Enter vehicle registration: ");
        String reg = scan.nextLine();

        System.out.println("1 - standard sized");
        System.out.println("2 - higher vehicle");
        System.out.println("3 - longer vehicle");
        System.out.println("Enter type of vehicle to park: ");
        String choice = scan.nextLine().toUpperCase();

        switch (choice) {
            case "1":
                standardSized s = new standardSized(reg);
                carPark.park(s);
                break;
            case "2":
                higherVehicle h = new higherVehicle(reg);
                carPark.park(h);
                break;
            case "3":
                longerVehicle l = new longerVehicle(reg);
                carPark.park(l);
                break;
            default:
                System.out.println("Invalid");
        }
    }

    private void attendantMenu(){
        System.out.println("1 - park a customers vehicle");
        System.out.println("2 - collect a customers vehicle");
        System.out.println("q - logout");
    }

    private void displayCarPark() {
        System.out.println("Car park display: ");
        System.out.println(carPark);
    }

    private void save(){
        try{
            carPark.save("carpark.txt");
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Application MCPApp = new Application();
        MCPApp.initialisation();
        MCPApp.menu();
        //MCPApp.save();


    }
}
