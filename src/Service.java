import java.io.IOException;

public class Service {
    final BookRepoStaticArray bookRepoStaticArray = new BookRepoStaticArray();
    final ReadingFromFileRepo readingFromFileRepo = new ReadingFromFileRepo();
    final BookRepoArrayList bookRepoArrayList= new BookRepoArrayList();

    public String addBook(String name, String author, int page)  {
        Book book = new Book(name, author, page);
        try {
            if(readingFromFileRepo.save(book))
                return "Книга создана";
            return "Такая книга уже существует";
        } catch (Exception e) {
            return e.toString();

        }




    }

    public String getBook(int id) {

        try {
            return readingFromFileRepo.getBook(id);
        }
        catch (RuntimeException e){
            return "Такой книги нет";
        }
    }

    public String getAllBook() {

        return readingFromFileRepo.getAllBook();
    }

    public String getBookByName(String name) {

        try {
            Book book = readingFromFileRepo.getBookByName(name);
            if (book == null)
                return "Такая книга не найдена";
            return book.toString();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public String deleteBook(String name) {
        if (readingFromFileRepo.delete(name))
            return "Книга удалена";
        return "Такой книги с таким названием нет";

    }

    public String putBook(int id, String author, String name)  {

        try {
            return readingFromFileRepo.putBook(id, author, name);
        } catch (BookException e) {
            return "Не удалось внести изменения";
        }
    }
}

