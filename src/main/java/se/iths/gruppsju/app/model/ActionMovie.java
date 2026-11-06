package se.iths.gruppsju.app.model;

public class ActionMovie extends Movie {

    public ActionMovie(int id, String title) {
        super(id, title);
    }

    @Override
    public String category(String movieCategory) {
        return "Action";
    }
}
