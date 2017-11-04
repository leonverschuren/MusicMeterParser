package leonverschuren.musicmeterparser.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class StatsCrawlerTest {
    private StatsCrawler target;

    @Before
    public void fetchDocument() throws Exception {
        Document document = Jsoup.connect("https://www.musicmeter.nl/album/1/stats")
                .cookie("cok", "1").get();

        target = new StatsCrawler(document);
    }

    @Test
    public void extractsReleaseDate() {
        // Arrange

        // Act
        String result = target.extractReleaseDate();

        // Assert
        Assert.assertEquals("1992-12-15", result);
    }
}
