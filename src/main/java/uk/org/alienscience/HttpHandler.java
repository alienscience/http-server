package uk.org.alienscience;

/**
 * TODO: Document
 */
public abstract class HttpHandler {

    public void doGet(HttpRequest request, HttpResponse response) {
        response.sendError(HttpResponse.SC_NOT_FOUND);
    }

    public void doHead(HttpRequest request, HttpResponse response) {
        response.sendError(HttpResponse.SC_NOT_FOUND);
    }

    public void doPost(HttpRequest request, HttpResponse response) {
        response.sendError(HttpResponse.SC_NOT_FOUND);
    }

    public void doPut(HttpRequest request, HttpResponse response) {
        response.sendError(HttpResponse.SC_NOT_FOUND);
    }

    public void doDelete(HttpRequest request, HttpResponse response) {
        response.sendError(HttpResponse.SC_NOT_FOUND);
    }

}