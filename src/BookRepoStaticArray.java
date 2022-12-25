import java.util.Scanner;

public class BookRepoStaticArray implements BookRepoInterface {
    Scanner sc = new Scanner(System.in);

    private int index = 0;

    private final Book[] books = new Book[10];
    @Override
    public String save(Book book) throws BookException {
        try {
            return "Книга с названием"+getBookByName(book.getName())+" уже существует";
        }
        catch (BookException c){
            books[index] = book;
            index++;
            return "Книга создана";
        }


    }
    @Override
    public boolean delete(String name) {
        int id = getIdByName(name);
        if (id == -1)
            return false;
        for (int i = id; i < index; i++)
            books[i] = books[i + 1];
        index--;
        return true;

    }
    @Override
    public String getBook(int id) {

        return books[id].toString();
    }
    @Override
    public String getAllBook() {
        StringBuilder q = new StringBuilder();
        for (int j = 0; j <= index - 1; j++)
            q.append(books[j].toString());
        return q.toString();

    }
    @Override
    public Book getBookByName(String name) throws BookException {
        int id = getIdByName(name);
        if (id == -1)
            throw new BookException("Такой книги нет");
        return books[id];
    }
    @Override
    public int getIdByName(String name) {
        for (int i = 0; i < index; i++)
            if (books[i].getName().equals(name))
                return i;
        return -1;

    }
    @Override
    public String putBook(int id, String author, String name) throws BookException {
        if (books[id] != null) {
            books[id].setAuthor(author);
            books[id].setName(name);
            return "Изменение принято";
        }
        throw new BookException("Нет такой книги");
    }
}


