package persistence;

import org.junit.jupiter.api.Test;

import model.Anime;
import model.AnimeList;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            AnimeList al = new AnimeList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyAnimeList.json");
            writer.open();
            writer.write(al);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyAnimeList.json");
            al = reader.read();
            assertEquals(0, al.getAnimeList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            AnimeList al = new AnimeList();
            al.addAnime("a", 5);
            al.addAnime("b", 3.9);
            al.addAnime("c", 4.0);
            al.addComment("a", "What a great love!");
            al.addComment("b", "Not as well as this director's previous works.");
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralAnimeList.json");
            writer.open();
            writer.write(al);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralAnimeList.json");
            al = reader.read();
            List<Anime> animes = al.getAnimeList();
            assertEquals(3, animes.size());
            checkAnime("a", 5, "What a great love!", animes.get(0));
            checkAnime("b", 3.9, "Not as well as this director's previous works.", animes.get(1));
            checkAnime("c", 4.0, "", animes.get(2));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
