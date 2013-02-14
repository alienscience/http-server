package uk.org.alienscience;

import java.io.IOException;

/**
 * A class that is overriden to handle HTTP requests
 */
public abstract class HttpHandler {

    public void doGet(HttpRequest request, HttpResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND);
    }

    public void doHead(HttpRequest request, HttpResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND);
    }

    public void doPost(HttpRequest request, HttpResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND);
    }

    public void doPut(HttpRequest request, HttpResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND);
    }

    public void doDelete(HttpRequest request, HttpResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND);
    }

}
