package se.iths.gruppsju.app;

import se.iths.gruppsju.app.model.ActionMovie;
import se.iths.gruppsju.app.model.ComedyMovie;
import se.iths.gruppsju.app.model.HorrorMovie;
import se.iths.gruppsju.app.model.Movie;
import se.iths.gruppsju.app.storage.ActionMovieStorage;
import se.iths.gruppsju.app.storage.ComedyMovieStorage;
import se.iths.gruppsju.app.storage.HorrorMovieStorage;
import se.iths.gruppsju.app.storage.MovieStorage;
import se.iths.gruppsju.app.model.DramaMovie;
import se.iths.gruppsju.app.storage.DramaMovieStorage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Main {
    static MovieStorage<ActionMovie> actionMovieStorage =
            new ActionMovieStorage(Path.of("Data", "action-movies.json"));
    static MovieStorage<HorrorMovie> horrorStorage =
            new HorrorMovieStorage(Path.of("data", "horror-movies.json"));
    static MovieStorage<DramaMovie> dramaStorage =
            new DramaMovieStorage(Path.of("Data", "drama-movies.json"));

    static void main() throws IOException {
        Path comedyMovieStoragePath = Path.of("Data", "comedy-movies.json");
        MovieStorage<ComedyMovie> comedyMovieStorage = new ComedyMovieStorage(comedyMovieStoragePath);
        saveActionMovie();
        saveHorrorMovies();
        saveDramaMovies();
        createComedyMovies(comedyMovieStorage);

        boolean inMenu = true;

        while (inMenu) {
            String input = IO.readln("1. Show all movies in a category." +
                    "\n2. Show all movies from each catergory." +
                    "\n0. Close the program.\n:");

            switch (input) {
                case "1" -> {
                    String catergoryChoice = IO.readln("""
                            Which category would your like to choose?
                            1. Action movies.
                            2. Comendy movies.
                            3. Drama movies.
                            4. Horror movies.
                            : """);
                    switch (catergoryChoice) {
                        case "1" -> {
                            for (ActionMovie movie : actionMovieStorage.findAll()) {
                                IO.println(movie.getTitle());
                            }
                        }
                        case "2" -> {
                            for (ComedyMovie movie : comedyMovieStorage.findAll()) {
                                IO.println(movie.getTitle());
                            }
                        }
                        case "3" -> {
                            for (DramaMovie movie : dramaStorage.findAll()) {
                                IO.println(movie.getTitle());
                            }
                        }
                        case "4" -> {
                            for (HorrorMovie movie : horrorStorage.findAll()) {
                                IO.println(movie.getTitle());
                            }
                        }
                        default -> IO.println("You need to choose a category between 1-4.");
                    }
                }
                case "2" -> {
                    IO.println("All movies by category");
                    printMoviesByCategory("Action", actionMovieStorage.findAll());
                    IO.println("\n********");
                    printMoviesByCategory("Comedy", comedyMovieStorage.findAll());
                    IO.println("\n********");
                    printMoviesByCategory("Drama", dramaStorage.findAll());
                    IO.println("\n********");
                    printMoviesByCategory("Horror", horrorStorage.findAll());
                    IO.println("\n********");
                }
                case "0" -> inMenu = false;

                default -> IO.println("You need to choose between 1, 2 & 0");
            }
        }
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

        horrorStorage.save(new HorrorMovie(1, "Alien"));
        horrorStorage.save(new HorrorMovie(2, "The Shining"));
        horrorStorage.save(new HorrorMovie(3, "Scream"));
        horrorStorage.save(new HorrorMovie(4, "Halloween"));
        horrorStorage.save(new HorrorMovie(5, "The Exorcist"));
    }

    static void saveActionMovie() throws IOException {
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
    static void saveDramaMovies() throws IOException {
        dramaStorage.save(new DramaMovie(1, "The Godfather"));
        dramaStorage.save(new DramaMovie(2, "Forrest Gump"));
        dramaStorage.save(new DramaMovie(3, "Schindler's List"));
        dramaStorage.save(new DramaMovie(4, "The Shawshank Redemption"));
        dramaStorage.save(new DramaMovie(5, "Fight Club"));
    }

    static void printMoviesByCategory(String category, List<? extends Movie> movies) {
        IO.println("\n" + category + ":");
        for (Movie movie : movies) {
            IO.println(movie.getId()
                    + " "
                    + movie.getTitle());
        }
    }

}
