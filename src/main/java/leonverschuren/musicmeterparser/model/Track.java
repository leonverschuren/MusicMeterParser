package leonverschuren.musicmeterparser.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class Track {
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Artists")
    private List<String> artists;

    public Track() {
        artists = new ArrayList<>();
    }

    public List<String> getArtists() {
        return Collections.unmodifiableList(artists);
    }

    public void addArtist(String artist) {
        artists.add(artist);
    }
}
