import java.util.ArrayList;
import java.util.Scanner;

public class Application {
    private String filename;
    private Scanner scan;
    private String reg;
    private String receiptNo;


    private Application(){
        scan = new Scanner(System.in);
        System.out.println("Enter the file name with the car park information: ");
        filename = scan.next();


    }

    private void initiliation(){
        System.out.println("Loading from "+ filename);

        /*try{

        }catch ()*/

    }


    private void menu(){
        String answer;
        do {
            printMenu();
            System.out.println("Enter choice: ");
            scan = new Scanner(System.in);
            answer = scan.nextLine().toUpperCase();
            switch(answer) {
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
        }while(!(answer.equals("Q")));
    }

    private void printMenu(){
        System.out.println("1 - new customer");
        System.out.println("2 - returning customer");
        System.out.println("3 - attendant login");
        System.out.println("4 - display car park status");
        System.out.println("q - quit");

    }

    private void newCustomer(){
        System.out.println("Welcome new customer");
        System.out.println("Enter vehicle registration: ");
        System.out.println("Enter vehicle height: ");
        System.out.println("Enter vehicle length: ");

    }

    private void returningCustomer(){
        System.out.println("Welcome back");
    }

    private void attendantLogin(){
        System.out.println("Welcome staff");
    }

    private void displayCarPark(){
        System.out.println("Car park display: ");
    }


    public static void main(String[] args) {
        Application MCPApp = new Application();
        MCPApp.initiliation();
        MCPApp.menu();

    }
}
