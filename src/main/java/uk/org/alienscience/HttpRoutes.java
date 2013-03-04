package uk.org.alienscience;

/**
 * Route URLs to HTTP handlers.
 * TODO: path pattern examples
 */
public interface HttpRoutes {

    /**
     * Add a route that is handled through a blocking API.
     * @param pattern A path pattern.
     * @param handler A handler to handle the route.
     * @throws IllegalArgumentException if the url is invalid
     */
    public void add(String pattern, HttpHandler handler);

    /**
     * Add a route that is handled through a non-blocking API.
     * @param pattern A path pattern.
     * @param handler A handler to handle the route.
     */
    public void add(String pattern, SocketEventHandler handler);

    /**
     * Lookup a HttpHandler for the given request.
     * @param httpRequest The HttpRequest to handle.
     * @return An object containing a handler any values extracted from looking up the route.
     */
    public RouteLookup lookup(HttpRequest httpRequest);

}
