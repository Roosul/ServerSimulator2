import java.io.IOException;

public class Service {
    final BookRepoStaticArray bookRepoStaticArray = new BookRepoStaticArray();
    final ReadingFromFileRepo readingFromFileRepo;

    {
        readingFromFileRepo = new ReadingFromFileRepo();
    }

    public String addBook(String name, String author, int page) {
        Book book = new Book(name, author, page);

        try {
            return readingFromFileRepo.save(book);

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


    }

    public String getBook(int id) {
        return readingFromFileRepo.getBook(id);
    }

    public String getAllBook() {
        try {
            return readingFromFileRepo.getAllBook();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String getBookByName(String name) {
        try {
            Book book = readingFromFileRepo.getBookByName(name);
            return book.toString();
        }
            catch (BookException c){
            return c.getMessage();
            } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String deleteBook(String name) {
        if (readingFromFileRepo.delete(name))
            return "Книга удалена";
        return "Такой книги с таким названием нет";

    }

    public String putBook(int id, String author, String name) {
        try {
            return readingFromFileRepo.putBook(id, author, name);
        } catch (BookException e) {
            return e.getMessage();
        }
    }

}


//TODO создать интерфейс bookRepoInerface от него будут наследоваться несколько реализаций bookRepo
//в одной будет статичный массив,в другой файл,в другой коллекции
