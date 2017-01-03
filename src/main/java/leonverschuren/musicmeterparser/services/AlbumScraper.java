package leonverschuren.musicmeterparser.services;

import leonverschuren.musicmeterparser.ParserFactory;
import leonverschuren.musicmeterparser.model.Album;
import leonverschuren.musicmeterparser.model.AlbumInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

public class AlbumScraper {
    public Album parse(int id) throws IOException {
        Album album = new Album();

        Document doc = fetchAlbumDocument(id);

        AlbumParser parser = ParserFactory.createParser(doc);

        album.setTitle(parser.extractAlbumTitle(doc));
        album.setArtist(parser.extractAlbumArtist(doc));
        album.setYear(parser.extractYear(doc));
        album.setRating(parser.extractRating(doc));
        album.setGenre(parser.extractGenre(doc));
        album.setLabel(parser.extractLabel(doc));
        album.setTracks(parser.extractTracks(doc));

        return album;
    }

    public List<AlbumInfo> search(String term) throws IOException {
        Document doc = fetchSearchDocument(term);

        SearchParser parser = new SearchParser();

        return parser.extractSearchResults(doc);
    }

    Document fetchAlbumDocument(int id) throws IOException {
        return Jsoup.connect("https://www.musicmeter.nl/album/" + Integer.toString(id))
                .cookie("cok", "1")
                .get();
    }

    Document fetchSearchDocument(String term) throws IOException {
        return Jsoup.connect("https://www.musicmeter.nl/site/search/" + URLEncoder.encode(term, "UTF-8"))
                .cookie("cok", "1")
                .get();
    }
}
