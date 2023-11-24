package integracaoSlack;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import helpers.Helper;
import org.json.JSONObject;

public class Slack {
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final String URL = "aHR0cHM6Ly9ob29rcy5zbGFjay5jb20vc2VydmljZXMvVDA1TlI2WlVEMFIvQjA2Nzg0MVVLUjgvUGlsQkRYd1RIbWlFOWdxSzk3RzBOWm1z";



    public static void sendMessage(JSONObject objeto) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(
                URI.create(Helper.decodeBase64ToString(URL)))
                .header("accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(objeto.toString())
        ).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

       System.out.printf("""
               Status: %s
               Response: %s
               %n""", response.statusCode(), response.body());
    }
}
