import java.io.*;

public class ReadingFromFileRepo implements BookRepoInterface {
    private int index = 0;

    String path = "C:\\Users\\rasul\\IdeaProjects\\ServerSimulator\\BookRepoFile.bin";

    @Override
    public String save(Book book) throws IOException {
        try {
            return "Книга с названием" + getBookByName(book.getName()) + " уже существует";
        } catch (BookException c) {
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            Book [] books = new Book[10];
            books[index] = book;
            oos.writeObject(books);
            index++;
            oos.close();
            return "Книга создана";
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public boolean delete(String name) {
        int id = getIdByName(name);
        if (id == -1)
            return false;

        for (int i = id; i < index; i++) {
            try {
                FileInputStream fis = new FileInputStream(path);
                ObjectInputStream ois = new ObjectInputStream(fis);
                Book[] books = (Book[]) ois.readObject();
                books[i] = books[i + 1];
                ois.close();
                FileOutputStream fos = new FileOutputStream(path);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(books);
                index--;
                return true;
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }


            @Override
        public String getBook ( int id){
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
        public String getAllBook () throws IOException, ClassNotFoundException {
            StringBuilder q = new StringBuilder();
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Book[] books = (Book[]) ois.readObject();
            ois.close();
            for (int i = 0; i <= index - 1; i++)
                q.append(books[i].toString());
            return q.toString();
        }

        @Override
        public Book getBookByName (String name) throws BookException, IOException, ClassNotFoundException {
            int id = getIdByName(name);
            if (id == -1) {
                throw new BookException("Такой книги нет");
            }
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Book[] books = (Book[]) ois.readObject();
            ois.close();
            return books[id];
        }

        @Override
        public int getIdByName (String name){

            try {
                    FileInputStream fis = new FileInputStream(path);
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    Book[] books = (Book[]) ois.readObject();
                    ois.close();
                    int i = 0;
                    for(Book book : books) {
                        if (book.getName().equals(name))
                            return i;
                        i++;
                    }
                return -1;
            } catch (IOException | ClassNotFoundException e) {
                return -1;
            }
        }

        @Override
        public String putBook ( int id, String author, String name) throws BookException {
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
