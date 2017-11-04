package leonverschuren.musicmeterparser;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import leonverschuren.musicmeterparser.resource.AlbumResource;
import leonverschuren.musicmeterparser.resource.SearchResource;

public class MusicMeterParserService extends Application<Configuration> {
    public static void main(String[] args) throws Exception {
        new MusicMeterParserService().run(args);
    }

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {
    }

    @Override
    public void run(Configuration configuration, Environment environment) {
        environment.jersey().register(AlbumResource.class);
        environment.jersey().register(SearchResource.class);
    }
}
