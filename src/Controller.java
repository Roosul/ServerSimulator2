import java.io.IOException;

public class Controller {
    final Service service = new Service();

    public String addBook(String requestBody) {
        String[] str = requestBody.split(" ");
        String name = str[0];
        String author = str[1];
        int page = Integer.parseInt(str[2]);
        return service.addBook(name, author, page);
    }

    public String getBook(String requestBody) {
        int id = Integer.parseInt(requestBody);
        return service.getBook(id);
    }

    public String getAllBook() {
        try {
            return service.getAllBook();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String getBookByName(String name) {
        return service.getBookByName(name);

    }

    public String deleteBook(String name) {
        return service.deleteBook(name);
    }


    public String putNewParam(String requestBody) {
        String[] str = requestBody.split(" ");
        int id = Integer.parseInt(str[0]);
        String author = str[1];
        String name = str[2];
        return (service.putBook(id, author, name));

    }


}
