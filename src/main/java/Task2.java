import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Task2 {
    public String writeLastCommentsInJson(int userID) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users/" + userID +"/posts"))
                .header(HttpHeaderUtils.CONTENT_TYPE_NAME, HttpHeaderUtils.VALUE_JSON)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode jsonNode = ObjectMapperUtils.OBJECT_MAPPER.readTree(response.body());
        int lastPostNumber = 0;
        for (JsonNode post : jsonNode) {
            int userId = post.get("id").asInt();
            if (userId > lastPostNumber) {
                lastPostNumber= userId;
            }
        }

        HttpRequest requestComments = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts/"+lastPostNumber+"/comments"))
                .header(HttpHeaderUtils.CONTENT_TYPE_NAME, HttpHeaderUtils.VALUE_JSON)
                .GET()
                .build();

        HttpResponse<String> responseComments = client.send(requestComments, HttpResponse.BodyHandlers.ofString());
        String fileName = "user-" + userID + "-post-" + lastPostNumber + "-comments.json";
        ObjectMapperUtils.OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), jsonNode);
        System.out.println("File created " + fileName);
        return responseComments.body();
    }
}
