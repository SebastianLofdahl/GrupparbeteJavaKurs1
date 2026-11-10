package se.iths.gruppsju.app;

import se.iths.gruppsju.app.model.ActionMovie;
import se.iths.gruppsju.app.model.Movie;
import se.iths.gruppsju.app.storage.ActionMovieStorage;
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

    }
}
