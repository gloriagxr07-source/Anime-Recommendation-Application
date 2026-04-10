package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents an animelist having a list of anime which have been 
// recommended by a user (maintained in the order in which 
// they were add to the list)
// (no two animes in the list have the same name)
public class AnimeList implements Writable {

    private ArrayList<Anime> animes;

    // EFFECTS: constructs a AnimeList object with an empty
    // list of anime
    public AnimeList() {
        animes = new ArrayList<Anime>();
    }

    // MODIFIES: this
    // EFFECTS: adds anime to end of the list animes,
    // if not already in the list;
    // otherwise, update the rating
    public void addAnime(String animeName, double rating) {
        for (Anime next : animes) {
            if (next.getName().equals(animeName)) {
                next.setRating(rating);
                return;
            }
        }
        Anime newAnime = new Anime(animeName, rating);
        animes.add(newAnime);

        EventLog.getInstance().logEvent(new Event("An anime added to anime list"));
    }

    // REQUIRES: findAnime(animeName) != null
    // MODIFIES: this
    // EFFECTS: deletes the anime from the list,
    public void deleteAnime(String animeName) {
        ArrayList<Anime> newAnimes = new ArrayList<Anime>();
        for (Anime next : animes) {
            if (!next.getName().equals(animeName)) {
                newAnimes.add(next);
            }
        }
        this.animes = newAnimes;
    }

    // EFFECTS: returns the animelist
    public ArrayList<Anime> getAnimeList() {
        return animes;
    }

    // REQUIRES: 0.0 <= threshold <= 5.0
    // EFFECTS: returns a list of animes which rating is
    // greater than or equal to threshold
    // in the order they were add to the list
    public ArrayList<Anime> getRecommendedAnimes(double threshold) {
        ArrayList<Anime> printedAnimes = new ArrayList<Anime>();
        for (Anime next : animes) {
            if (next.getRating() >= threshold) {
                printedAnimes.add(next);
            }
        }
        return printedAnimes;
    }

    // REQUIRES: findAnime(animeName) != null
    // MODIFIES: this
    // EFFECTS: adds the comment to the given anime's introduction
    public void addComment(String animeName, String comment) {
        Anime anime = findAnime(animeName);
        anime.addComment(comment);

        EventLog.getInstance().logEvent(new Event("A comment added to specific anime in the list"));

    }

    // EFFECTS: finds out the anime of the given name if anime exists in the list;
    // otherwise, return null
    public Anime findAnime(String animeName) {
        for (Anime next : animes) {
            if (next.getName().equals(animeName)) {
                return next;
            }
        }
        return null;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("animes", animesToJson());
        return json;
    }

    // EFFECTS: returns animes in this list as a JSON array
    private JSONArray animesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Anime a : animes) {
            jsonArray.put(a.toJson());
        }

        return jsonArray;
    }
}
