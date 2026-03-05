package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
// import java.io.FileNotFoundException;
// import java.io.IOException;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.Anime;
import model.AnimeList;
import persistence.JsonReader;
import persistence.JsonWriter;

// Anime record and recomendation application
// Reference: TellerApp UI class, JsonSerializationDemo UI class
@ExcludeFromJacocoGeneratedReport
public class AnimeApp {
    private static final String JSON_STORE = "./data/animelist.json";
    private AnimeList animeList;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the anime application
    public AnimeApp() {
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user unput
    private void runApp() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false; // quit
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nThanks for using");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            doAddAnime();
        } else if (command.equals("d")) {
            doDeleteAnime();
        } else if (command.equals("c")) {
            doAddComment();
        } else if (command.equals("v")) {
            doViewAnimeList();
        } else if (command.equals("p")) {
            doPrintRecommendation();
        } else if (command.equals("s")) {
            doSaveAnimes();
        } else if (command.equals("l")) {
            doLoadAnimes();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes accounts
    private void init() {
        animeList = new AnimeList();
        input = new Scanner(System.in);
        input.useDelimiter("\r?\n|\r");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add an anime");
        System.out.println("\td -> delete an anime");
        System.out.println("\tc -> add a comment");
        System.out.println("\tv -> view whole list");
        System.out.println("\tp -> print recommendations");
        System.out.println("\ts -> save anime list to file");
        System.out.println("\tl -> load anime list from file ");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: promosts users to enter a name and a rating,
    // adds the anime to the list or updates its rating
    private void doAddAnime() {
        System.out.println("\nAnime name?");
        String name = input.next();
        System.out.println("Enter rating (0.0 ~ 5.0): ");

        try {
            double rating = input.nextDouble();

            if (rating < 0.0 || rating > 5.0) {
                System.out.println("Rating out of range...\n");
            } else {
                animeList.addAnime(name, rating);
                System.out.println("Added " + name + " successfully!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid rating...\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: promosts users to enter a name and deletes the anime
    private void doDeleteAnime() {
        System.out.println("\nAnime name?");
        String name = input.next();

        if (animeList.findAnime(name)==null) {
            System.out.println("Please enter the name of an anime that exists in the list!");
        } else {
            animeList.deleteAnime(name);
            System.out.println("Deleted " + name + " successfully!");
        }
    }

    // MODIFIES: this
    // EFFECTS: promosts users to enter a name and a comment,
    // and adds the comment to the anime
    private void doAddComment() {
        System.out.println("\nWhich anime to add comment?");
        String name = input.next();
        if (animeList.findAnime(name)==null) {
            System.out.println("Please enter the name of an anime that exists in the list!");
        } else {
            System.out.println("Write a comment: ");
            String comment = input.next();

            animeList.addComment(name, comment);
            System.out.println("Finished adding comment!");
        }

    }

    // EFFECTS: lists all animes in the list with their information
    private void doViewAnimeList() {
        ArrayList<Anime> animes = animeList.getAnimeList();
        if (animes.isEmpty()) {
            System.out.println("No anime in the list. Better add an anime first...");
        } else {
            for (Anime anime : animes) {
                System.out.println("\n" + anime);
            }
            System.out.println("Here is your anime list!");

        }
    }

    // EFFECTS: lists all animes in the list which have ratings
    // equal to or greater than the threshold
    private void doPrintRecommendation() {
        System.out.println("Enter threshold (0.0 ~ 5.0): ");

        try {
            double threshold = input.nextDouble();
            ArrayList<Anime> animes = animeList.getRecommendedAnimes(threshold);

            if (threshold < 0.0 || threshold > 5.0) {
                System.out.println("Threshold out of range...\n");
            } else if (animes.isEmpty()) {
                System.out.println("No anime meet the required threshold.");
            } else {
                for (Anime anime : animes) {
                    System.out.println("\n" + anime);
                }
                System.out.println("Here is your recommendation list!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Please enter a threshold...\n");
        }
    }

    // EFFECTS: saves the animelist to file
    private void doSaveAnimes() {
        try {
            jsonWriter.open();
            jsonWriter.write(animeList);
            jsonWriter.close();
            System.out.println("Saved anime list to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void doLoadAnimes() {
        try {
            animeList = jsonReader.read();
            System.out.println("Loaded anime list from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
