package model;

import org.json.JSONObject;
import persistence.Writable;

//A class to represent a coffee tasting note
public class BrewNote {
    private int beanID;
    private String taster;
    private int aroma;
    private int acidity;
    private int sweet;
    private int body;
    private int finish;
    private int overall;
    private String note;

    //EFFECT: construct instance of brewnote to store tasting note details
    public BrewNote(int id, String taster, int ar, int ac, int sw, int bo, int fin, int overall, String note) {
        this.beanID = id;
        this.taster = taster;
        this.aroma = ar;
        this.acidity = ac;
        this.sweet = sw;
        this.body = bo;
        this.finish = fin;
        this.overall = overall;
        this.note = note;
    }

    public int getBeanID() {
        return beanID;
    }

    public String getTaster() {
        return taster;
    }

    public int getAroma() {
        return aroma;
    }

    public int getAcidity() {
        return acidity;
    }

    public int getSweet() {
        return sweet;
    }

    public int getBody() {
        return body;
    }

    public int getFinish() {
        return finish;
    }

    public int getOverall() {
        return overall;
    }

    public String getNote() {
        return note;
    }

    public void setBeanID(int beanID) {
        this.beanID = beanID;
    }

    public void setTaster(String taster) {
        this.taster = taster;
    }

    public void setAroma(int aroma) {
        this.aroma = aroma;
    }

    public void setAcidity(int acidity) {
        this.acidity = acidity;
    }

    public void setSweet(int sweet) {
        this.sweet = sweet;
    }

    public void setBody(int body) {
        this.body = body;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }

    public void setOverall(int overall) {
        this.overall = overall;
    }

    public void setNote(String note) {
        this.note = note;
    }

    // EFFECTS: returns things in this brewnote as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("beanID", beanID);
        json.put("taster", taster);
        json.put("aroma", aroma);
        json.put("acidity", acidity);
        json.put("sweet", sweet);
        json.put("body", body);
        json.put("finish", finish);
        json.put("overall", overall);
        json.put("note", note);
        return json;
    }


}
