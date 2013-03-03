package uk.org.alienscience;

import uk.org.alienscience.routes.RoutingException;

/**
 * Route URLs to HTTP handlers
 */
public interface HttpRoutes {

    /**
     * Add a route that is handled through a blocking API.
     * @param url The path or URL of the route.
     * @param handler A handler to handle the route.
     * @throws RoutingException 
     */
    public void add(String url, HttpHandler handler) throws RoutingException;

    /**
     * Add a route that is handled through a non-blocking API.
     * @param url The path or URL of the route.
     * @param handler A handler to handle the route.
     */
    public void add(String url, SocketEventHandler handler);

    /**
     * Lookup a HttpHandler for the given request.
     * @param httpRequest The HttpRequest to handle.
     * @return An object containing a handler any values extracted from looking up the route.
     */
    public RouteLookup lookup(HttpRequest httpRequest);

}
