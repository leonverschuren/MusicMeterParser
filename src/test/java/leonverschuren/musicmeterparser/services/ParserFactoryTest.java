package leonverschuren.musicmeterparser.services;

import leonverschuren.musicmeterparser.ParserFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;

public class ParserFactoryTest {
    @Test
    public void albumDocumentReturnsAlbumParser() throws Exception {
        // Arrange
        Document document = Jsoup.connect("https://www.musicmeter.nl/album/1")
                .cookie("cok", "1")
                .get();

        // Act
        AlbumParser result = ParserFactory.createParser(document);

        // Assert
        Assert.assertEquals(AlbumParser.class, result.getClass());
    }

    @Test
    public void compilationDocumentReturnsCompilationParser() throws Exception {
        // Arrange
        Document document = Jsoup.connect("https://www.musicmeter.nl/album/13039")
                .cookie("cok", "1")
                .get();

        // Act
        AlbumParser result = ParserFactory.createParser(document);

        // Assert
        Assert.assertEquals(CompilationParser.class, result.getClass());
    }
}
