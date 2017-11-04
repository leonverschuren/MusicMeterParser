package leonverschuren.musicmeterparser.crawler;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.text.ParsePosition;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class StatsCrawler {
    private final Document document;

    public StatsCrawler(Document document) {
        this.document = document;
    }

    public String extractReleaseDate() {
        Element element = document.select("#content > div:nth-child(2) > p:nth-child(7)").first();

        DateTimeFormatter f = DateTimeFormatter.ofPattern("d MMMM yyyy", new Locale("nl"));
        LocalDate date = LocalDate.from(f.parse(element.text(), new ParsePosition(14)));

        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
