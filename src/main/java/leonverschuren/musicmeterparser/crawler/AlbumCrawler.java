package leonverschuren.musicmeterparser.crawler;

import leonverschuren.musicmeterparser.model.Track;

import java.util.List;

public interface AlbumCrawler {
    String extractAlbumTitle();

    String extractAlbumArtist();

    String extractCover();

    String extractYear();

    String extractRating();

    String extractGenre();

    String extractLabel();

    List<Track> extractTracks();
}
