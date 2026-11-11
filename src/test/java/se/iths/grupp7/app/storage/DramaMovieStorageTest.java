package se.iths.grupp7.app.storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import se.iths.gruppsju.app.model.DramaMovie;
import se.iths.gruppsju.app.storage.DramaMovieStorage;
import se.iths.gruppsju.app.storage.MovieStorage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class DramaMovieStorageTest {

    @TempDir
    Path tempDir;

    MovieStorage<DramaMovie> storage;

    @BeforeEach
    void setUp() {
        storage = new DramaMovieStorage(tempDir.resolve("drama-movies-test.json"));
    }

    @Test
    @DisplayName("Save a movie and find it by id")
    void saveAndFindById() throws IOException {
        storage.save(new DramaMovie(1, "The Godfather"));

        DramaMovie found = storage.findById(1);

        Assertions.assertNotNull(found);
        Assertions.assertEquals(1, found.getId());
        Assertions.assertEquals("The Godfather", found.getTitle());
        Assertions.assertEquals("Drama", found.category());
    }

    @Test
    @DisplayName("Duplicate id is not saved twice")
    void duplicateIdIsNotSaved() throws IOException {
        storage.save(new DramaMovie(1, "The Godfather"));
        storage.save(new DramaMovie(1, "Forrest Gump"));

        List<DramaMovie> movies = storage.findAll();

        Assertions.assertEquals(1, movies.size());
        Assertions.assertEquals("The Godfather", movies.getFirst().getTitle());
    }

    @Test
    @DisplayName("findAll returns empty list when nothing is saved")
    void findAllWithNoSavedMovies() throws IOException {
        Assertions.assertTrue(storage.findAll().isEmpty());
    }

    @Test
    @DisplayName("findById returns null for a missing id")
    void findByIdWithMissingId() throws IOException {
        storage.save(new DramaMovie(1, "The Godfather"));

        Assertions.assertNull(storage.findById(99));
    }
}