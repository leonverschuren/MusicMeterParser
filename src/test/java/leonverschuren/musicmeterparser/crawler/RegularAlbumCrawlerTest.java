package leonverschuren.musicmeterparser.crawler;

import leonverschuren.musicmeterparser.model.Track;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class RegularAlbumCrawlerTest {
    private AlbumCrawler target;

    @Before
    public void fetchDocument() throws Exception {
        Document document = Jsoup.connect("https://www.musicmeter.nl/album/1").cookie("cok", "1").get();
        target = new RegularAlbumCrawler(document);
    }

    @Test
    public void extractsAlbumArtist() {
        // Arrange

        // Act
        String result = target.extractAlbumArtist();

        // Assert
        Assert.assertEquals("Dr. Dre", result);
    }

    @Test
    public void extractsAlbumTitle() {
        // Arrange

        // Act
        String result = target.extractAlbumTitle();

        // Assert
        Assert.assertEquals("The Chronic", result);
    }

    @Test
    public void extractsCover() {
        // Arrange

        // Act
        String result = target.extractCover();

        // Assert
        Assert.assertEquals("https://www.musicmeter.nl/images/cover/0/1.jpg", result);
    }

    @Test
    public void extractsYear() {
        // Arrange

        // Act
        String result = target.extractYear();

        // Assert
        Assert.assertEquals("1992", result);
    }

    @Test
    public void extractsRating() {
        // Arrange

        // Act
        String result = target.extractRating();
        float rating = Float.parseFloat(result);

        // Assert
        Assert.assertEquals(3.99f, rating, 0.1f);
    }

    @Test
    public void extractsGenre() {
        // Arrange

        // Act
        String result = target.extractGenre();

        // Assert
        Assert.assertEquals("Hip-Hop", result);
    }

    @Test
    public void extractsLabel() {
        // Arrange

        // Act
        String result = target.extractLabel();

        // Assert
        Assert.assertEquals("Death Row", result);
    }

    @Test
    public void extractsTracks() {
        // Arrange

        // Act
        List<Track> result = target.extractTracks();

        // Assert
        Assert.assertEquals(23, result.size());

        Track first = result.get(0);
        Assert.assertEquals("The Chronic (Intro)", first.getTitle());
        Assert.assertEquals(Arrays.asList("Dr. Dre", "Snoop Doggy Dogg"), first.getArtists());

        Track last = result.get(22);
        Assert.assertEquals("Would You Ride *", last.getTitle());
        Assert.assertEquals(Arrays.asList("Dr. Dre", "Kurupt", "Amber", "Tyrone", "Daz", "Snoop Doggy Dogg"), last.getArtists());
    }

    @Test
    public void extractsAlbumTitleWithParentheses() throws Exception {
        // Arrange
        Document document = Jsoup.connect("https://www.musicmeter.nl/album/30643").cookie("cok", "1").get();
        RegularAlbumCrawler target = new RegularAlbumCrawler(document);

        // Act
        String result = target.extractYear();

        // Assert
        Assert.assertEquals("1997", result);
    }
}
