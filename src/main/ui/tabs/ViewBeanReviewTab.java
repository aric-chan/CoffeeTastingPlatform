package ui.tabs;

import model.BeanReview;
import model.BrewNote;
import ui.PlatformUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//A class to instantiate GUI to view purchase details
// Citation: sample code from SmartHome and IntersectionGUI
public class ViewBeanReviewTab extends JPanel {
    private JLabel print;

    //EFFECT: construct instance of buttons, listener and layout
    public ViewBeanReviewTab() {
        BorderLayout layout = new BorderLayout();
        layout.setHgap(200);
        layout.setVgap(200);
        setLayout(layout);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton viewBeanReviewAverageButton = new JButton("View Bean Review (Average)");
        JButton viewBeanReviewButton = new JButton("View Bean Review");
        JButton removeButton = new JButton("Remove Last Element of Bean Review List");
        buttonPanel.add(viewBeanReviewAverageButton);
        buttonPanel.add(viewBeanReviewButton);
        buttonPanel.add(removeButton);

        print = new JLabel("");
        addListner(viewBeanReviewAverageButton, viewBeanReviewButton, removeButton);

        add(print, layout.CENTER);
        add(buttonPanel,layout.SOUTH);

    }

    //MODIFIES: This
    //EFFECT: helper function to set up buttons listener
    private void addListner(JButton viewBeanReviewAverageButton, JButton viewBeanReviewButton, JButton removeButton) {
        viewBeanReviewAverageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                print.setText(PlatformUI.getMultiBeanReviews().printBeanReviewAverage(HomeTab.getBeanReviewList()));
            }
        });

        viewBeanReviewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                print.setText(PlatformUI.getMultiBeanReviews().printBeanReviewAll(HomeTab.getBeanReviewList()));
            }
        });

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PlatformUI.getMultiBeanReviews().removeBeanReview(HomeTab.getBeanReviewList());
            }
        });
    }

}


