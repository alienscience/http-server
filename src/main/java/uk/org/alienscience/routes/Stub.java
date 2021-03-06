package uk.org.alienscience.routes;

import uk.org.alienscience.*;

/**
 * A route lookup that just returns a single handler
 */
public class Stub implements HttpRoutes {
    private HttpHandler httpHandler;
    
    // TODO: non-blocking handlers
    private SocketEventHandler socketEventHandler;

    @Override
    public void add(String url, HttpHandler handler) {
        this.httpHandler = handler;
    }

    @Override
    public void add(String url, SocketEventHandler handler) {
        this.socketEventHandler = handler;
    }

    @Override
    public RouteLookup lookup(HttpRequest httpRequest) {
        if (httpHandler == null) {
            return RouteLookup.notFound();
        }

        return RouteLookup.found(httpHandler);
    }
}
