package se.iths.grupp7.app.storage;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import se.iths.gruppsju.app.model.ActionMovie;
import se.iths.gruppsju.app.model.Movie;
import se.iths.gruppsju.app.storage.ActionMovieStorage;
import se.iths.gruppsju.app.storage.MovieStorage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class ActionMovieStorageTest {
    @TempDir
    Path tempDir;

    MovieStorage<ActionMovie> storage;

    @BeforeEach
    void setup() {
        Path file = tempDir.resolve("action-movies-test.json");
        storage = new ActionMovieStorage(file);
    }


    @Test
    @DisplayName("Save movie and get movie by id test")
    void saveAndFindTest() throws IOException {
        ActionMovie movie = new ActionMovie(1, "Top Gun");
        storage.save(movie);
        ActionMovie savedMovie = storage.findById(1);

        Assertions.assertEquals(1, savedMovie.getId());
        Assertions.assertEquals("Top Gun", savedMovie.getTitle());
    }

    @Test
    @DisplayName("Check for duplicates")
    void checkForDuplicates() throws IOException {
        ActionMovie movieOne = new ActionMovie(1, "Top Gun");
        ActionMovie movieTwo = new ActionMovie(1, "Spiderman");
        storage.save(movieOne);
        storage.save(movieTwo);

        List<ActionMovie> savedMovies = storage.findAll();

        Assertions.assertEquals("Top Gun", savedMovies.getFirst().getTitle());
        Assertions.assertEquals(1, savedMovies.size());
    }

    @Test
    @DisplayName("No movie saved yet")
    void checkForNoSavedMovies() throws IOException {
        List<ActionMovie> movies = storage.findAll();
        Assertions.assertEquals(0, movies.size());
    }

    @Test
    @DisplayName("Save movie and check for id that doesn't exist.")
    void checkForNonExistingID() throws IOException {
        ActionMovie movie = new ActionMovie(1, "Die Hard");
        storage.save(movie);

        Assertions.assertNull(storage.findById(2));
    }
}
