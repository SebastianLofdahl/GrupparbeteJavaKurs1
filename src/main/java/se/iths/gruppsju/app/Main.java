package se.iths.gruppsju.app;

import se.iths.gruppsju.app.model.ActionMovie;
import se.iths.gruppsju.app.model.ComedyMovie;
import se.iths.gruppsju.app.model.HorrorMovie;
import se.iths.gruppsju.app.storage.ActionMovieStorage;
import se.iths.gruppsju.app.storage.ComedyMovieStorage;
import se.iths.gruppsju.app.storage.HorrorMovieStorage;
import se.iths.gruppsju.app.storage.MovieStorage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Main {
    static void main() throws IOException {
        MovieStorage<ActionMovie> actionMovieStorage = new ActionMovieStorage(Path.of("Data", "action-movies.json"));

        List<ActionMovie> actionMovies = List.of(
                new ActionMovie(1, "Top Gun"),
                new ActionMovie(2, "Spiderman"),
                new ActionMovie(3, "Batman"),
                new ActionMovie(4, "John Wick"),
                new ActionMovie(5, "Die Hard")
        );

        for (ActionMovie movie : actionMovies) {
            actionMovieStorage.save(movie);
        }
        Path comedyMovieStoragePath = Path.of("Data", "comedy-movies.json");
        MovieStorage<ComedyMovie> comedyMovieStorage = new ComedyMovieStorage(comedyMovieStoragePath);

        createComedyMovies(comedyMovieStorage);
        saveHorrorMovies();
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

    static void saveHorrorMovies() throws IOException {
        MovieStorage<HorrorMovie> horrorStorage =
                new HorrorMovieStorage(Path.of("data", "horror-movies.json"));

        horrorStorage.save(new HorrorMovie(1, "Alien"));
        horrorStorage.save(new HorrorMovie(2, "The Shining"));
        horrorStorage.save(new HorrorMovie(3, "Scream"));
        horrorStorage.save(new HorrorMovie(4, "Halloween"));
        horrorStorage.save(new HorrorMovie(5, "The Exorcist"));
    }
}
