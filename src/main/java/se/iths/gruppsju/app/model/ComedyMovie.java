package se.iths.gruppsju.app.model;

public class ComedyMovie extends Movie {
    public ComedyMovie() {
    }

    public ComedyMovie(int id, String title) {
        super(id, title);
    }

    @Override
    public String category() {
        return "Comedy";
    }
}
