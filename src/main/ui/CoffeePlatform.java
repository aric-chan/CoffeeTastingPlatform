package ui;

import model.BeanReview;
import model.Beans;
import model.BrewNote;
import model.Purchase;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//The is a console UI to display coffee app. Codes are inspired from the sample code TellerApp
// Citation: sample code from JsonSerializationDemo
public class CoffeePlatform {
    private static final String JSON_PURCHASE = "./data/purchase.json";
    private static final String JSON_BEANREVIEW = "./data/beanreview.json";
    private Scanner input;
    ArrayList<Purchase> purchaseList;
    ArrayList<BeanReview> beanReviewList;
    private JsonWriter jsonPurchaseWriter;
    private JsonWriter jsonBeanReviewWriter;
    private JsonReader jsonPurchaseReader;
    private JsonReader jsonBeanReviewReader;

    // EFFECTS: runs the Coffee App
    public CoffeePlatform() throws FileNotFoundException {
        input = new Scanner(System.in);
        purchaseList = new ArrayList<Purchase>();
        beanReviewList = new ArrayList<BeanReview>();
        jsonPurchaseWriter = new JsonWriter(JSON_PURCHASE);
        jsonBeanReviewWriter = new JsonWriter(JSON_BEANREVIEW);
        jsonPurchaseReader = new JsonReader(JSON_PURCHASE);
        jsonBeanReviewReader = new JsonReader(JSON_BEANREVIEW);
        runCoffeeApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runCoffeeApp() {
        boolean keepGoing = true;
        String command;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nGet a cup of coffee next time!");
    }

    // MODIFIES: this
    // EFFECTS: processes user input from main menu
    private void processCommand(String command) {
        if (command.equals("p")) {
            doPurchase();
        } else if (command.equals("b")) {
            doBeanReview();
        } else if (command.equals("l")) {
            printPurchase(purchaseList);
        } else if (command.equals("k")) {
            printBeanReview(beanReviewList);
        } else if (command.equals("s")) {
            savePurchase();
        } else if (command.equals("t")) {
            saveBeanReview();
        } else if (command.equals("m")) {
            loadPurchase();
        } else if (command.equals("n")) {
            loadBeanReview();
        } else {
            System.out.println("Wrong input. Select again!");
        }
    }

    // MODIFIES: this
    // EFFECTS: instantiate Scanner object, purchase and bean review array list
    private void init() {
        purchaseList = new ArrayList<>();
        beanReviewList = new ArrayList<>();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tp -> Purchase record logging");
        System.out.println("\tb -> Bean review logging");
        System.out.println("\tl -> View purchase");
        System.out.println("\tk -> View bean review");
        System.out.println("\ts -> Save Purchase to file");
        System.out.println("\tt -> Save Bean Review to file");
        System.out.println("\tm -> Load Purchase from file");
        System.out.println("\tn -> Load Bean Review from file");
        System.out.println("\tq -> Quit");
    }

    // MODIFIES : this
    // EFFECTS: Add brewnotes to Bean Review and print all brewnotes details
    private void doBeanReview() {
        System.out.print("How many coffee beans to review?");
        int brarraysize = input.nextInt();

        boolean keepGoing = true;

        while (keepGoing) {
            System.out.print("What is the beanID?");
            int id = input.nextInt();

            BeanReview brTemp = new BeanReview(id);
            beanReviewList.add(brTemp);

            boolean keepGoingInner = true;

            while (keepGoingInner) {
                //helper function to take user inputs
                doPBeanReviewHelper(id, brTemp);

                System.out.print("Finish adding brew notes to this bean? (y/n)");
                String finish = input.next();

                if (finish.equals("y")) {
                    keepGoingInner = false;
                }
            }
            brarraysize--;
            System.out.println("You have " + brarraysize + " coffee beans review to input.");

            if (brarraysize == 0) {
                keepGoing = false;
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: take input from user and add BrewNote into bean review
    private void doPBeanReviewHelper(int id, BeanReview brTemp) {
        System.out.print("What is name of the taster?");
        String name = input.next();

        System.out.print("Rank aroma (1-10)");
        int ar = input.nextInt();

        System.out.print("Rank acidity (1-10)");
        int ac = input.nextInt();

        System.out.print("Rank sweetness (1-10)");
        int sw = input.nextInt();

        System.out.print("Rank body (1-10)");
        int body = input.nextInt();

        System.out.print("Rank finish (1-10)");
        int fin = input.nextInt();

        System.out.print("Rank overall (1-10)");
        int overall = input.nextInt();

        System.out.print("Additional note");
        String note = input.next();

        brTemp.addBrewNote(new BrewNote(id, name, ar, ac, sw, body, fin, overall, note));
    }

    // REQUIRES: Bean Review list not empty
    // EFFECTS: prints all bean reviews on console
    private void printBeanReview(ArrayList<BeanReview> beanReviewList) {
        if (beanReviewList.isEmpty()) {
            System.out.println("No bean review found!");
        } else {
            for (BeanReview i : beanReviewList) {
                System.out.println("");
                System.out.println("Bean Review for Bean ID : " + i.getBeanID());
                System.out.println("Overall score : " + i.getAverageReview(i.getBrewNoteList()));
                for (BrewNote m : i.getBrewNoteList()) {
                    System.out.println("Special Notes by " + m.getTaster() + " : " + m.getNote());
                }
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: Add beans to purchase
    private void doPurchase() {
        boolean keepGoing = true;

        while (keepGoing) {
            System.out.print("What is your name?");
            String name = input.next();

            Purchase purchaseTemp = new Purchase(name);
            purchaseList.add(purchaseTemp);

            boolean keepGoingInner = true;

            while (keepGoingInner) {
                //helper function to take user inputs
                doPurchaseHelper(purchaseTemp);

                System.out.print("Finish adding beans for " + purchaseTemp.getBuyer() + " ? (y/n)");
                String finishInner = input.next();

                if (finishInner.equals("y")) {
                    keepGoingInner = false;
                }
            }

            System.out.print("Any more purchase from others ? (y/n)");
            String finish = input.next();
            if (finish.equals("n")) {
                keepGoing = false;
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: take input from user and add bean into purchase list
    private void doPurchaseHelper(Purchase purchaseTemp) {
        System.out.print("What is the beanID?");
        int beanID = input.nextInt();

        System.out.print("What is name of the roaster?");
        String ro = input.next();

        System.out.print("Where is the country of origin?");
        String or = input.next();

        System.out.print("What is bean processing method?");
        String pro = input.next();

        System.out.print("What is year of the roast date?");
        int yyYY = input.nextInt();

        System.out.print("What is month of the roast date?");
        int mm = input.nextInt();

        System.out.print("What is day of the roast date?");
        int dd = input.nextInt();

        purchaseTemp.addBean(new Beans(beanID, ro, or, pro, yyYY, mm, dd));
    }

    // REQUIRES: purchase list not empty
    // EFFECTS: prints beans details on console
    private void printPurchase(ArrayList<Purchase> purchases) {
        if (purchases.isEmpty()) {
            System.out.println("No purchase record found!");
        } else {
            for (Purchase i : purchases) {
                System.out.println("");
                System.out.println("Purchase log for " + i.getBuyer());
                for (Beans m : i.getBeanList()) {
                    System.out.println("ID:" + m.getBeanID());
                    System.out.println("roaster:" + m.getBeanID());
                    System.out.println("origin:" + m.getOrigin());
                    System.out.println("process:" + m.getProcess());
                    System.out.println("origin:" + m.getOrigin());
                    System.out.println("Roast date:" + m.getRoastYYyy() + "-" + m.getRoastMM() + "-" + m.getRoastDD());
                }
            }
        }
    }

    // EFFECTS: saves purchase to file
    public void savePurchase() {
        try {
            jsonPurchaseWriter.open();
            jsonPurchaseWriter.writePurchase(purchaseList);
            jsonPurchaseWriter.close();
            for (Purchase m : purchaseList) {
                System.out.println("Saved " + m.getBuyer() + " to " + JSON_PURCHASE);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_PURCHASE);
        }
    }

    // EFFECTS: saves bean review to file
    public void saveBeanReview() {
        try {
            jsonBeanReviewWriter.open();
            jsonBeanReviewWriter.writeBeanReview(beanReviewList);
            jsonBeanReviewWriter.close();
            for (BeanReview m : beanReviewList) {
                System.out.println("Saved " + m.getBeanID() + " to " + JSON_BEANREVIEW);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_BEANREVIEW);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads purchase from file
    public void loadPurchase() {
        try {
            purchaseList = jsonPurchaseReader.readPurchase();
            for (Purchase m : purchaseList) {
                System.out.println("Loaded " + m.getBuyer() + " from " + JSON_PURCHASE);
            }
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_PURCHASE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads bean review from file
    public void loadBeanReview() {
        try {
            beanReviewList = jsonBeanReviewReader.readBeanReview();
            for (BeanReview m : beanReviewList) {
                System.out.println("Loaded " + m.getBeanID() + " from " + JSON_BEANREVIEW);
            }
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_BEANREVIEW);
        }
    }

    public ArrayList<Purchase> getPurchaseList() {
        return purchaseList;
    }

    public ArrayList<BeanReview> getBeanReviewList() {
        return beanReviewList;
    }
}