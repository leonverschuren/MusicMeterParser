package leonverschuren.musicmeterparser.crawler;

import leonverschuren.musicmeterparser.model.Track;
import lombok.extern.java.Log;

import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Level;

@Log
public class ExceptionHandlingAlbumCrawler implements AlbumCrawler {
    private final AlbumCrawler innerCrawler;

    ExceptionHandlingAlbumCrawler(AlbumCrawler innerCrawler) {
        this.innerCrawler = innerCrawler;
    }

    @Override
    public String extractAlbumTitle() {
        return HandleException(innerCrawler::extractAlbumTitle);
    }

    @Override
    public String extractAlbumArtist() {
        return HandleException(innerCrawler::extractAlbumArtist);
    }

    @Override
    public String extractCover() {
        return HandleException(innerCrawler::extractCover);
    }

    @Override
    public String extractYear() {
        return HandleException(innerCrawler::extractYear);
    }

    @Override
    public String extractRating() {
        return HandleException(innerCrawler::extractRating);
    }

    @Override
    public String extractGenre() {
        return HandleException(innerCrawler::extractGenre);
    }

    @Override
    public String extractLabel() {
        return HandleException(innerCrawler::extractLabel);
    }

    @Override
    public List<Track> extractTracks() {
        return HandleException(innerCrawler::extractTracks);
    }


    private <T> T HandleException(Supplier<T> supplier)
    {
        T result = null;

        try
        {
            result = supplier.get();
        }
        catch (Exception e)
        {
            log.log(Level.SEVERE, e.getMessage(), e);
        }

        return result;
    }
}
