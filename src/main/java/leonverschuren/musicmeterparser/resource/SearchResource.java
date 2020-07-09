package leonverschuren.musicmeterparser.resource;

import leonverschuren.musicmeterparser.crawler.AlbumCrawler;
import leonverschuren.musicmeterparser.crawler.CrawlerFactory;
import leonverschuren.musicmeterparser.crawler.SearchCrawler;
import leonverschuren.musicmeterparser.model.SearchResult;
import lombok.extern.java.Log;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Level;

@Log
@Path("/v2/search/")
@Produces(MediaType.APPLICATION_JSON)
public class SearchResource {
    @GET
    @Path("/{term}")
    public List<SearchResult> search(@PathParam("term") String term) throws Exception {
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
            log.log(Level.SEVERE, e.getMessage(), e);
        }

        return results;
    }
}
