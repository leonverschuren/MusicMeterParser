package leonverschuren.musicmeterparser.services;

import leonverschuren.musicmeterparser.model.Track;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class AlbumParserTest {
    private AlbumParser target;
    private Document document;

    @Before
    public void fetchDocument() throws Exception {
        target = new AlbumParser();
        document = new AlbumScraper().fetchAlbumDocument(1);
    }

    @Test
    public void extractsAlbumArtist() {
        // Arrange

        // Act
        String result = target.extractAlbumArtist(document);

        // Assert
        Assert.assertEquals("Dr. Dre", result);
    }

    @Test
    public void extractsAlbumTitle() {
        // Arrange

        // Act
        String result = target.extractAlbumTitle(document);

        // Assert
        Assert.assertEquals("The Chronic", result);
    }

    @Test
    public void extractsYear() {
        // Arrange

        // Act
        String result = target.extractYear(document);

        // Assert
        Assert.assertEquals("1992", result);
    }

    @Test
    public void extractsRating() {
        // Arrange

        // Act
        String result = target.extractRating(document);

        // Assert
        Assert.assertEquals("3.97", result);
    }

    @Test
    public void extractsGenre() {
        // Arrange

        // Act
        String result = target.extractGenre(document);

        // Assert
        Assert.assertEquals("Hip-Hop", result);
    }

    @Test
    public void extractsLabel() {
        // Arrange

        // Act
        String result = target.extractLabel(document);

        // Assert
        Assert.assertEquals("Death Row", result);
    }

    @Test
    public void extractsTracks() {
        // Arrange

        // Act
        List<Track> result = target.extractTracks(document);

        // Assert
        Assert.assertEquals(23, result.size());

        Track first = result.get(0);
        Assert.assertEquals("The Chronic (Intro)", first.getTitle());
        Assert.assertEquals(Arrays.asList("Dr. Dre", "Snoop Doggy Dogg"), first.getArtists());

        Track last = result.get(22);
        Assert.assertEquals("Would You Ride *", last.getTitle());
        Assert.assertEquals(Arrays.asList("Dr. Dre", "Kurupt", "Amber", "Tyrone", "Daz", "Snoop Doggy Dogg"), last.getArtists());
    }
}
