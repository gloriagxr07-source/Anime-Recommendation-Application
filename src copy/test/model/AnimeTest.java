package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class AnimeTest {

    private Anime testAnime;

    @BeforeEach
    void runBefore() {
        testAnime = new Anime("A", 4.7);
    }

    @Test
    void testAnime() {
        assertEquals("A", testAnime.getName());
        assertEquals(4.7, testAnime.getRating());
        assertEquals("", testAnime.getIntro());
    }

    // @Test
    // void testAnimeWithIntro() {
    //     Anime animeWithIntro = new Anime("A", 4.7, "nice");
    //     assertEquals("A", animeWithIntro.getName());
    //     assertEquals(4.7, animeWithIntro.getRating());
    //     assertEquals("nice", animeWithIntro.getIntro());
    // }

    @Test
    void testAddComment() {
        testAnime.addComment("An interesting anime.");
        assertEquals("A", testAnime.getName());
        assertEquals(4.7, testAnime.getRating());
        assertEquals("An interesting anime.", testAnime.getIntro());

        testAnime.addComment("I like the plot and music.");
        assertEquals("A", testAnime.getName());
        assertEquals(4.7, testAnime.getRating());
        assertEquals("An interesting anime.\n\t      I like the plot and music.", testAnime.getIntro());

    }

    @Test
    void testAddCommentAfterNull() {
        testAnime.addComment("");
        testAnime.addComment("An interesting anime.");

        assertEquals("A", testAnime.getName());
        assertEquals(4.7, testAnime.getRating());
        assertEquals("An interesting anime.", testAnime.getIntro());
    }

    @Test
    void testToString() {
        assertEquals("Name: A\nRating: 4.7\nIntroduction: ", testAnime.toString());

        testAnime.addComment("An interesting anime.");
        assertEquals("Name: A\nRating: 4.7\nIntroduction: An interesting anime.", testAnime.toString());
    }

    @Test
    void testToStringTwoComments() {
        testAnime.addComment("Comment1");
        testAnime.addComment("Comment2");

        assertEquals("Name: A\nRating: 4.7\nIntroduction: Comment1\n\t      Comment2", testAnime.toString());
    }

    @Test
    void testSetRating() {
        testAnime.setRating(5.0);
        assertEquals(5.0, testAnime.getRating());
        testAnime.setRating(0.0);
        assertEquals(0.0, testAnime.getRating());
        testAnime.setRating(3.3);
        assertEquals(3.3, testAnime.getRating());
    }

    // @Test
    // void testSetIntro() {
    //     testAnime.setIntro("Not so interesting when I watched it again");
    //     assertEquals("Not so interesting when I watched it again", testAnime.getIntro());
    // }
}
