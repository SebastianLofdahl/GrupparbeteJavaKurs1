package se.iths.grupp7.app.storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import se.iths.gruppsju.app.model.ComedyMovie;
import se.iths.gruppsju.app.storage.ComedyMovieStorage;
import se.iths.gruppsju.app.storage.MovieStorage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class ComedyMovieStorageTest {
    MovieStorage<ComedyMovie> storage;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        Path file = tempDir.resolve("comedy-movies-test.json");
        storage = new ComedyMovieStorage(file);
    }


    @Test
    void saveMovieAndGetItById() throws IOException {
        ComedyMovie movie = new ComedyMovie(1, "movie");

        storage.save(movie);
        ComedyMovie movieFromStorage = storage.findById(1);

        Assertions.assertEquals(1, movieFromStorage.getId());
        Assertions.assertEquals("movie", movieFromStorage.getTitle());
    }

    @Test
    void checkThatDuplicatesDontGetSaved() throws IOException {
        ComedyMovie movie1 = new ComedyMovie(1, "movie1");
        ComedyMovie movie2 = new ComedyMovie(1, "movie2");

        storage.save(movie1);
        storage.save(movie2);

        List<ComedyMovie> movies = storage.findAll();

        Assertions.assertEquals(1, movies.size());
        Assertions.assertEquals("movie1", movies.getFirst().getTitle());
    }

    @Test
    void findAllWithoutMovieSaved() throws IOException {
        List<ComedyMovie> movies = storage.findAll();

        Assertions.assertTrue(movies.isEmpty());
    }

    @Test
    void findByIdOnNonExistingId() throws IOException {
        ComedyMovie movie = new ComedyMovie(1, "movie");
        storage.save(movie);

        ComedyMovie movieFromStorage = storage.findById(2);

        Assertions.assertNull(movieFromStorage);
    }


}
