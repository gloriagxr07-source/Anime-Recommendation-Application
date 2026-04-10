package persistence;

import model.AnimeList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads animelist from JSON data stored in file
// Reference: JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads animelist from file and returns it;
    // throws IOException if an error occurs reading data from file
    public AnimeList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAnimeList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses animelist from JSON object and returns it
    private AnimeList parseAnimeList(JSONObject jsonObject) {
        AnimeList al = new AnimeList();
        addAnimes(al, jsonObject);
        return al;
    }

    // MODIFIES: al
    // EFFECTS: parses animes from JSON object and adds them to animelist
    private void addAnimes(AnimeList al, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("animes");
        for (Object json : jsonArray) {
            JSONObject nextAnime = (JSONObject) json;
            addAnime(al, nextAnime);
        }
    }

    // MODIFIES: al
    // EFFECTS: parses anime from JSON object and adds it to animelist
    private void addAnime(AnimeList al, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double rating  = jsonObject.getDouble("rating");
        String intro = jsonObject.getString("introduction");
        al.addAnime(name, rating);
        al.addComment(name, intro);
    }

}
