import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


/**
 * The CarPark class - to model a car park - a collection of different vehicles and zones
 *
 * @author brb19
 * @version 1
 */


public class CarPark {
    private Scanner scan;
    private ArrayList<Zone> zones;
    private ArrayList<String> receipts;
    private ZoneA zoneA;
    private ZoneB zoneB;
    private ZoneC zoneC;
    private ZoneD zoneD;
    private ZoneE zoneE;
    private double zoneAcost;
    private double zoneBcost;
    private double zoneCcost;
    private double zoneDcost;
    private double zoneEcost;

    /**
     * Creates a carpark
     */

    public CarPark() {
        scan = new Scanner(System.in);
        zones = new ArrayList<>();
        receipts = new ArrayList<>();

    }

    /**
     * This method sets the settings of each zone from a config file
     * @param filename name of config file
     * @throws IOException
     */

    public void setZones(String filename) throws IOException {
        try (FileReader fr = new FileReader(filename);
             BufferedReader br = new BufferedReader(fr);
             Scanner infile = new Scanner(br)) {

            //Reading Zone A info
            infile.nextLine();
            int capacity = infile.nextInt();
            zoneA = new ZoneA(capacity);
            zones.add(zoneA);
            zoneAcost = infile.nextDouble();
            infile.nextLine();

            //Reading Zone B info
            infile.nextLine();
            capacity = infile.nextInt();
            zoneB = new ZoneB(capacity);
            zones.add(zoneB);
            zoneBcost = infile.nextDouble();
            infile.nextLine();

            //Reading Zone C info
            infile.nextLine();
            capacity = infile.nextInt();
            zoneC = new ZoneC(capacity);
            zones.add(zoneC);
            zoneCcost = infile.nextDouble();
            infile.nextLine();

            //Reading Zone D info
            infile.nextLine();
            capacity = infile.nextInt();
            zoneD = new ZoneD(capacity);
            zones.add(zoneD);
            zoneDcost = infile.nextDouble();
            infile.nextLine();

            //Reading Zone E info
            infile.nextLine();
            capacity = infile.nextInt();
            zoneE = new ZoneE(capacity);
            zones.add(zoneE);
            zoneEcost = infile.nextDouble();


        }


    }


    /**
     * This method saves the current state of the carpark into a file
     * @param outfileName the file to be saved to
     * @throws IOException
     */
    public void save(String outfileName) throws IOException{

        try (FileWriter fw = new FileWriter(outfileName);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter outfile = new PrintWriter(bw)) {

            for (String receipt:receipts){

                System.out.println(receipt+" added");
                outfile.println(receipt);
            }

            for (int i = 0; i <5; i++){

                System.out.println(zones.get(i).toString());
                outfile.println(zones.get(i).toString());


            }


        }
    }

    /**
     * Attempts to find a valid receipt
     * @param receiptNo the receipt to be found
     */

    public void findReceipt(String receiptNo){
        boolean found = false;
        for (String receipt:receipts){
            if (receipt.equals(receiptNo)){
                foundReceipt(receiptNo);
                found = true;
            }
        }
        if (!found){
            System.out.println("Receipt not found");
        }


    }


    private void foundReceipt(String receiptNo){
        String parkedDate = receiptNo.substring(2);
        String returnDate = getDateString();
        long hours= hoursParked(parkedDate, returnDate);
        calcCost(receiptNo, hours);

    }

    private void calcCost(String receiptNo, long hours){
        scan = new Scanner(System.in);
        boolean disabledDriver = false;
        System.out.println("Are you a disabled driver? (Yes/No): ");
        String ans = scan.next().toUpperCase();
        if (ans.equals("YES")){

            disabledDriver = true;
        }

        double cost = 0;
        if (receiptNo.substring(0,1).equals("A")){
            cost = hours*zoneAcost;
        }else if (receiptNo.substring(0,1).equals("B")){
            cost = hours*zoneBcost;
        }else if (receiptNo.substring(0,1).equals("C")){
            cost = hours*zoneCcost;
        }else if (receiptNo.substring(0,1).equals("D")){
            cost = hours*zoneDcost;
        }else if (receiptNo.substring(0,1).equals("E")){
            cost = hours*zoneEcost;
        }
        if (disabledDriver){
            cost *= 0.5;
        }
        pay(receiptNo,cost);

    }

    private void pay(String receiptNo, double cost){
        System.out.println("Your parking ticket cost is "+ cost);
        System.out.println("Enter amount to pay: ");
        double amountPaid = scan.nextDouble();
        if (amountPaid >= cost){
            double change = amountPaid - cost;
            System.out.println("Amount paid: "+ amountPaid);
            System.out.println("Change due: " + change);
            System.out.println("Your vehicle is located at "+receiptNo.substring(0,2));
            collectVehicle(receiptNo);

        }else if (amountPaid < cost){
            System.out.println("Insufficient amount");

        }


    }

   private void collectVehicle(String receiptNo){
        String zoneLetter = receiptNo.substring(0,1);
        int spaceNo = Integer.parseInt(receiptNo.substring(1,2));
        System.out.println("You have 15 minutes to collect your vehicle and exit.");
        long startTime = System.currentTimeMillis();

        System.out.println("Press any key when you are ready to exit: ");
        scan.next();
        if ((System.currentTimeMillis() - startTime)> 90){
            //900000 milliseconds = 15 minutes
            System.out.println("You took more than 15 minutes. Please seek assistance");
        }else{
            System.out.println("Thank you, goodbye");
            if (zoneLetter.equals("A")){
                zoneA.clearSpace(spaceNo);
            }else if (zoneLetter.equals("B")){
                zoneB.clearSpace(spaceNo);
            }else if (zoneLetter.equals("C")){
                zoneC.clearSpace(spaceNo);
            }else if (zoneLetter.equals("D")){
                zoneD.clearSpace(spaceNo);
            }else if (zoneLetter.equals("E")){
                zoneE.clearSpace(spaceNo);
            }

        }
    }

    /**
     * Creates a String of the currenty date+time
     * @return date+time in the format ddMMyyyyGGmmss
     */

    public String getDateString(){
        DateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");

        Date date = new Date();

        return sdf.format(date);

    }

    /**
     * Puts a given vehicle into an appropriate zone
     * @param s standard vehicle
     */

    public void park(standardSized s) {
        String space;
        String receiptNo;
        if (zoneD.hasSpace()) {
            space = "D" + zoneD.getNextSpaceNumber();
            System.out.println("Your space is at " + space);
            zoneD.addVehicle(s);
            receiptNo = space + getDateString();
            receipts.add(receiptNo);
            System.out.println("Your receipt number is: " + receiptNo);

        } else if (zoneA.hasSpace()) {
            space = "A" + zoneA.getNextSpaceNumber();
            System.out.println("Your space is at " + space);
            zoneA.addVehicle(s);
            receiptNo = space + getDateString();
            receipts.add(receiptNo);
            System.out.println("Your receipt number is: " + receiptNo);
        } else {
            System.out.println("No space for standard sized vehicle");
        }


    }

    /**
     * @param h higher vehicle
     */

    public void park(higherVehicle h) {
        String space;
        String receiptNo;
        if (zoneA.hasSpace()) {
            space = "A" + zoneA.getNextSpaceNumber();
            System.out.println("Your space is at " + space);
            zoneA.addVehicle(h);
            receiptNo = space + getDateString();
            receipts.add(receiptNo);
            System.out.println("your receipt number is: " + receiptNo);
        } else {
            System.out.println("No space for higher vehicles");
        }

    }

    /**
     * @param l longer vehicle
     */

    public void park(longerVehicle l) {
        String space;
        String receiptNo;
        if (zoneB.hasSpace()) {
            space = "B" + zoneB.getNextSpaceNumber();
            System.out.println("Your space is at " + space);
            zoneB.addVehicle(l);
            receiptNo = space + getDateString();
            receipts.add(receiptNo);
            System.out.println("Your receipt number is: " + receiptNo);
        } else {
            System.out.println("No space for higher vehicles");
        }

    }


    /**
     * @param c Coach
     */
    public void park(Coach c) {
        String space;
        String receiptNo;
        if (zoneC.hasSpace()) {
            space = "C" + zoneC.getNextSpaceNumber();
            System.out.println("Your space is at " + space);
            zoneC.addVehicle(c);
            receiptNo = space + getDateString();
            receipts.add(receiptNo);
            System.out.println("Your receipt number is: " + receiptNo);
        } else {
            System.out.println("No space for more coaches");
        }

    }

    /**
     * @param m Motorbike
     */
    public void park(Motorbike m) {
        String space;
        String receiptNo;
        if (zoneE.hasSpace()) {
            space = "E" + zoneE.getNextSpaceNumber();
            System.out.println("Your space is it " + space);
            zoneE.addVehicle(m);
            receiptNo = space + getDateString();
            receipts.add(receiptNo);
            System.out.println("Your receipt number is: " + receiptNo);
        } else {
            System.out.println("No space for motorbikes");
        }

    }

    /**
     * This method compares two dates that are in the UK standard date format
     * and find the difference in hours in order to determine how many hours
     * a vehicle has been parked
     * @param dateStart Start date
     * @param dateStop End date
     * @return Number of hours parked
     * @author MKYONG.COM
     */
    //enter the date/time in the format ddMMyyyHHmmss
    //date-month-year-hours-minutes-seconds
    public long hoursParked(String dateStart, String dateStop) {
        long diffHours = 0;
        //From MKYONG.COM
        //dateStart = "14/01/201809:29:58";
        //dateStop = "15/01/201810:31:48";
        SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmss");
        Date d1 = null;
        Date d2 = null;

        try
        {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);
            //in millis
            long diff = d2.getTime() - d1.getTime();

            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            diffSeconds += diffMinutes * 60;
            diffHours += diffDays * 24;
            if (diffSeconds > 0) {
                diffHours++;
            }
            System.out.println("You were parked for " + diffHours + " hours");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return diffHours;
    }


    /**
     *
     * @return String showing all the information in the zones
     */
    public String toString() {
        String result = "";
        for (Zone zone : zones) {
            result += zone.toString();
            result += "\n";
        }

        return result;

    }

}
