package persistence;

import org.json.JSONObject;

// Represents an object that can be converted to JSON format
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
