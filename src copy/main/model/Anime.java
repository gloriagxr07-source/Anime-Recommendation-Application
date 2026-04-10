package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents an Anime having a name, a rating out of 5 and 
// an introductoin
public class Anime implements Writable {

    private String name;
    private double rating;
    private String intro;

    // REQUIRES: 0.0 <= rating <= 5.0
    // EFFECTS: constructs an anime object which name is set to name,
    // rating is set to rating and introductoin is empty string
    public Anime(String name, double rating) {
        this.name = name;
        this.rating = rating;
        this.intro = "";
    }

    // // REQUIRES: 0.0 <= rating <= 5.0
    // // EFFECTS: constructs an anime object which name is set to name,
    // // rating is set to rating and introductoin is set to intro
    // public Anime(String name, double rating, String intro) {
    //     this.name = name;
    //     this.rating = rating;
    //     this.intro = intro;
    // }

    // EFFECTS: returns name of the anime
    public String getName() {
        return name;
    }

    // EFFECTS: returns rating of the anime
    public double getRating() {
        return rating;
    }

    // EFFECTS: returns introduction of the anime
    public String getIntro() {
        return intro;
    }

    // REQUIRES: 0.0 <= rating <=5.0
    // MODIFIES: this
    // EFFECTS: sets the rating of the anime to rating
    public void setRating(double rating) {
        this.rating = rating;
    }

    // MODIFIES: this
    // EFFECTS: sets the introduction of the anime to intro
    public void setIntro(String intro) {
        this.intro = intro;
    }

    // MODIFIES: this
    // EFFECTS: set the comment as introduction
    // if it is empty string;
    // otherwise, add the comment in a seperate line at the
    // end of introduction
    public void addComment(String comment) {
        if (intro.equals("")) {
            setIntro(comment);
        } else {
            intro = intro + "\n\t      " + comment;
        }
    }

    // EFFECTS: returns a string representation of anime
    @Override
    public String toString() {
        return "Name: " + name + "\nRating: " + rating + "\nIntroduction: " + intro;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("rating", rating);
        json.put("introduction", intro);
        return json;
    }

}
