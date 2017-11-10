package leonverschuren.musicmeterparser.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.net.URLEncoder;

public class CrawlerFactory {
    public static AlbumCrawler createAlbumCrawler(int albumId) throws Exception {
        return createAlbumCrawler(albumId, true);
    }

    static AlbumCrawler createAlbumCrawler(int albumId, boolean withExceptionHandling) throws Exception {
        AlbumCrawler crawler = determineAlbumCrawler(albumId);

        if (withExceptionHandling) {
            return new ExceptionHandlingAlbumCrawler(crawler);
        } else {
            return crawler;
        }
    }

    private static AlbumCrawler determineAlbumCrawler(int albumId) throws Exception {
        Document document = Jsoup.connect("https://www.musicmeter.nl/album/" + String.valueOf(albumId))
                .cookie("cok", "1")
                .get();

        Elements header = document.select("h1 a");

        return (header.size() != 0) ? new RegularAlbumCrawler(document) : new CompilationCrawler(document);
    }

    public static SearchCrawler createSearchCrawler(String term) throws Exception {
        Document document = Jsoup.connect("https://www.musicmeter.nl/site/search?q=" + URLEncoder.encode(term, "UTF-8"))
                .cookie("cok", "1")
                .get();

        return new SearchCrawler(document);
    }

    public static StatsCrawler createStatsCrawler(int albumId) throws Exception {
        Document document = Jsoup.connect("https://www.musicmeter.nl/album/" + String.valueOf(albumId) + "/stats/")
                .cookie("cok", "1")
                .get();

        return new StatsCrawler(document);
    }
}
