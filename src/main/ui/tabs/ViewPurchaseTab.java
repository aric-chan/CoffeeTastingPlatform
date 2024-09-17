package ui.tabs;

import model.BeanReview;
import model.Beans;
import model.BrewNote;
import model.Purchase;
import ui.PlatformUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// A class to instantiate GUI to view purchase details
// Citation: sample code from SmartHome and IntersectionGUI
public class ViewPurchaseTab extends JPanel {
    private JLabel print;

    //EFFECT: construct instance of buttons, listener and layout
    public ViewPurchaseTab() {
        BorderLayout layout = new BorderLayout();
        layout.setHgap(200);
        layout.setVgap(200);
        setLayout(layout);

        JButton viewPurchaseButton = new JButton("View Purchase");
        print = new JLabel("");
        viewPurchaseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                print.setText(PlatformUI.getMultiPurchases().printPurchase(HomeTab.getPurchaseList()));
            }
        });

        add(print, BorderLayout.CENTER);
        add(viewPurchaseButton,BorderLayout.SOUTH);

    }

}
