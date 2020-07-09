package leonverschuren.musicmeterparser.crawler;

import leonverschuren.musicmeterparser.model.AlbumInfo;
import leonverschuren.musicmeterparser.model.SearchResult;
import lombok.extern.java.Log;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static java.util.stream.Collectors.toList;

@Log
public class SearchCrawler {
    private final Document document;

    SearchCrawler(Document document) {
        this.document = document;
    }

    public List<AlbumInfo> extractAlbumInfo() {
        List<AlbumInfo> results = new ArrayList<>();

        List<SearchResult> searchResults = extractSearchResults();
        for (SearchResult result : searchResults)
        {
            AlbumInfo albumInfo = new AlbumInfo();

            albumInfo.setId(result.getId());
            albumInfo.setTitle(String.format("%s - %s (%s)", result.getArtist(), result.getTitle(), result.getYear()));

            results.add(albumInfo);
        }

        return results;
    }

    public List<SearchResult> extractSearchResults() {
        Elements albums = document.select("#main a[data-tooltip-entity]");

        return albums.parallelStream().map(this::getSearchResult).collect(toList());
    }

    private SearchResult getSearchResult(Element element) {
        SearchResult result = new SearchResult();

        String albumUrl = element.attr("href");
        String albumId = albumUrl.substring(albumUrl.lastIndexOf("/") + 1);

        result.setUrl("http://musicmeter.leonverschuren.nl/album/" + albumId);
        result.setId(albumId);
        result.setName(element.text());

        try
        {
            AlbumCrawler crawler = CrawlerFactory.createAlbumCrawler(Integer.parseInt(albumId));

            result.setArtist(crawler.extractAlbumArtist());
            result.setTitle(crawler.extractAlbumTitle());
            result.setYear(crawler.extractYear());
        }
        catch (Exception e)
        {
            log.log(Level.SEVERE, e.getMessage(), e);
        }

        return result;
    }
}
