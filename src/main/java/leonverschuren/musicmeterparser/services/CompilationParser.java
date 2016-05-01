package leonverschuren.musicmeterparser.services;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class CompilationParser extends AlbumParser {
    @Override
    String extractYear(Document document) {
        Element header = document.getElementsByTag("h1").first();

        String year = header.childNode(1).toString();

        return StringUtils.substringBetween(year, "(", ")");
    }

    @Override
    String extractAlbumArtist(Document document) {
        return null;
    }
}
