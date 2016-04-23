package leonverschuren.musicmeterparser.resources;

import leonverschuren.musicmeterparser.services.Parser;
import leonverschuren.musicmeterparser.model.Album;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class AlbumResource {
    private final Parser parser;

    public AlbumResource(Parser parser) {
        this.parser = parser;
    }

    @GET
    @Path("/{id}")
    public Album album(@PathParam("id") String id) throws IOException {
        return parser.parse(Integer.parseInt(id));
    }
}
