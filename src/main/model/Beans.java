package model;

import org.json.JSONObject;
import persistence.Writable;


// Beans Class to represent the details of coffee bean
public class Beans {
    private String roaster;
    private String origin;
    private String process;
    private int roastYYyy;
    private int roastMM;
    private int roastDD;
//    private static int count = 1;
    private int beanID;

    //EFFECT: construct instance of Beans to store various bean information in fields
    public Beans(int beanID, String roaster, String origin, String process, int roastYYyy, int roastMM, int roastDD) {
        this.beanID = beanID;
        this.roaster = roaster;
        this.origin = origin;
        this.process = process;
        this.roastYYyy = roastYYyy;
        this.roastMM = roastMM;
        this.roastDD = roastDD;
    }

    public String getRoaster() {
        return roaster;
    }

    public String getOrigin() {
        return origin;
    }

    public String getProcess() {
        return process;
    }

    public int getRoastYYyy() {
        return roastYYyy;
    }

    public int getRoastMM() {
        return roastMM;
    }

    public int getRoastDD() {
        return roastDD;
    }

    public int getBeanID() {
        return beanID;
    }

    public void setRoaster(String roaster) {
        this.roaster = roaster;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public void setRoastYYyy(int roastYYyy) {
        this.roastYYyy = roastYYyy;
    }

    public void setRoastMM(int roastMM) {
        this.roastMM = roastMM;
    }

    public void setRoastDD(int roastDD) {
        this.roastDD = roastDD;
    }

    // EFFECTS: returns things in this beans as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("beanID", beanID);
        json.put("roaster", roaster);
        json.put("origin", origin);
        json.put("process", process);
        json.put("roast year", roastYYyy);
        json.put("roast month", roastMM);
        json.put("roast date", roastDD);

        return json;
    }

}
