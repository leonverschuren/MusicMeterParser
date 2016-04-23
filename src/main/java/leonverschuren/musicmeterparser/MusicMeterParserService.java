package leonverschuren.musicmeterparser;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import leonverschuren.musicmeterparser.resources.AlbumResource;
import leonverschuren.musicmeterparser.services.Parser;

public class MusicMeterParserService extends Application<Configuration> {
    public static void main(String[] args) throws Exception {
        new MusicMeterParserService().run(args);
    }

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {
    }

    @Override
    public void run(Configuration configuration, Environment environment) {
        final Parser parser = new Parser();
        final AlbumResource albumResource = new AlbumResource(parser);

        environment.jersey().register(albumResource);
    }
}
