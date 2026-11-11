package se.iths.gruppsju.app.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import se.iths.gruppsju.app.model.DramaMovie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DramaMovieStorage implements MovieStorage<DramaMovie> {

    private final ObjectMapper mapper = new ObjectMapper();
    private final Path path;

    public DramaMovieStorage(Path path) {
        this.path = path;
    }

    @Override
    public void save(DramaMovie movie) throws IOException {
        if (findById(movie.getId()) != null) {
            return;
        }

        List<DramaMovie> movies = findAll();
        movies.add(movie);

        mapper.writerWithDefaultPrettyPrinter()
                .writeValue(path.toFile(), movies);
    }

    @Override
    public DramaMovie findById(int id) throws IOException {
        for (DramaMovie movie : findAll()) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }

    @Override
    public List<DramaMovie> findAll() throws IOException {
        if (Files.notExists(path)) {
            return new ArrayList<>();
        }

        return mapper.readValue(
                path.toFile(),
                new TypeReference<List<DramaMovie>>() {
                }
        );
    }
}