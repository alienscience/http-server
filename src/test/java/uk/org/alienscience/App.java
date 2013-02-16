package uk.org.alienscience;

import java.io.IOException;

/**
 * Example HttpServer
 */
public class App {

    public static void main(String[] args) throws IOException {
        HttpServer server = new HttpServer();
        server.route("/hello", new HttpHandler() {
            @Override
            public void doGet(HttpRequest request, HttpResponse response) throws IOException {
                response.body("Hello world\r\n");
            }
        });
        server.start();
    }
}
