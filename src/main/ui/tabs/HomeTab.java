package ui.tabs;

import model.BeanReview;
import model.Purchase;

import ui.PlatformUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

//A class to instantiate save/load buttons
// Citation: sample code from SmartHome and IntersectionGUI
public class HomeTab extends JPanel {
    private JButton loadPurchaseLoglogButton;
    private JButton loadReviewLogButton;
    private JButton savePurchaseLoglogButton;
    private JButton saveReviewLogButton;
    private JButton imageButton;
    private static ArrayList<Purchase> purchaseList = PlatformUI.getPurchaseList();

    private static ArrayList<BeanReview> beanReviewList = PlatformUI.getBeanReviewList();

    //EFFECT: construct instance of buttons and add to borderlayout
    public HomeTab() {

        loadPurchaseLoglogButton = new JButton("load purchase log");
        loadReviewLogButton = new JButton("load review log");
        savePurchaseLoglogButton = new JButton("save purchase log");
        saveReviewLogButton = new JButton("save review log");
        imageButton = new JButton("Show Image");

        addListener();

        add(loadPurchaseLoglogButton, BorderLayout.SOUTH);
        add(loadReviewLogButton, BorderLayout.SOUTH);
        add(savePurchaseLoglogButton, BorderLayout.SOUTH);
        add(saveReviewLogButton, BorderLayout.SOUTH);
        add(imageButton,BorderLayout.SOUTH);

    }

    //EFFECT: implement save button and image button listener
    public void addListener() {
        loadButtonListner();

        savePurchaseLoglogButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                savePurchase();
            }
        });

        saveReviewLogButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveBeanReview();
            }
        });

        imageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                imagePopup();
            }
        });
    }

    //EFFECT: implement load button listener
    private void loadButtonListner() {
        loadPurchaseLoglogButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadPurchase();
            }
        });

        loadReviewLogButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadBeanReview();
            }
        });
    }

    //EFFECT: implement image popping window
    public void imagePopup() {
        JFrame popupWindow = new JFrame("Image Popup");
        ImageIcon image = new ImageIcon("./data/coffeereviewimage.jpeg");

        JLabel label = new JLabel(image);
        popupWindow.setSize(image.getIconWidth(), image.getIconHeight());

        popupWindow.add(label);
        popupWindow.setVisible(true);
    }

    // EFFECTS: saves purchase to file
    public void savePurchase() {
        try {
            PlatformUI.getJsonPurchaseWriter().open();
            PlatformUI.getJsonPurchaseWriter().writePurchase(PlatformUI.getPurchaseList());
            PlatformUI.getJsonPurchaseWriter().close();
            for (Purchase m : PlatformUI.getPurchaseList()) {
                System.out.println("Saved " + m.getBuyer() + " to " + PlatformUI.getJsonPurchase());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + PlatformUI.getJsonPurchase());
        }
    }


    // EFFECTS: saves bean review to file
    public void saveBeanReview() {
        try {
            PlatformUI.getJsonBeanReviewWriter().open();
            PlatformUI.getJsonBeanReviewWriter().writeBeanReview(PlatformUI.getBeanReviewList());
            PlatformUI.getJsonBeanReviewWriter().close();
            for (BeanReview m : PlatformUI.getBeanReviewList()) {
                System.out.println("Saved " + m.getBeanID() + " to " + PlatformUI.getJsonBeanreview()
                );
            }
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + PlatformUI.getJsonBeanreview()
            );
        }
    }

    // MODIFIES: this
    // EFFECTS: loads purchase from file
    public void loadPurchase() {
        try {
            purchaseList = PlatformUI.getJsonPurchaseReader().readPurchase();
            for (Purchase m : purchaseList) {
                System.out.println("Loaded " + m.getBuyer() + " from " + PlatformUI.getJsonPurchase());
            }
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + PlatformUI.getJsonPurchase());
        }
    }

    // MODIFIES: this
    // EFFECTS: loads bean review from file
    public void loadBeanReview() {
        try {
            beanReviewList = PlatformUI.getJsonBeanReviewReader().readBeanReview();
            for (BeanReview m : beanReviewList) {
                System.out.println("Loaded " + m.getBeanID() + " from " + PlatformUI.getJsonBeanreview()
                );
            }
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + PlatformUI.getJsonBeanreview()
            );
        }
    }

    public static ArrayList<BeanReview> getBeanReviewList() {
        return beanReviewList;
    }

    public static ArrayList<Purchase> getPurchaseList() {
        return purchaseList;
    }
}
