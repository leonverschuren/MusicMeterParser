package leonverschuren.musicmeterparser.crawler;

import leonverschuren.musicmeterparser.model.Track;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class CompilationCrawlerTest {
    private CompilationCrawler target;

    @Before
    public void fetchDocument() throws Exception {
        Document document = Jsoup.connect("https://www.musicmeter.nl/album/13039").cookie("cok", "1").get();
        target = new CompilationCrawler(document);
    }

    @Test
    public void extractsAlbumArtist() {
        // Arrange

        // Act
        String result = target.extractAlbumArtist();

        // Assert
        Assert.assertEquals("", result);
    }

    @Test
    public void extractsAlbumTitle() {
        // Arrange

        // Act
        String result = target.extractAlbumTitle();

        // Assert
        Assert.assertEquals("Dr. Dre Presents... The Aftermath", result);
    }

    @Test
    public void extractsTracks() {
        // Arrange

        // Act
        List<Track> result = target.extractTracks();

        // Assert
        Assert.assertEquals(16, result.size());

        Track first = result.get(0);
        Assert.assertEquals("Aftermath (The Intro)", first.getTitle());
        Assert.assertEquals(Arrays.asList("Dr. Dre"), first.getArtists());

        Track last = result.get(15);
        Assert.assertEquals("Fame", last.getTitle());
        Assert.assertEquals(Arrays.asList("RC", "King Tee"), last.getArtists());
    }
}
