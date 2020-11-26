package prototypes;
/*
*Last updated on MM/DD/20
*
*Makes http request to a "jokes" api and and prints the JSON response
*
*Contributing authors
*@author Andy?
*@author Francisco?
*/
import java.io.IOException;
import java.net.URI;
import java.net.http.*;
/**
 *
 *
 *
 */

public class HttpClientTest{

    public static void getHttpResponseAndPrint() throws IOException, InterruptedException {

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse response = httpClient.send(HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://sv443.net/jokeapi/v2/joke/Any?amount=10"))
                .build(), HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }


}
