package leonverschuren.musicmeterparser.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlbumInfo {
    @JsonProperty("id")
    private String id;

    @JsonProperty("title")
    private String title;
}
