import java.io.*;
import java.util.Arrays;

public class ReadingFromFileRepo implements BookRepoInterface {
    FileOutputStream fileInt;
    ObjectOutputStream oosInt;

    {
        try {
            fileInt = new FileOutputStream("fileint.bin");
            oosInt = new ObjectOutputStream(fileInt);
            int index = 0;
            oosInt.writeObject(index);
            oosInt.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    String path = "C:\\Users\\rasul\\IdeaProjects\\ServerSimulator\\BookRepoFile.bin";

    @Override
    public boolean save(Book book) throws IOException {
        try {
            String name = book.getName();
            System.out.println(name);
            if (getBookByName(name) != null)
                return false;
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            Book[] books = new Book[10];
            oos.writeObject(books);
            oos.close();


            return true;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public boolean delete(String name) {
        int id = getIdByName(name);
        if (id == -1)
            return false;
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Book[] books = (Book[]) ois.readObject();
            ois.close();
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(books);
            return true;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public String getBook(int id) {
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Book[] books = (Book[]) ois.readObject();
            ois.close();
            if (books[id] == null) {
                return "Нет такой книги";
            }
            return books[id].toString();

        } catch (ClassNotFoundException | IOException e) {
            return "ЖОПА";
        }


    }

    @Override
    public String getAllBook() {
        try {
            StringBuilder q = new StringBuilder();
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Book[] books = (Book[]) ois.readObject();
            ois.close();
            for (Book book : books) {
                if (book != null)
                    q.append(book);
            }

            return q.toString();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Book getBookByName(String name) throws IOException, ClassNotFoundException {
        if (getIdByName(name) == -1) {
            return null;
        }
        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Book[] books = (Book[]) ois.readObject();
        ois.close();
        return books[getIdByName(name)];
    }

    @Override
    public int getIdByName(String name) {
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Book[] books = (Book[]) ois.readObject();
            int i = 0;
            ois.close();
            for (Book book : books) {
                if(book!=null) {
                    if (name.equals(book.getName())) {
                        return i;
                    }
                }
                i++;
            }
            return -1;
        } catch (IOException | ClassNotFoundException e) {
            return -1;
        }
    }

    @Override
    public String putBook(int id, String author, String name) throws BookException {
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Book[] books = (Book[]) ois.readObject();
            ois.close();
            if (books[id] != null) {
                books[id].setAuthor(author);
                books[id].setName(name);
                return "Изменение принято";
            }
            return "Книги по такому id не найдено";
        } catch (IOException | ClassNotFoundException e) {
            return "Книги по такому id не найдено";
        }

    }
}
