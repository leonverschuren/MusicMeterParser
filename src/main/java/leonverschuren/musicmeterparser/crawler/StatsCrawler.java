package leonverschuren.musicmeterparser.crawler;

import lombok.extern.java.Log;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.text.ParsePosition;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.logging.Level;

@Log
public class StatsCrawler {
    private final Document document;

    StatsCrawler(Document document) {
        this.document = document;
    }

    public String extractReleaseDate() {
        String result = null;

        try {
            Element element = document.select("#main > div > p:nth-child(6)").first();

            DateTimeFormatter f = DateTimeFormatter.ofPattern("d MMMM yyyy", new Locale("nl"));
            LocalDate date = LocalDate.from(f.parse(element.text(), new ParsePosition(14)));

            result = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage(), e);
        }

        return result;
    }
}
