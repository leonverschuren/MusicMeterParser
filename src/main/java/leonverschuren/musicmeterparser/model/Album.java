package leonverschuren.musicmeterparser.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Album {
    @JsonProperty("title")
    private String title;

    @JsonProperty("cover")
    private String cover;

    @JsonProperty("artist")
    private String artist;

    @JsonProperty("year")
    private String year;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("rating")
    private String rating;

    @JsonProperty("label")
    private String label;

    @JsonProperty("genre")
    private String genre;

    @JsonProperty("tracks")
    private List<Track> tracks;
}
