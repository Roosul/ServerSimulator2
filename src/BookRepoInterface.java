import java.io.IOException;

public interface BookRepoInterface {

    boolean save(Book book) throws BookException, IOException, ClassNotFoundException;

    boolean delete(String name);

    String getBook(int id) throws IOException, ClassNotFoundException;

    String getAllBook() throws IOException, ClassNotFoundException;

    Book getBookByName(String name) throws BookException, IOException, ClassNotFoundException;

    int getIdByName(String name);

    String putBook(int id, String author, String name) throws BookException;
}
