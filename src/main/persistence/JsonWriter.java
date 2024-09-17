package persistence;

import model.BeanReview;
import model.Purchase;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

// Represents a writer that writes JSON representation of purchase and bean review to file
// Citation: sample code from JsonSerializationDemo
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of purchase to file
    public void writePurchase(List<Purchase> purchaseList) {
        // Create a JSONArray to hold multiple Purchase JSON objects
        JSONArray jsonArray = new JSONArray();
        for (Purchase pr : purchaseList) {
            jsonArray.put(pr.toJson());
        }
        saveToFile(jsonArray.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of bean review to file
    public void writeBeanReview(List<BeanReview> beanReviewsList) {
        // Create a JSONArray to hold multiple BeanReview JSON objects
        JSONArray jsonArray = new JSONArray();
        for (BeanReview br : beanReviewsList) {
            jsonArray.put(br.toJson());
        }
        saveToFile(jsonArray.toString(TAB));
    }


    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
