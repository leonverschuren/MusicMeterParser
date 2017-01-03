package leonverschuren.musicmeterparser.resources;

import leonverschuren.musicmeterparser.model.Album;
import leonverschuren.musicmeterparser.model.AlbumInfo;
import leonverschuren.musicmeterparser.model.Cover;
import leonverschuren.musicmeterparser.services.AlbumScraper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

@Path("/album/")
@Produces(MediaType.APPLICATION_JSON)
public class AlbumResource {
    private final AlbumScraper scraper;

    public AlbumResource(AlbumScraper scraper) {
        this.scraper = scraper;
    }

    @GET
    @Path("/{id}")
    public Album album(@PathParam("id") String id) throws IOException {
        return scraper.parse(Integer.parseInt(id));
    }

    @GET
    @Path("/search/{term}")
    public List<AlbumInfo> search(@PathParam("term") String term) throws IOException {
        return scraper.search(term);
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
