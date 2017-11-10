package leonverschuren.musicmeterparser.resource;

import leonverschuren.musicmeterparser.crawler.AlbumCrawler;
import leonverschuren.musicmeterparser.crawler.CrawlerFactory;
import leonverschuren.musicmeterparser.crawler.SearchCrawler;
import leonverschuren.musicmeterparser.crawler.StatsCrawler;
import leonverschuren.musicmeterparser.model.Album;
import leonverschuren.musicmeterparser.model.AlbumInfo;
import leonverschuren.musicmeterparser.model.Cover;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/album/")
@Produces(MediaType.APPLICATION_JSON)
public class AlbumResource {
    @GET
    @Path("/{id}")
    public Album album(@PathParam("id") int id) throws Exception {
        Album album = new Album();

        AlbumCrawler crawler = CrawlerFactory.createAlbumCrawler(id);
        StatsCrawler statsCrawler = CrawlerFactory.createStatsCrawler(id);

        album.setTitle(crawler.extractAlbumTitle());
        album.setCover(crawler.extractCover());
        album.setArtist(crawler.extractAlbumArtist());
        album.setYear(crawler.extractYear());
        album.setReleaseDate(statsCrawler.extractReleaseDate());
        album.setRating(crawler.extractRating());
        album.setLabel(crawler.extractLabel());
        album.setGenre(crawler.extractGenre());
        album.setTracks(crawler.extractTracks());

        return album;
    }

    @GET
    @Path("/search/{term}")
    public List<AlbumInfo> search(@PathParam("term") String term) throws Exception {
        SearchCrawler crawler = CrawlerFactory.createSearchCrawler(term);

        return crawler.extractAlbumInfo();
    }

    @GET
    @Path("/cover/{id}")
    public Cover cover(@PathParam("id") String id) {
        int width = 1280;
        int height = 1280;

        String url = "http://images.osl.wimpmusic.com/im/im?w=" + width + "&h=" + height + "&albumid=" + id;

        return new Cover(url);
    }
}
