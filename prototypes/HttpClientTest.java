package http.request.test;
import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 *Makes http request to a "quotes" api and and prints the JSON response
 *
 */
public class HttpRequestTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpResponse response = null;
        response = httpClient.send(HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://quotes.rest/qod"))  
                .build(), HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }    

}
