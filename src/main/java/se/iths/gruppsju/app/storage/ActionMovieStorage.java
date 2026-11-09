package se.iths.gruppsju.app.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import se.iths.gruppsju.app.model.ActionMovie;
import se.iths.gruppsju.app.model.Movie;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ActionMovieStorage implements MovieStorage<ActionMovie> {

    ObjectMapper mapper = new ObjectMapper();
    Path path;

    public ActionMovieStorage(Path path) {
        this.path = path;
    }

    @Override
    public void save(ActionMovie movie) throws IOException {
        Movie searchedMovie = findById(movie.getId());

        if (searchedMovie == null) {
            List<ActionMovie> movies = findAll();
            movies.add(movie);

            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(path.toFile(), movies);
        }
    }

    @Override
    public ActionMovie findById(int id) throws IOException {
        List<ActionMovie> movies = findAll();
        for (ActionMovie movie : movies) {
            if (movie.getId() == id) {
                return movie;
            }
        }

        return null;
    }

    @Override
    public List<ActionMovie> findAll() throws IOException {
        List<ActionMovie> loadedMovies = mapper.readValue(
                path.toFile(),
                new TypeReference<List<ActionMovie>>() {}
        );

        if (loadedMovies == null) {
            return new ArrayList<>();
        }

        return loadedMovies;
    }
}
