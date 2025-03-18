import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Task1 task1 = new Task1();
        Task2 task2 = new Task2();
        Task3 task3 = new Task3();
        String jsonBody = "{\"username\": \"Vladyslav\"}";
        String url = "https://jsonplaceholder.typicode.com/users";
        //Task 1
        System.out.println(task1.add(url, jsonBody));
        System.out.println(task1.update(url, jsonBody, 2));
        System.out.println(task1.delete(url, 4));
        System.out.println(task1.allUsersInfo(url));
        System.out.println(task1.idUserInfo(url, 8));
        System.out.println(task1.nameUserInfo(url, "Bret"));
        //Task 2
        System.out.println(task2.writeLastCommentsInJson(4));
        System.out.println(task2.writeLastCommentsInJson(1));
        //Task 3
        System.out.println(task3.openedTasks(3));
    }
}
