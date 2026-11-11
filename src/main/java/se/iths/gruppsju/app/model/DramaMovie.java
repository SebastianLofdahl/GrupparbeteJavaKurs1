package se.iths.gruppsju.app.model;

public class DramaMovie extends Movie {

    public DramaMovie() {
    }

    public DramaMovie(int id, String title) {
        super(id, title);
    }

    @Override
    public String category() {
        return "Drama";
    }
}