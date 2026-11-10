package se.iths.grupp7.app.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import se.iths.gruppsju.app.model.HorrorMovie;
import se.iths.gruppsju.app.storage.HorrorMovieStorage;


import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HorrorMovieStorageTest {

    @TempDir
    Path tempDir;

    HorrorMovieStorage storage;

    @BeforeEach
    void setUp() {
        storage = new HorrorMovieStorage(tempDir.resolve("horror.json"));
    }

    @Test
    void saveAndFindById() throws IOException {

        storage.save(new HorrorMovie(1, "Alien"));
        HorrorMovie found = storage.findById(1);

        assertNotNull(found);
        assertEquals(1, found.getId());
        assertEquals("Alien", found.getTitle());
    }

    @Test
    void duplicateId() throws IOException {

        storage.save(new HorrorMovie(1, "Alien"));
        storage.save(new HorrorMovie(1, "Scream"));
        List<HorrorMovie> movies = storage.findAll();

        assertEquals(1, movies.size());
        assertEquals("Alien", movies.get(0).getTitle());
    }

    @Test
    void findAllWithNoSavedMovies() throws IOException {

        assertTrue(storage.findAll().isEmpty());
    }

    @Test
    void findByIdWithMissingId() throws IOException {

        storage.save(new HorrorMovie(1, "Alien"));

        assertNull(storage.findById(99));
    }
}