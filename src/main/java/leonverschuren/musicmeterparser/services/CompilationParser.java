package leonverschuren.musicmeterparser.services;

import leonverschuren.musicmeterparser.model.Track;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class CompilationParser extends AlbumParser {
    @Override
    String extractYear(Document document) {
        Element header = document.getElementsByTag("h1").first();

        String year = header.childNode(1).toString();

        return StringUtils.substringBetween(year, "(", ")");
    }

    @Override
    String extractAlbumArtist(Document document) {
        return null;
    }

    @Override
    List<Track> extractTracks(Document document) {
        Elements trackElements = document.getElementById("album_details_wrapper")
                .getElementsByTag("li");

        List<Track> tracks = new ArrayList<>();
        for (Element e : trackElements) {
            Track track = new Track();

            Elements artists = e.getElementsByClass("tooltip");
            for (Element artist : artists) {
                track.addArtist(artist.text());
            }

            for (Node n : e.childNodes()) {
                if (n.toString().startsWith(" - ")) {
                    track.setTitle(n.toString().trim().substring(2));
                    break;
                }
            }

            tracks.add(track);
        }

        return tracks;
    }
}
