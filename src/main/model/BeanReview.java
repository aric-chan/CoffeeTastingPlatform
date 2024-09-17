package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//A class to handle bean review, score based on brewnotes
public class BeanReview implements Writable {
    private ArrayList<BrewNote> brewnoteList;
    private int beanID;
    private Map<String,Double> averageReviewMap;

    //EFFECT: construct instance of BeanReview by beanID
    public BeanReview(int beanID) {
        this.beanID = beanID;
        brewnoteList = new ArrayList<>();
    }

    public int getBeanID() {
        return beanID;
    }

    //EFFECT: return BrewNote arraylist
    public ArrayList<BrewNote> getBrewNoteList() {
        return brewnoteList;
    }

    //REQUIRE: BrewNote ArrayList not empty
    //EFFECT: return HashMap of all averaged score by taste dimension contributed by all tasters for one coffee ID
    public Map<String,Double> getAverageReview(ArrayList<BrewNote> brewnote) {
        averageReviewMap = new HashMap<>();

        ArrayList<Double> averageList = averageHelper(brewnote);
        averageReviewMap.put("Overall",averageHelper(brewnote).get(0));
        averageReviewMap.put("Acidity",averageHelper(brewnote).get(1));
        averageReviewMap.put("Aroma",averageHelper(brewnote).get(2));
        averageReviewMap.put("Finish",averageHelper(brewnote).get(3));
        averageReviewMap.put("Sweet",averageHelper(brewnote).get(4));
        averageReviewMap.put("Body",averageHelper(brewnote).get(5));
        String message = "Calculated average rating of BeanID " + brewnote.get(0).getBeanID();
        EventLog.getInstance().logEvent(new Event(message));
        return averageReviewMap;
    }

    //REQUIRE: BrewNote ArrayList not empty
    //EFFECT: return an arraylist of average score of a brew note
    private ArrayList<Double> averageHelper(ArrayList<BrewNote> brewnote) {
        ArrayList<Double> averageListHelper = new ArrayList<>();
        int overalSum = 0;
        int acidSum = 0;
        int aromaSum = 0;
        int finishSum = 0;
        int sweetSum = 0;
        int bodySum = 0;

        for (BrewNote i :brewnote) {
            overalSum += i.getOverall();
            acidSum += i.getAcidity();
            aromaSum += i.getAroma();
            finishSum += i.getFinish();
            sweetSum += i.getSweet();
            bodySum += i.getBody();
        }
        averageListHelper.add((double) overalSum / brewnote.size());
        averageListHelper.add((double) acidSum / brewnote.size());
        averageListHelper.add((double) aromaSum / brewnote.size());
        averageListHelper.add((double) finishSum / brewnote.size());
        averageListHelper.add((double) sweetSum / brewnote.size());
        averageListHelper.add((double) bodySum / brewnote.size());

        return averageListHelper;
    }

    //MODIFIES: this
    //EFFECT: add brewnote to brewnoteList
    public void addBrewNote(BrewNote note) {
        brewnoteList.add(note);
        String message = "BrewNote of BeanID " + note.getBeanID() + " added to BrewNote List";
        EventLog.getInstance().logEvent(new Event(message));
    }

    //REQUIRE : brewnoteList.size() >= 1
    //MODIFIES: this
    //EFFECT: remove brewnote that is added at last
    public void removeLastBrewNote() {
        brewnoteList.remove(brewnoteList.size() - 1);
    }

    public void setBeanID(int beanID) {
        this.beanID = beanID;
    }

    // EFFECTS: returns things in this bean review as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("beanID", beanID);
        json.put("BrewNote", brewNoteToJson());
        return json;
    }

    // EFFECTS: returns things in this bean review as a JSON array
    private JSONArray brewNoteToJson() {
        JSONArray jsonArray = new JSONArray();

        for (BrewNote t : brewnoteList) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }


}