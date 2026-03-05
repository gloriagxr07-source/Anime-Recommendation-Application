package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class AnimeListTest {

    private AnimeList testAnimeList;
    private Anime testAnime1;
    private Anime testAnime2;
    private Anime testAnime3;
    private Anime testAnime4;
    private Anime testAnime5;

    @BeforeEach
    void runBefore() {
        testAnimeList = new AnimeList();
        testAnime1 = new Anime("A", 4.7);
        testAnime2 = new Anime("B", 4.9);
        testAnime3 = new Anime("C", 2.0);
        testAnime4 = new Anime("D", 3.5);
        testAnime5 = new Anime("E", 4.7);
    }

    @Test
    void testAnimeList() {
        assertEquals(0, testAnimeList.getAnimeList().size());
    }

    @Test
    void testAddAnime() {
        testAnimeList.addAnime("A", 4.7);
        assertEquals(1, testAnimeList.getAnimeList().size());
        assertEquals("A", testAnimeList.getAnimeList().get(0).getName());
        assertEquals(4.7, testAnimeList.getAnimeList().get(0).getRating());

        testAnimeList.addAnime("B", 4.9);
        assertEquals(2, testAnimeList.getAnimeList().size());
        assertEquals("B", testAnimeList.getAnimeList().get(1).getName());
        assertEquals(4.9, testAnimeList.getAnimeList().get(1).getRating());
    }

    @Test
    void testAddAnimeSameName() {
        testAnimeList.addAnime("A", 4.7);
        testAnimeList.addAnime("A", 4.0);

        assertEquals(1, testAnimeList.getAnimeList().size());
        assertEquals("A", testAnimeList.getAnimeList().get(0).getName());
        assertEquals(4.0, testAnimeList.getAnimeList().get(0).getRating());
    }

    @Test
    void testDeleteAnime() {
        testAnimeList.addAnime("A", 4.7);
        testAnimeList.addAnime("B", 4.9);
        testAnimeList.addAnime("C", 3.0);

        testAnimeList.deleteAnime("A");

        assertEquals(2, testAnimeList.getAnimeList().size());
        assertEquals("B", testAnimeList.getAnimeList().get(0).getName());
        assertEquals("C", testAnimeList.getAnimeList().get(1).getName());
        assertEquals(4.9, testAnimeList.getAnimeList().get(0).getRating());
        assertEquals(3.0, testAnimeList.getAnimeList().get(1).getRating());

    }

    @Test
    void testGetRecommendedAnimes() {
        testAnimeList.addAnime("A", 4.0);
        testAnimeList.addAnime("B", 4.1);
        testAnimeList.addAnime("C", 3.9);
        testAnimeList.addAnime("D", 3.0);

        ArrayList<Anime> recommendations = testAnimeList.getRecommendedAnimes(4.0);
        assertEquals(2, recommendations.size());
        assertEquals("A", recommendations.get(0).getName());
        assertEquals("B", recommendations.get(1).getName());
    }

    @Test
    void testGetRecommendedAnimesBoundary() {
        testAnimeList.addAnime("A", 4.0);
        testAnimeList.addAnime("B", 4.9);

        assertEquals(0, testAnimeList.getRecommendedAnimes(5.0).size());
        ArrayList<Anime> recommendations = testAnimeList.getRecommendedAnimes(0.0);
        assertEquals(2, recommendations.size());
        assertEquals("A", recommendations.get(0).getName());
        assertEquals("B", recommendations.get(1).getName());
    }

    @Test
    void testAddComment() {
        testAnimeList.addAnime("A", 4.0);
        testAnimeList.addAnime("B", 4.9);

        testAnimeList.addComment("A", "Comment1");
        testAnimeList.addComment("A", "Comment2");

        assertEquals("Comment1\n\t      Comment2", testAnimeList.findAnime("A").getIntro());
    }

    @Test
    void testFindAnime() {
        testAnimeList.addAnime("A", 4.0);
        testAnimeList.addAnime("B", 4.1);
        testAnimeList.addAnime("C", 3.9);
        testAnimeList.addAnime("D", 3.0);
        assertEquals("A", testAnimeList.findAnime("A").getName());
        assertNull(testAnimeList.findAnime("E"));
    }
}
