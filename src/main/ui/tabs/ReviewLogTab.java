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

import static java.lang.Integer.parseInt;

//A class to instantiate GUI to save bean review details
// Citation: sample code from SmartHome and IntersectionGUI
public class ReviewLogTab extends JPanel {
    private JLabel labelBeanID;
    private JLabel labelTaster;
    private JLabel labelAroma;
    private JLabel labelAcid;
    private JLabel labelSweet;
    private JLabel labelBody;
    private JLabel labelFinish;
    private JLabel labelOverall;
    private JLabel labelNote;
    private JTextField textBeanID;
    private JTextField textTaster;
    private JTextField textAroma;
    private JTextField textAcid;
    private JTextField textSweet;
    private JTextField textBody;
    private JTextField textFinish;
    private JTextField textOverall;
    private JTextField textNote;

    //EFFECT: construct instance of buttons, listener and layout
    public ReviewLogTab() {
        BorderLayout layout = new BorderLayout();
        layout.setHgap(200);
        layout.setVgap(200);
        setLayout(layout);

        JButton saveReviewLogButton = new JButton("save review log");

        add(getReviewPanel(), BorderLayout.CENTER);
        add(saveReviewLogButton,BorderLayout.SOUTH);

        // Add action listener to the button
        saveReviewLogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doBeanReview();
                JOptionPane.showMessageDialog(ReviewLogTab.this, "saved!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    //EFFECT: instantiate element of the Panel
    public JPanel getReviewPanel() {
        JPanel reviewPanel = new JPanel(new GridLayout(9, 2, 5, 5));
        labelBeanID = new JLabel("What is the beanID?");
        textBeanID = new JTextField(20);
        labelTaster = new JLabel("What is name of the taster?");
        textTaster = new JTextField(20);
        labelAroma = new JLabel("Rank aroma (1-10)");
        textAroma = new JTextField(20);
        labelAcid = new JLabel("Rank acidity (1-10)");
        textAcid = new JTextField(20);
        labelSweet = new JLabel("Rank sweetness (1-10)");
        textSweet = new JTextField(20);
        labelBody = new JLabel("Rank body (1-10)");
        textBody = new JTextField(20);
        labelFinish = new JLabel("Rank finish (1-10)");
        textFinish = new JTextField(20);
        labelOverall = new JLabel("Rank overall (1-10)");
        textOverall = new JTextField(20);
        labelNote = new JLabel("Additional note");
        textNote = new JTextField(20);

        getPurchasePanelHelper(reviewPanel);

        return reviewPanel;
    }

    //EFFECT: add instances to Panel
    private void getPurchasePanelHelper(JPanel panel) {
        panel.add(labelBeanID);
        panel.add(textBeanID);
        panel.add(labelTaster);
        panel.add(textTaster);
        panel.add(labelAroma);
        panel.add(textAroma);
        panel.add(labelAcid);
        panel.add(textAcid);
        panel.add(labelSweet);
        panel.add(textSweet);
        panel.add(labelBody);
        panel.add(textBody);
        panel.add(labelFinish);
        panel.add(textFinish);
        panel.add(labelOverall);
        panel.add(textOverall);
        panel.add(labelNote);
        panel.add(textNote);
    }

    // MODIFIES : this
    // EFFECTS: Add brewnotes to Bean Review and print all brewnotes details
    private void doBeanReview() {
        boolean foundDuplicateID = false;
        for (BeanReview m : PlatformUI.getBeanReviewList()) {
            //check pass bean ID is within the beanreview arraylist
            int passBeanID = parseInt(textBeanID.getText());
            if (passBeanID == m.getBeanID()) {
                doPBeanReviewHelper(m);
                foundDuplicateID = true;
            }
        }
        //if no duplicate beanID found in the bean review list, instantiate a new bean reivew object
        if (!foundDuplicateID) {
            BeanReview brTemp = new BeanReview(parseInt(textBeanID.getText()));
            PlatformUI.getBeanReviewList().add(brTemp);
            doPBeanReviewHelper(brTemp);
        }
    }

    //MODIFIES: this
    //EFFECTS: take input from user and add BrewNote into bean review
    public void doPBeanReviewHelper(BeanReview brTemp) {
        int id = parseInt(textBeanID.getText());
        String name = textTaster.getText();
        int ar = parseInt(textAroma.getText());
        int ac = parseInt(textAcid.getText());
        int sw = parseInt(textSweet.getText());
        int body = parseInt(textBody.getText());
        int fin = parseInt(textFinish.getText());
        int overall = parseInt(textOverall.getText());
        String note = textNote.getText();

        brTemp.addBrewNote(new BrewNote(id, name, ar, ac, sw, body, fin, overall, note));
    }
}
