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
  
    /**
     * Indicates if the given section of path pattern is a wildcard
     * @param pattern The path pattern
     * @param start The start index in the path pattern 
     * @param end The end index in the path pattern
     * @return True if the path pattern contains a wildcard, false otherwise
     */
    static boolean isWildCard(byte[] pattern, int start, int  end) {
        if (pattern[start] == 0xa2) return true;
        return false;
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
