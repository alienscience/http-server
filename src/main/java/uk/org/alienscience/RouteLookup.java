package uk.org.alienscience;

/**
 * The result of looking up a route
 */
public class RouteLookup {

    private final boolean isAvailable;
    private final HttpHandler handler;

    /**
     * Create an unavailable route
     */
    public RouteLookup() {
        isAvailable = false;
        handler = null;
    }

    /**
     * Create an available route
     */
    public RouteLookup(HttpHandler handler) {
        this.isAvailable = true;
        this.handler = handler;
    }

    /**
     * Handle a HTTP request matching this route
     * @param request Http request header information
     * @param response The response to send back to the caller
     */
    public void handle(HttpRequest request, HttpResponse response) {
        // TODO: If blocking, setup request/response and call handler
        // TODO: If non-blocking, send to non-blocking thread

        switch (request.method) {
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
                response.sendError(HttpResponse.SC_METHOD_NOT_ALLOWED);
        }
    }

    public boolean isAvailable() {
        return isAvailable;
    }

}
