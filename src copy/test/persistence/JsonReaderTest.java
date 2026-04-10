package persistence;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import model.Anime;
import model.AnimeList;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            AnimeList al = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyAnimeList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyAnimeList.json");
        try {
            AnimeList al = reader.read();
            assertEquals(0, al.getAnimeList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralAnimeList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralAnimeList.json");
        try {
            AnimeList al = reader.read();
            List<Anime> animes = al.getAnimeList();
            assertEquals(3, animes.size());
            checkAnime("A", 4.7, "I love the plot!", animes.get(0));
            checkAnime("B", 4.9, "A very warm story!", animes.get(1));
            checkAnime("C", 2.0, "Not worth watching.\n\tWell, the music is okay.", animes.get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
