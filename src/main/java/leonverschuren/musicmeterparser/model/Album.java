package leonverschuren.musicmeterparser.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Album {
    @JsonProperty("Artist")
    private String artist;
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Year")
    private String year;
    @JsonProperty("Genre")
    private String genre;
    @JsonProperty("Label")
    private String label;
    @JsonProperty("Rating")
    private String rating;
    @JsonProperty("Tracks")
    private List<Track> tracks;

    public Album() {
        tracks = new ArrayList<Track>();
    }
}
