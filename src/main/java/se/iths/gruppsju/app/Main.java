package se.iths.gruppsju.app;

import se.iths.gruppsju.app.model.ComedyMovie;
import se.iths.gruppsju.app.storage.ComedyMovieStorage;
import se.iths.gruppsju.app.storage.MovieStorage;

import java.io.IOException;
import java.nio.file.Path;

public class Main {
    static void main() throws IOException {
        createComedyMovies();
    }

    static void createComedyMovies() {
        Path path = Path.of("Data", "comedy-movies.json");

        MovieStorage<ComedyMovie> storage = new ComedyMovieStorage(path);

        for (int i = 0; i < 5; i++) {
            try {
                storage.save(new ComedyMovie(i + 1, "ComedyMovie " + (i + 1)));
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }


    }
}
