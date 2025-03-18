import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class Task3 {
    public String openedTasks(int userID) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users/" + userID +"/todos"))
                .header(HttpHeaderUtils.CONTENT_TYPE_NAME, HttpHeaderUtils.VALUE_JSON)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JsonNode jsonNode = ObjectMapperUtils.OBJECT_MAPPER.readTree(response.body());
        List<JsonNode> jsonNodeList = new ArrayList<>();
        for (JsonNode task : jsonNode){
            boolean completed = task.get("completed").asBoolean();
            if (!completed){
                jsonNodeList.add(task);
            }
        }
        return ObjectMapperUtils.OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNodeList);
    }
}
