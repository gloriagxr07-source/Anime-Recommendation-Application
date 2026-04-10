package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import model.Anime;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class JsonTest {
    protected void checkAnime(String name, double rating, String intro, Anime anime) {
        assertEquals(name, anime.getName());
        assertEquals(rating, anime.getRating());
        assertEquals("Name: " + name + "\nRating: " + rating + "\nIntroduction: " + intro,
                anime.toString());
    }
}
