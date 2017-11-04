package leonverschuren.musicmeterparser.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.net.URLEncoder;

public class CrawlerFactory {
    public static AlbumCrawler createAlbumCrawler(int albumId) throws Exception {
        Document document = Jsoup.connect("https://www.musicmeter.nl/album/" + String.valueOf(albumId))
                .cookie("cok", "1")
                .get();

        Elements header = document.select("h1 a");

        if (header.size() != 0) {
            return new AlbumCrawler(document);
        } else {
            return new CompilationCrawler(document);
        }
    }

    public static  SearchCrawler createSearchCrawler(String term) throws Exception
    {
        Document document = Jsoup.connect("https://www.musicmeter.nl/site/search?q=" + URLEncoder.encode(term, "UTF-8"))
                .cookie("cok", "1")
                .get();

        return new SearchCrawler(document);
    }
}
