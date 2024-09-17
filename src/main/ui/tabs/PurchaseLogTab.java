package ui.tabs;

import model.Beans;
import model.Purchase;
import ui.PlatformUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

// A class to instantiate GUI to save bean purchase details
// Citation: sample code from SmartHome and IntersectionGUI
public class PurchaseLogTab extends JPanel {
    private JTextField textName;
    private JTextField textBeanID;
    private JTextField textRoster;
    private JTextField textCountry;
    private JTextField textProcess;
    private JTextField textYear;
    private JTextField textMonth;
    private JTextField textDate;
    private JLabel labelName;
    private JLabel labelBeanID;
    private JLabel labelRoster;
    private JLabel labelCountry;
    private JLabel labelProcess;
    private JLabel labelYear;
    private JLabel labelMonth;
    private JLabel labelDate;

    //EFFECT: construct instance of buttons, listener and layout
    public PurchaseLogTab() {
        BorderLayout layout = new BorderLayout();
        layout.setHgap(200);
        layout.setVgap(200);
        setLayout(layout);

        JButton saveReviewLogButton = new JButton("save review log");

        add(getPurchasePanel(), BorderLayout.CENTER);
        add(saveReviewLogButton,BorderLayout.SOUTH);

                // Add action listener to the button
        saveReviewLogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doPurchase();
                JOptionPane.showMessageDialog(PurchaseLogTab.this, "saved", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    //EFFECT: instantiate element of the Panel
    public JPanel getPurchasePanel() {
        JPanel purchasePanel = new JPanel(new GridLayout(8, 2, 5, 5));
        labelName = new JLabel("What is your name?");
        textName = new JTextField(20);
        labelBeanID = new JLabel("What is the beanID?");
        textBeanID = new JTextField(20);
        labelRoster = new JLabel("What is name of the roaster?");
        textRoster = new JTextField(20);
        labelCountry = new JLabel("Where is the country of origin?");
        textCountry = new JTextField(20);
        labelProcess = new JLabel("What is bean processing method?");
        textProcess = new JTextField(20);
        labelYear = new JLabel("What is year of the roast date?");
        textYear = new JTextField(20);
        labelMonth = new JLabel("What is month of the roast date?");
        textMonth = new JTextField(20);
        labelDate = new JLabel("What is day of the roast date?");
        textDate = new JTextField(20);

        getPurchasePanelHelper(purchasePanel);

        return purchasePanel;
    }

    //EFFECT: add instances to Panel
    private void getPurchasePanelHelper(JPanel panel) {
        panel.add(labelName);
        panel.add(textName);
        panel.add(labelBeanID);
        panel.add(textBeanID);
        panel.add(labelRoster);
        panel.add(textRoster);
        panel.add(labelCountry);
        panel.add(textCountry);
        panel.add(labelProcess);
        panel.add(textProcess);
        panel.add(labelYear);
        panel.add(textYear);
        panel.add(labelMonth);
        panel.add(textMonth);
        panel.add(labelDate);
        panel.add(textDate);
    }

    //MODIFIES: this
    //EFFECTS: Add beans to purchase
    private void doPurchase() {
        Purchase purchaseTemp = new Purchase(textName.getText());
        PlatformUI.getPurchaseList().add(purchaseTemp);
        doPurchaseHelper(purchaseTemp);
    }

    //MODIFIES: this
    //EFFECTS: take input from user and add bean into purchase list
    public void doPurchaseHelper(Purchase purchaseTemp) {
        int beanID = parseInt(textBeanID.getText());
        String ro = textRoster.getText();
        String or = textCountry.getText();
        String pro = textProcess.getText();
        int yyYY = parseInt(textYear.getText());
        int mm = parseInt(textMonth.getText());
        int dd = parseInt(textDate.getText());

        purchaseTemp.addBean(new Beans(beanID, ro, or, pro, yyYY, mm, dd));
    }
}

