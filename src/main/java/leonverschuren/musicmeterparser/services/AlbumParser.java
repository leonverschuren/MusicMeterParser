package leonverschuren.musicmeterparser.services;

import leonverschuren.musicmeterparser.model.Album;
import leonverschuren.musicmeterparser.model.Track;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AlbumParser {
    public Album parse(int id) throws IOException {
        Album album = new Album();

        Document doc = fetchDocument(id);

        album.setTitle(extractAlbumTitle(doc));
        album.setArtist(extractAlbumArtist(doc));
        album.setYear(extractYear(doc));
        album.setRating(extractRating(doc));
        album.setGenre(extractGenre(doc));
        album.setLabel(extractLabel(doc));
        album.setTracks(extractTracks(doc));

        return album;
    }

    Document fetchDocument(int id) throws IOException {
        return Jsoup.connect("http://www.musicmeter.nl/album/" + Integer.toString(id))
                .cookie("cok", "1")
                .get();
    }

    String extractAlbumTitle(Document document) {
        Element detailsWrapper = document.getElementById("album_details_wrapper");
        Element header = detailsWrapper.getElementsByTag("h1").first();

        Element title = header.getElementsByAttributeValue("itemprop", "name").first();

        return title.text().trim();
    }

    String extractAlbumArtist(Document document) {
        Element detailsWrapper = document.getElementById("album_details_wrapper");
        Element header = detailsWrapper.getElementsByTag("h1").first();

        Element artist = header.getElementsByTag("a").first();

        return artist.text().trim();
    }

    String extractYear(Document document) {
        Element header = document.getElementsByTag("h1").first();

        String year = header.childNode(3).toString();

        return StringUtils.substringBetween(year, "(", ")");
    }

    String extractRating(Document document) {
        Element detailsWrapper = document.getElementById("album_details_wrapper");
        Element ratingElement = detailsWrapper.getElementsByClass("album_average").first().getElementsByTag("span").first();

        String rating = ratingElement.text();

        return rating.replace(',', '.');
    }

    String extractGenre(Document document) {
        Element detailsWrapper = document.getElementById("album_details_wrapper");
        Elements details = detailsWrapper.getElementsByClass("album_details");

        return details.first().childNode(2).toString().trim();
    }

    String extractLabel(Document document) {
        Element detailsWrapper = document.getElementById("album_details_wrapper");
        Elements details = detailsWrapper.getElementsByClass("album_details");

        return details.first().getElementsByTag("a").first().text().trim();
    }

    List<Track> extractTracks(Document document) {
        Element detailsWrapper = document.getElementById("album_details_wrapper");
        Elements trackElements = detailsWrapper.getElementsByTag("li");

        String albumArtist = extractAlbumArtist(document);

        List<Track> tracks = new ArrayList<>();
        for (Element e : trackElements) {
            Track track = new Track();
            track.setTitle(e.childNode(1).toString().trim());

            track.getArtists().add(albumArtist);
            Element span = e.getElementsByTag("span").last();
            Elements guests = span.getElementsByTag("a");
            for (Element g : guests) {
                track.getArtists().add(g.text());
            }

            tracks.add(track);
        }

        return tracks;
    }
}
