package se.iths.gruppsju.app.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import se.iths.gruppsju.app.model.HorrorMovie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class HorrorMovieStorage implements MovieStorage<HorrorMovie> {

    private final Path filePath;
    private final ObjectMapper mapper = new ObjectMapper();

    public HorrorMovieStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<HorrorMovie> findAll() throws IOException {
        if (!Files.exists(filePath)) {
            return new ArrayList<>();
        }
        return mapper.readValue(
                filePath.toFile(),
                new TypeReference<List<HorrorMovie>>() {
                }
        );
    }

    @Override
    public HorrorMovie findById(int id) throws IOException {
        for (HorrorMovie movie : findAll()) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }

    @Override
    public void save(HorrorMovie movie) throws IOException {
        if (findById(movie.getId()) != null) {
            return;
        }
        List<HorrorMovie> movies = findAll();
        movies.add(movie);
        mapper.writerWithDefaultPrettyPrinter().writeValue(filePath.toFile(), movies);
    }
}
