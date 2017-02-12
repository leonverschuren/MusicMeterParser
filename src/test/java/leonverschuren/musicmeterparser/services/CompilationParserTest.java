package leonverschuren.musicmeterparser.services;

import leonverschuren.musicmeterparser.model.Track;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class CompilationParserTest {
    private AlbumParser target;
    private Document document;

    @Before
    public void fetchDocument() throws Exception {
        target = new CompilationParser();
        document = new AlbumScraper().fetchAlbumDocument(13039);
    }

    @Test
    public void extractsTracks() {
        // Arrange

        // Act
        List<Track> result = target.extractTracks(document);

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
