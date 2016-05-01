package leonverschuren.musicmeterparser.services;

import leonverschuren.musicmeterparser.ParserFactory;
import leonverschuren.musicmeterparser.model.Album;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class AlbumScraper {
    public Album parse(int id) throws IOException {
        Album album = new Album();

        Document doc = fetchDocument(id);

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

    public Document fetchDocument(int id) throws IOException {
        return Jsoup.connect("http://www.musicmeter.nl/album/" + Integer.toString(id))
                .cookie("cok", "1")
                .get();
    }
}
