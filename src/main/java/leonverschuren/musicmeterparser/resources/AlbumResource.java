package leonverschuren.musicmeterparser.resources;

import leonverschuren.musicmeterparser.model.Album;
import leonverschuren.musicmeterparser.services.AlbumParser;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("/album/")
@Produces(MediaType.APPLICATION_JSON)
public class AlbumResource {
    private final AlbumParser parser;

    public AlbumResource(AlbumParser albumParser) {
        this.parser = albumParser;
    }

    @GET
    @Path("/{id}")
    public Album album(@PathParam("id") String id) throws IOException {
        return parser.parse(Integer.parseInt(id));
    }
}
