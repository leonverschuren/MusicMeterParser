package leonverschuren.musicmeterparser.crawler;

import leonverschuren.musicmeterparser.model.Track;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class AlbumCrawler {
    final Document document;

    AlbumCrawler(Document document) {
        this.document = document;
    }

    public String extractAlbumTitle() {
        String title = document.select("#content div.details > h1 > span").first().text();

        return title.substring(title.indexOf(" - ") + 3);
    }

    public String extractAlbumArtist() {
        Element element = document.select("#content div.details > h1 > span > a").first();

        return element.text();
    }

    public String extractCover() {
        Element element = document.select("#content div.image > img").first();

        String url = element.attr("src");

        return url.replace(".300", "");
    }

    public String extractYear() {
        Element element = document.select("#content div.details > h1").first();

        return StringUtils.substringBetween(element.text(), "(", ")");
    }

    public String extractRating() {
        Element element = document.select("#content div.star-rating.entity-rating > span").first();

        return element.text().replace(',', '.');
    }

    public String extractGenre() {
        Element details = document.select(".details-inner > p").first();

        return details.childNode(2).toString().trim();
    }

    public String extractLabel() {
        Element element = document.select("#content div.details-inner > p > a").first();

        return element.text().trim();
    }

    public List<Track> extractTracks() {
        Elements trackElements = document.select("#content div.tracks > ol > li");

        String albumArtist = extractAlbumArtist();

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
