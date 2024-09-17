package model;


import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import java.util.ArrayList;

//A Class to handle a set of purchased beans
public class Purchase implements Writable {
    private String buyer;
    private ArrayList<Beans> beansList;

    //EFFECT: construct instance of purchase with names provided
    public Purchase(String buyer) {
        this.buyer = buyer;
        beansList = new ArrayList<>();
    }

    public String getBuyer() {
        return buyer;
    }

    //EFFECT: return Beans arraylist
    public ArrayList<Beans> getBeanList() {
        return beansList;
    }

    //MODIFIES: this
    //EFFECT: add beans to beanslist
    public void addBean(Beans beans) {
        beansList.add(beans);
        EventLog.getInstance().logEvent(new Event("Bean ID " + beans.getBeanID() + " added to Bean List"));
    }

    //REQUIRE : beansList.size() >= 1
    //MODIFIES: this
    //EFFECT: remove beans that is added at last
    public void removeLastBean() {
        beansList.remove(beansList.size() - 1);
        EventLog.getInstance().logEvent(new Event("Remove the last added bean"));
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    // EFFECTS: returns things in this purchase as a JSON array
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("buyer", buyer);
        json.put("Beans", beansToJson());
        return json;
    }

    // EFFECTS: returns things in this purchase as a JSON array
    private JSONArray beansToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Beans t : beansList) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }

}
