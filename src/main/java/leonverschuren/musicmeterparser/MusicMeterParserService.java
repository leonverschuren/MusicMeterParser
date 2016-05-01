package leonverschuren.musicmeterparser;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import leonverschuren.musicmeterparser.resources.AlbumResource;
import leonverschuren.musicmeterparser.services.AlbumScraper;

public class MusicMeterParserService extends Application<Configuration> {
    public static void main(String[] args) throws Exception {
        new MusicMeterParserService().run(args);
    }

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {
    }

    @Override
    public void run(Configuration configuration, Environment environment) {
        final AlbumResource albumResource = new AlbumResource(new AlbumScraper());

        environment.jersey().register(albumResource);
    }
}
