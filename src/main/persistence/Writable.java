package persistence;

import org.json.JSONObject;
// Citation: sample code from JsonSerializationDemo

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
