package leonverschuren.musicmeterparser.services;

import leonverschuren.musicmeterparser.model.Track;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class AlbumParser {
    String extractAlbumTitle(Document document) {
        Element header = document.getElementById("album_details_wrapper")
                .getElementsByTag("h1")
                .first();

        Element title = header.getElementsByAttributeValue("itemprop", "name")
                .first();

        String elementText = title.text();

        return elementText.substring(elementText.indexOf(" - ") + 3);
    }

    String extractAlbumArtist(Document document) {
        Element header = document.getElementById("album_details_wrapper")
                .getElementsByTag("h1")
                .first();

        String artist = null;
        Elements links = header.getElementsByTag("a");
        if (!links.isEmpty()) {
            artist = links.first().text().trim();
        }

        return artist;
    }

    String extractYear(Document document) {
        Element header = document.getElementsByTag("h1").first();

        String year = header.childNode(1).toString();

        return StringUtils.substringBetween(year, "(", ")");
    }

    String extractRating(Document document) {
        Element ratingElement = document.getElementById("album_details_wrapper")
                .getElementsByClass("album_average").first()
                .getElementsByTag("span").first();

        String rating = ratingElement.text();

        return rating.replace(',', '.');
    }

    String extractGenre(Document document) {
        Elements details = document.getElementById("album_details_wrapper")
                .getElementsByClass("album_details");

        return details.first().childNode(2).toString().trim();
    }

    String extractLabel(Document document) {
        Elements details = document.getElementById("album_details_wrapper")
                .getElementsByClass("album_details");

        return details.first().getElementsByTag("a").first().text().trim();
    }

    List<Track> extractTracks(Document document) {
        Elements trackElements = document.getElementById("album_details_wrapper")
                .getElementsByTag("li");

        String albumArtist = extractAlbumArtist(document);

        List<Track> tracks = new ArrayList<>();
        for (Element e : trackElements) {
            Track track = new Track();

            track.addArtist(albumArtist);
            track.setTitle(e.ownText().trim());

            Element span = e.getElementsByClass("subtext").first();
            if (span != null) {
                Elements guests = span.getElementsByTag("a");
                for (Element g : guests) {
                    track.addArtist(g.text());
                }
            }

            tracks.add(track);
        }

        return tracks;
    }
}
