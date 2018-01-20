package leonverschuren.musicmeterparser.crawler;

import lombok.extern.log4j.Log4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.text.ParsePosition;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Log4j
public class StatsCrawler {
    private final Document document;

    StatsCrawler(Document document) {
        this.document = document;
    }

    public String extractReleaseDate() {
        String result = null;

        try {
            Element element = document.select("#main > div:nth-child(2) > p:nth-child(7)").first();

            DateTimeFormatter f = DateTimeFormatter.ofPattern("d MMMM yyyy", new Locale("nl"));
            LocalDate date = LocalDate.from(f.parse(element.text(), new ParsePosition(14)));

            result = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            log.warn(e);
        }

        return result;
    }
}
