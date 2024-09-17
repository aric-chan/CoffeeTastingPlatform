package ui;

import model.*;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.tabs.*;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;


// A class to instantiate Platform GUI, tabs to run the program
// Having global fields to allow different tabs to access various arraylists/json objects
// Citation: sample code from SmartHome and IntersectionGUI, How to Write Window Listeners from ORACLE documentation

public class PlatformUI extends JFrame implements WindowListener {
    public static final int HOME_TAB_INDEX = 0;
    public static final int PURCHASELOG_TAB_INDEX = 1;
    public static final int REVIEWLOG_TAB_INDEX = 2;
    public static final int VIEWPURCHASE_TAB_INDEX = 3;
    public static final int VIEWBEANREVIEW_TAB_INDEX = 4;

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 600;
    private JTabbedPane sidebar;
    private static String JSON_PURCHASE = "./data/purchase.json";
    private static String JSON_BEANREVIEW = "./data/beanreview.json";
    private static MultiPurchases multiPurchases = new MultiPurchases();
    private static MultiBeanReviews multiBeanReviews = new MultiBeanReviews();
    private static JsonWriter jsonPurchaseWriter = new JsonWriter(JSON_PURCHASE);
    private static JsonWriter jsonBeanReviewWriter = new JsonWriter(JSON_BEANREVIEW);
    private static JsonReader jsonPurchaseReader = new JsonReader(JSON_PURCHASE);
    private static JsonReader jsonBeanReviewReader = new JsonReader(JSON_BEANREVIEW);

    static PlatformUI frame;
    JTextArea display;


    //EFFECTS: run the UI
    public static void main(String[] args) {
        frame = new PlatformUI();
    }

    //MODIFIES: this
    //EFFECTS: creates PlatformUI, loads tabs and displays sidebar
    private PlatformUI() {
        super("Coffee Tasting Platform");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        sidebar = new JTabbedPane();
        sidebar.setTabPlacement(JTabbedPane.LEFT);

        loadTabs();
        add(sidebar);

        setVisible(true);

        addWindowListener(this);

    }


    //MODIFIES: this
    //EFFECTS: adds home tab, settings tab and report tab to this UI
    private void loadTabs() {
        JPanel homeTab = new HomeTab();
        JPanel purchaseLogTab = new PurchaseLogTab();
        JPanel reviewLogTab = new ReviewLogTab();
        JPanel viewPurchaseTab = new ViewPurchaseTab();
        JPanel viewBeanReviewTab = new ViewBeanReviewTab();

        sidebar.add(homeTab, HOME_TAB_INDEX);
        sidebar.setTitleAt(HOME_TAB_INDEX, "Home");
        sidebar.add(purchaseLogTab, PURCHASELOG_TAB_INDEX);
        sidebar.setTitleAt(PURCHASELOG_TAB_INDEX, "Purchase Log");
        sidebar.add(reviewLogTab, REVIEWLOG_TAB_INDEX);
        sidebar.setTitleAt(REVIEWLOG_TAB_INDEX, "Bean Review Log");
        sidebar.add(viewPurchaseTab, VIEWPURCHASE_TAB_INDEX);
        sidebar.setTitleAt(VIEWPURCHASE_TAB_INDEX, "View Purchase");
        sidebar.add(viewBeanReviewTab, VIEWBEANREVIEW_TAB_INDEX);
        sidebar.setTitleAt(VIEWBEANREVIEW_TAB_INDEX, "View Bean Review");
    }


    @Override
    public void windowOpened(WindowEvent e) {

    }

    public void windowClosing(WindowEvent e) {
        System.out.println("Print log before exit");
        for (Event event : EventLog.getInstance()) {
            System.out.println(event);
        }

        //A pause so user can see the message before
        //the window actually closes.
        ActionListener task = new ActionListener() {
            boolean alreadyDisposed = false;
            public void actionPerformed(ActionEvent e) {
                if (frame.isDisplayable()) {
                    alreadyDisposed = true;
                    frame.dispose();
                }
            }
        };
        Timer timer = new Timer(500, task); //fire every half second
        timer.setInitialDelay(2000);        //first delay 2 seconds
        timer.setRepeats(false);
        timer.start();
    }


    //EFFECTS: returns sidebar of this UI
    public JTabbedPane getTabbedPane() {
        return sidebar;
    }

    public static JsonReader getJsonBeanReviewReader() {
        return jsonBeanReviewReader;
    }

    public static ArrayList<Purchase> getPurchaseList() {
        return multiPurchases.getPurchaseList();
    }

    public static ArrayList<BeanReview> getBeanReviewList() {
        return multiBeanReviews.getBeanReviewList();
    }

    public static JsonWriter getJsonPurchaseWriter() {
        return jsonPurchaseWriter;
    }

    public static JsonWriter getJsonBeanReviewWriter() {
        return jsonBeanReviewWriter;
    }

    public static JsonReader getJsonPurchaseReader() {
        return jsonPurchaseReader;
    }

    public static String getJsonPurchase() {
        return JSON_PURCHASE;
    }

    public static String getJsonBeanreview() {
        return JSON_BEANREVIEW;
    }

    public static MultiBeanReviews getMultiBeanReviews() {
        return multiBeanReviews;
    }

    public static MultiPurchases getMultiPurchases() {
        return multiPurchases;
    }


    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
