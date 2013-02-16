package uk.org.alienscience;

import java.io.IOException;

/**
 * The result of looking up a route
 */
public class RouteLookup {

    private final boolean isAvailable;
    private final HttpHandler handler;

    /**
     * Create an unavailable route
     */
    public static RouteLookup notFound() {
        return new RouteLookup(false, null);
    }
   
    /**
     * Create an available route
     */
    public static RouteLookup found(HttpHandler handler) {
        return new RouteLookup(true, handler);
    }
    
    /**
     * Create a route lookup
     */
    private RouteLookup(boolean isAvailable, HttpHandler handler) {
        this.isAvailable = isAvailable;
        this.handler = handler;
    }

    /**
     * Handle a HTTP request matching this route
     * @param request Http request header information
     * @param response The response to send back to the caller
     */
    public void handle(HttpRequest request, HttpResponse response) throws IOException {
        // TODO: If non-blocking, send to non-blocking thread

        switch (request.getMethod()) {
            case GET:
                handler.doGet(request,response);
                break;
            case POST:
                handler.doPost(request,response);
                break;
            case HEAD:
                handler.doHead(request,response);
                break;
            case PUT:
                handler.doPut(request,response);
                break;
            case DELETE:
                handler.doDelete(request,response);
                break;
            default:
                response.sendError(HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    public boolean isAvailable() {
        return isAvailable;
    }

}
