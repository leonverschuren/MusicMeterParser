package leonverschuren.musicmeterparser.resource;

import leonverschuren.musicmeterparser.crawler.AlbumCrawler;
import leonverschuren.musicmeterparser.crawler.CrawlerFactory;
import leonverschuren.musicmeterparser.crawler.SearchCrawler;
import leonverschuren.musicmeterparser.message.SearchResultsResponse;
import leonverschuren.musicmeterparser.model.SearchResult;
import lombok.extern.log4j.Log4j;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Log4j
@Path("/v2/search/")
@Produces(MediaType.APPLICATION_JSON)
public class SearchResource {
    @GET
    @Path("/{term}")
    public SearchResultsResponse search(@PathParam("term") String term) throws Exception {
        SearchCrawler searchCrawler = CrawlerFactory.createSearchCrawler(term);
        List<SearchResult> results = searchCrawler.extractSearchResults();

        try {
            int albumId = Integer.parseInt(term);

            AlbumCrawler albumCrawler = CrawlerFactory.createAlbumCrawler(albumId);

            SearchResult result = new SearchResult();

            result.setUrl("http://musicmeter.leonverschuren.nl/album/" + albumId);
            result.setId(String.valueOf(albumId));
            result.setArtist(albumCrawler.extractAlbumArtist());
            result.setTitle(albumCrawler.extractAlbumTitle());
            result.setYear(albumCrawler.extractYear());
            result.setName(String.format("%s - %s (%s)", result.getArtist(), result.getTitle(), result.getYear()));

            results.add(0, result);
        } catch (Exception e) {
            log.info(e);
        }

        return new SearchResultsResponse(results);
    }
}
