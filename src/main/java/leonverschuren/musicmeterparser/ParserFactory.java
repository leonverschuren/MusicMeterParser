package leonverschuren.musicmeterparser;

import leonverschuren.musicmeterparser.services.AlbumParser;
import leonverschuren.musicmeterparser.services.CompilationParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ParserFactory {
    public static AlbumParser createParser(Document document) {
        Element header = document.getElementById("album_details_wrapper").getElementsByTag("h1").first();

        if (header.getElementsByTag("a").size() != 0) {
            return new AlbumParser();
        } else {
            return new CompilationParser();
        }
    }
}
