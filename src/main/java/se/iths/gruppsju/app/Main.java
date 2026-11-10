package se.iths.gruppsju.app;

import se.iths.gruppsju.app.model.ComedyMovie;
import se.iths.gruppsju.app.storage.ComedyMovieStorage;
import se.iths.gruppsju.app.storage.MovieStorage;

import java.io.IOException;
import java.nio.file.Path;

public class Main {
    static void main() throws IOException {
        Path comedyMovieStoragePath = Path.of("Data", "comedy-movies.json");
        MovieStorage<ComedyMovie> comedyMovieStorage = new ComedyMovieStorage(comedyMovieStoragePath);

        createComedyMovies(comedyMovieStorage);
    }

    static void createComedyMovies(MovieStorage<ComedyMovie> storage) {
        for (int i = 0; i < 5; i++) {
            try {
                storage.save(new ComedyMovie(i + 1, "ComedyMovie " + (i + 1)));
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }


    }
}
