package leonverschuren.musicmeterparser.crawler;

import leonverschuren.musicmeterparser.model.Track;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class CompilationCrawler extends AlbumCrawler {
    CompilationCrawler(Document document) {
        super(document);
    }

    @Override
    public String extractAlbumTitle() {
        return document.select("#content div.details > h1 > span").text();
    }

    @Override
    public String extractAlbumArtist() {
        return "";
    }

    @Override
    public List<Track> extractTracks() {
        Elements trackElements = document.select("#content div.tracks > ol > li");

        List<Track> tracks = new ArrayList<>();
        for (Element e : trackElements) {
            Track track = new Track();

            track.setTitle(e.ownText().substring(2).trim());

            Elements artists = e.getElementsByAttribute("data-tooltip-artist");
            for (Element artist : artists) {
                track.addArtist(artist.text());
            }

            tracks.add(track);
        }

        return tracks;
    }
}
