package leonverschuren.musicmeterparser.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchResult {
    @JsonProperty("id")
    private String id;

    @JsonProperty("url")
    private String url;

    @JsonProperty("name")
    private String name;

    @JsonProperty("artist")
    private String artist;

    @JsonProperty("title")
    private String title;

    @JsonProperty("year")
    private String year;
}
