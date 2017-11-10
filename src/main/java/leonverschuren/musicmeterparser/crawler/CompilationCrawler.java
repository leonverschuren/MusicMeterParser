package leonverschuren.musicmeterparser.crawler;

import org.jsoup.nodes.Document;

public class CompilationCrawler extends RegularAlbumCrawler {
    CompilationCrawler(Document document) {
        super(document);
    }

    @Override
    public String extractAlbumTitle() {
        return document.select("#content div.details > h1 > span").text();
    }

    @Override
    public String extractAlbumArtist() {
        return "";
    }
}
