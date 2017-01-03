package leonverschuren.musicmeterparser.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlbumInfo {
    @JsonProperty("Id")
    private String id;
    @JsonProperty("Title")
    private String title;
}
