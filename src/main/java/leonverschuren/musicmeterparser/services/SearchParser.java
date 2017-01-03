package leonverschuren.musicmeterparser.services;

import leonverschuren.musicmeterparser.model.AlbumInfo;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class SearchParser {
    public List<AlbumInfo> extractSearchResults(Document document) {
        List<AlbumInfo> results = new ArrayList<>();

        Elements albums = document.getElementsByClass("album_row");

        for (Element e : albums) {
            if (!e.getElementsByClass("tooltip").isEmpty()) {
                AlbumInfo info = new AlbumInfo();

                Element anchor = e.getElementsByClass("tooltip").first();

                String albumUrl = anchor.attr("href");
                String albumId = albumUrl.substring(albumUrl.lastIndexOf('/') + 1);

                info.setId(albumId);
                info.setTitle(anchor.text());

                results.add(info);
            }
        }

        return results;
    }
}
