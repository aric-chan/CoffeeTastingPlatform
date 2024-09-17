package model;


import java.util.ArrayList;

//A class to handle multiple bean reviews, removing and printing bean review list
public class MultiBeanReviews {
    private ArrayList<BeanReview> beanReviewList;

    //MODIFIES: this
    //EFFECT: instantiate BeanReviewList
    public MultiBeanReviews() {
        beanReviewList = new ArrayList<>();
    }

    public ArrayList<BeanReview> getBeanReviewList() {
        return beanReviewList;
    }

    //MODIFIES: this
    //EFFECT: add BeanReview to BeanReviewList
    public void addBeanReview(BeanReview br) {
        beanReviewList.add(br);
    }

    public void removeBeanReview(ArrayList<BeanReview> beanReviewList) {
        BeanReview tempBr;
        tempBr = beanReviewList.get(beanReviewList.size() - 1);
        beanReviewList.remove(beanReviewList.size() - 1);
        EventLog.getInstance().logEvent(new Event("Remove the last added BeanID " + tempBr.getBeanID()));
    }

    //REQUIRES: Bean Review list not empty
    //EFFECT: print all bean review result
    public String printBeanReviewAll(ArrayList<BeanReview> beanReviewList) {
        String str = "<html>";
        if (beanReviewList.isEmpty()) {
            str += "No bean review found!";
        } else {
            for (BeanReview i : beanReviewList) {
                str += "<br/>";
                str += "Bean Review for Bean ID : " + i.getBeanID() + "<br/>";
                for (BrewNote m : i.getBrewNoteList()) {
                    str += "Taster : " + m.getTaster() + "<br/>";
                    str += "Aroma : " + m.getAroma() + "<br/>";
                    str += "Acidity : " + m.getAcidity() + "<br/>";
                    str += "Sweet : " + m.getSweet() + "<br/>";
                    str += "Body : " + m.getBody() + "<br/>";
                    str += "Finish : " + m.getFinish() + "<br/>";
                    str += "Overall : " + m.getOverall() + "<br/>";
                    str += "Special Notes :" + m.getNote() + "<br/>";
                }
            }
        }
        str += "</html>";
        EventLog.getInstance().logEvent(new Event("Print all bean review "));
        return str;
    }

    // REQUIRES: Bean Review list not empty
    // EFFECTS: prints average of bean rating
    public String printBeanReviewAverage(ArrayList<BeanReview> beanReviewList) {
        String str = "<html>";
        if (beanReviewList.isEmpty()) {
            str += "No bean review found!";
        } else {
            for (BeanReview i : beanReviewList) {
                str += "<br/>";
                str += "Bean Review for Bean ID : " + i.getBeanID() + "<br/>";
                str += "Overall score : " + i.getAverageReview(i.getBrewNoteList()) + "<br/>";
                for (BrewNote m : i.getBrewNoteList()) {
                    str += "Special Notes by " + m.getTaster() + " : " + m.getNote() + "<br/>";
                }
            }
        }
        str += "</html>";
        EventLog.getInstance().logEvent(new Event("Print average rating of bean review "));
        return str;
    }

}
