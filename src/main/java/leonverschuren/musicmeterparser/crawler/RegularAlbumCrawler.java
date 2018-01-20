package leonverschuren.musicmeterparser.crawler;

import leonverschuren.musicmeterparser.model.Track;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class RegularAlbumCrawler implements AlbumCrawler {
    final Document document;

    RegularAlbumCrawler(Document document) {
        this.document = document;
    }

    @Override
    public String extractAlbumTitle() {
        String title = document.select("#main div.details > h1 > span").first().text();

        return title.substring(title.indexOf(" - ") + 3);
    }

    @Override
    public String extractAlbumArtist() {
        Element element = document.select("#main div.details > h1 > span > a").first();

        return element.text();
    }

    @Override
    public String extractCover() {
        Element element = document.select("#main div.image > img").first();

        String url = element.attr("src");

        int index = url.indexOf("?");
        if (index != -1)
        {
            url = url.substring(0, url.indexOf("?"));
        }

        return url.replace(".300", "");
    }

    @Override
    public String extractYear() {
        Element element = document.select("#main div.details > h1").first();

        return StringUtils.substringBetween(element.text(), "(", ")");
    }

    @Override
    public String extractRating() {
        Element element = document.select("#main div.star-rating.entity-rating > span").first();

        return element.text().replace(',', '.');
    }

    @Override
    public String extractGenre() {
        Element details = document.select(".details-inner > p").first();

        return details.childNode(2).toString().trim();
    }

    @Override
    public String extractLabel() {
        Element element = document.select("#main div.details-inner > p > a").first();

        return element.text().trim();
    }

    @Override
    public List<Track> extractTracks() {
        Elements trackElements = document.select("#main div.tracks > ol > li");

        List<Track> tracks = new ArrayList<>();
        for (Element e : trackElements) {
            Track track = new Track();

            Elements artists = e.select(":root > a[data-tooltip-artist]");
            if (!artists.isEmpty())
            {
                String title = e.ownText();
                track.setTitle(title.substring(title.indexOf("- ") + 2).trim());
            }
            else
            {
                track.setTitle(e.ownText().trim());
            }

            String albumArtist = extractAlbumArtist();

            if (albumArtist != null && !albumArtist.isEmpty())
            {
                track.addArtist(albumArtist);
            }

            Elements guests = e.select(".subtext > a");
            artists.addAll(guests);

            for (Element artist : artists) {
                track.addArtist(artist.text());
            }

            tracks.add(track);
        }

        return tracks;
    }
}
