import java.io.IOException;
import java.util.HashMap;
import java.util.ListIterator;

public class BookRepoHashMap implements BookRepoInterface {
    private static final HashMap<Integer, Book> books = new HashMap<>();
    int index = 0;


    @Override
    public boolean save(Book book) throws BookException, IOException, ClassNotFoundException {
        if (getBookByName(book.getName()) == null){
            books.put(index, book);
            index++;
        return true;
        }
        return false;

    }

    @Override
    public boolean delete(String name) {
        int id = getIdByName(name);
        if (id == -1)
            return false;
        books.remove(id);
        index--;
        return true;
    }

    @Override
    public String getBook(int id) throws IOException, ClassNotFoundException {
        return String.valueOf(books.get(id));
    }

    @Override
    public String getAllBook() throws IOException, ClassNotFoundException {
        StringBuilder q = new StringBuilder();
        for (int j = 0; j <= index - 1; j++)
            q.append(books.get(j).toString());
        return q.toString();
    }

    @Override
    public Book getBookByName(String name) throws BookException, IOException, ClassNotFoundException {
        int id = getIdByName(name);
        if (id == -1)
            return null;
        return books.get(id);
    }

    @Override
    public int getIdByName(String name) {
        for (int i = 0; i < index; i++)
            if (books.get(i).getName().equals(name))
                return i;
        return -1;

    }

    @Override
    public String putBook(int id, String author, String name) throws BookException {
        if (books.get(id) != null) {
            books.get(id).setAuthor(author);
            books.get(id).setName(name);
            return "Изменение принято";
        }
        throw new BookException("Нет такой книги");
    }
}
