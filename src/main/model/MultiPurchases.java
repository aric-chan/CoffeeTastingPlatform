package model;

import java.util.ArrayList;

//A class to handle multiple purchases, printing purchase list
public class MultiPurchases {
    private ArrayList<Purchase> purchaseList;

    //MODIFIES: this
    //EFFECT: instantiate Purchase List
    public MultiPurchases() {
        purchaseList = new ArrayList<>();
    }

    public ArrayList<Purchase> getPurchaseList() {
        return purchaseList;
    }

    //MODIFIES: this
    //EFFECT: add Purchase to Purchase List
    public void addPurchase(Purchase pr) {
        purchaseList.add(pr);
    }

    // REQUIRES: purchase list not empty
    // EFFECTS: prints beans details
    public String printPurchase(ArrayList<Purchase> purchases) {
        String str = "<html>";
        if (purchases.isEmpty()) {
            str += "No purchase record found!";
        } else {
            for (Purchase i : purchases) {
                str += "<br/>";
                str += "Purchase log for " + i.getBuyer() + "<br/>";
                for (Beans m : i.getBeanList()) {
                    str += "ID:" + m.getBeanID() + "<br/>";
                    str += "roaster:" + m.getBeanID() + "<br/>";
                    str += "origin:" + m.getOrigin() + "<br/>";
                    str += "process:" + m.getProcess() + "<br/>";
                    str += "origin:" + m.getOrigin() + "<br/>";
                    str += "Roast date:" + m.getRoastYYyy() + "-" + m.getRoastMM() + "-" + m.getRoastDD() + "<br/>";
                }
            }
        }
        str += "</html>";
        EventLog.getInstance().logEvent(new Event("Print all purchase "));
        return str;
    }
}
