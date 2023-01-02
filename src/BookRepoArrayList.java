import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class BookRepoArrayList implements BookRepoInterface {
    List<Book> bookList = new ArrayList<>();

    @Override
    public boolean save(Book book) throws IOException {
        if(getBookByName(book.getName())==null)
           return bookList.add(book);
        return false;
    }

    @Override
    public boolean delete(String name) {
        if (getIdByName(name) == -1)
            return false;
        bookList.remove(getIdByName(name));
        return true;
    }

    @Override
    public String getBook(int id) throws RuntimeException {
        try {
            return bookList.get(id).toString();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }


    @Override
    public String getAllBook() {
        StringBuilder q = new StringBuilder();
        for (Book books : bookList)
            q.append(books.toString());
        return q.toString();
    }

    @Override
    public Book getBookByName(String name) {
        if(getIdByName(name)==-1)
           return null;

        return bookList.get(getIdByName(name));
    }

    @Override
    public int getIdByName(String name) {
        ListIterator<Book> listIterator = bookList.listIterator();
        while(listIterator.hasNext()){
            if(listIterator.next().getName().equals(name))
                return listIterator.nextIndex()-1;

        }
        return -1;

        }

    @Override
    public String putBook(int id, String author, String name) throws BookException {
        try {
            bookList.get(id).setAuthor(author);
            bookList.get(id).setName(name);
            return "Изменения приняты";
        } catch (Exception e) {
            throw new BookException("Не удалось внести изменения");
        }
    }
}
