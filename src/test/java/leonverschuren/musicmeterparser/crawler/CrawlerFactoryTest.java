package leonverschuren.musicmeterparser.crawler;

import org.junit.Assert;
import org.junit.Test;

public class CrawlerFactoryTest {
    @Test
    public void albumDocumentReturnsAlbumParser() throws Exception {
        // Arrange

        // Act
        AlbumCrawler result = CrawlerFactory.createAlbumCrawler(1);

        // Assert
        Assert.assertEquals(RegularAlbumCrawler.class, result.getClass());
    }

    @Test
    public void compilationDocumentReturnsCompilationParser() throws Exception {
        // Arrange

        // Act
        AlbumCrawler result = CrawlerFactory.createAlbumCrawler(13039);

        // Assert
        Assert.assertEquals(CompilationCrawler.class, result.getClass());
    }
}
