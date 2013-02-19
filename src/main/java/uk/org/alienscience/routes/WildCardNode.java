package uk.org.alienscience.routes;

import uk.org.alienscience.HttpHandler;
import uk.org.alienscience.HttpRequest;

/**
 * Matches anything
 */
class WildCardNode implements Node {

    private final HttpHandler handler;

    WildCardNode(HttpHandler handler) {
        this.handler = handler;
    }
    
    @Override
    public HttpHandler lookup(HttpRequest request, byte[] path, int start, int end) {
       return handler;
    }

    @Override
    public boolean insert(byte[] path, int start, int end, HttpHandler handler) {
        // No new nodes can be added to this object
        return false;
    }
}
