package persistence;

import model.BeanReview;
import model.BrewNote;
import model.Purchase;
import model.Beans;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

// Represents a reader that reads purchase and bean review from JSON Array stored in file
// Citation: sample code from JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads purchase from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ArrayList<Purchase> readPurchase() throws IOException {
        String jsonData = readFile(source);
        JSONArray jsonArray = new JSONArray(jsonData);
        return parsePurchase(jsonArray);
    }

    // EFFECTS: reads bean review from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ArrayList<BeanReview> readBeanReview() throws IOException {
        String jsonData = readFile(source);
        JSONArray jsonArray = new JSONArray(jsonData);
        return parseBeanReview(jsonArray);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses purchase from JSON Array and returns it
    private ArrayList<Purchase> parsePurchase(JSONArray jsonArray) {
        ArrayList<Purchase> purchaseList = new ArrayList<Purchase>();
        for (Object obj : jsonArray) {
            JSONObject jsonPurchase = (JSONObject) obj;

            String buyer = jsonPurchase.getString("buyer");
            Purchase purchase = new Purchase(buyer);
            addBeans(purchase, jsonPurchase);
            purchaseList.add(purchase);

        }
        return purchaseList;
    }


    // EFFECTS: parses bean review from JSON Array and returns it
    private ArrayList<BeanReview> parseBeanReview(JSONArray jsonArray) {
        ArrayList<BeanReview> beanReviewList = new ArrayList<BeanReview>();
        for (Object obj : jsonArray) {
            JSONObject jsonBeanReview = (JSONObject) obj;

            int beanID = jsonBeanReview.getInt("beanID");
            BeanReview beanReview = new BeanReview(beanID);
            addBrewNotes(beanReview, jsonBeanReview);
            beanReviewList.add(beanReview);

        }
        return beanReviewList;
    }

    // MODIFIES: pr
    // EFFECTS: parses purchase from JSON object and adds them to workroom
    private void addBeans(Purchase pr, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Beans");
        for (Object json : jsonArray) {
            JSONObject nextBeans = (JSONObject) json;
            addBean(pr, nextBeans);
        }
    }

    // MODIFIES: pr
    // EFFECTS: parses Purchase from JSON Array and adds it to Purchase
    private void addBean(Purchase pr, JSONObject jsonObject) {
        int beanID = (int) jsonObject.get("beanID");
        String roaster = jsonObject.getString("roaster");
        String origin = jsonObject.getString("origin");
        String process = jsonObject.getString("process");
        int roastYYyy = jsonObject.getInt("roast year");
        int roastMM = jsonObject.getInt("roast month");
        int roastDD = jsonObject.getInt("roast date");

        Beans beans = new Beans(beanID, roaster, origin, process, roastYYyy, roastMM, roastDD);
        pr.addBean(beans);
    }

    // MODIFIES: br
    // EFFECTS: parses BrewNote from JSON Array and adds them to BrewNote
    private void addBrewNotes(BeanReview br, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("BrewNote");
        for (Object json : jsonArray) {
            JSONObject nextBrewNote = (JSONObject) json;
            addBrewNote(br, nextBrewNote);
        }
    }

    // MODIFIES: br
    // EFFECTS: parses BrewNote from JSON Array and adds it to BrewNote
    private void addBrewNote(BeanReview br, JSONObject jsonObject) {
        int beanID = (int)jsonObject.get("beanID");
        String taster = jsonObject.getString("taster");
        int ar = jsonObject.getInt("aroma");
        int ac = jsonObject.getInt("acidity");
        int sw = jsonObject.getInt("sweet");
        int bo = jsonObject.getInt("body");
        int fin = jsonObject.getInt("finish");
        int overall = jsonObject.getInt("overall");
        String note = jsonObject.getString("note");

        BrewNote brewnote = new BrewNote(beanID, taster, ar, ac, sw, bo, fin, overall, note);
        br.addBrewNote(brewnote);
    }


}
