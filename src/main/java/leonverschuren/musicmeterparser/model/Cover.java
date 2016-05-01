package leonverschuren.musicmeterparser.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Cover {
    @JsonProperty("Url")
    private String url;
}
