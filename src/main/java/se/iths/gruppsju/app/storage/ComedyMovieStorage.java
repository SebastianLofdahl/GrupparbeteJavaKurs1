package se.iths.gruppsju.app.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import se.iths.gruppsju.app.model.ComedyMovie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ComedyMovieStorage implements MovieStorage<ComedyMovie> {
    private final ObjectMapper mapper = new ObjectMapper();
    private final Path path;

    public ComedyMovieStorage(Path path) {
        this.path = path;
    }

    @Override
    public void save(ComedyMovie movie) throws IOException {
        if (findById(movie.getId()) != null) {
            return;
        }

        List<ComedyMovie> movies = findAll();
        movies.add(movie);

        mapper.writerWithDefaultPrettyPrinter()
                .writeValue(path.toFile(), movies);
    }

    @Override
    public ComedyMovie findById(int id) throws IOException {
        List<ComedyMovie> movies = findAll();

        for (ComedyMovie movie : movies) {
            if (movie.getId() == id) {
                return movie;
            }
        }

        return null;
    }

    @Override
    public List<ComedyMovie> findAll() throws IOException {
        if (Files.notExists(path)) {
            return new ArrayList<>();
        }

        return mapper.readValue(
                path.toFile(),
                new TypeReference<List<ComedyMovie>>() {
                }
        );
    }
}



