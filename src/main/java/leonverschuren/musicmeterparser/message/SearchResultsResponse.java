package leonverschuren.musicmeterparser.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import leonverschuren.musicmeterparser.model.SearchResult;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class SearchResultsResponse {
    @JsonProperty("results")
    List<SearchResult> results;
}
