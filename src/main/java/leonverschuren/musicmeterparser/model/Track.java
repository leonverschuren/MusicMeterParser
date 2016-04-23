package leonverschuren.musicmeterparser.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Track {
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Artists")
    private List<String> artists;

    public Track() {
        artists = new ArrayList<String>();
    }
}
