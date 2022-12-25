import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static final Controller controller = new Controller();

    public static void main(String[] args) {


        while (true) {
            System.out.println("Введите тип метода");
            String methodType = sc.nextLine();
            System.out.println("Введите url");
            String url = sc.nextLine();
            System.out.println("Введите название,автора,и количество страниц книги через пробелы");
            String requestBody = sc.nextLine();
            String response = null;
            switch (methodType) {
                case ("POST") -> {
                    switch (url) {
                        case ("/book/add") -> {
                            response = controller.addBook(requestBody);
                        }
                    }
                }
                case ("GET") -> {
                    switch (url) {
                        case ("/book/get") -> {
                            response = controller. getBook(requestBody);
                        }
                        case ("/book/getAll") -> {
                            response = controller.getAllBook();
                        }
                        case ("/book/getName") -> {
                            response = controller.getBookByName(requestBody);
                        }
                    }
                }

                case ("PUT") ->
                        response = controller.putNewParam(requestBody);

            case ("DELETE") -> response = controller.deleteBook(requestBody);
        default -> response = "Error MethodType(" + methodType + ")";
    }
            if(response ==null)
    response ="Error URL("+url+")";System.out.println(response);

}

    }
            }