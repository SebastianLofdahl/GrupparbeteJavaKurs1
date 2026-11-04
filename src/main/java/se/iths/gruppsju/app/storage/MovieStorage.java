package se.iths.gruppsju.app.storage;

import java.io.IOException;
import java.util.List;

public interface MovieStorage<T> {
    void save(T movie) throws IOException;

    T findById(int id) throws IOException;

    List<T> findAll() throws IOException;
}
