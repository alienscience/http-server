package uk.org.alienscience.routes;

import uk.org.alienscience.HttpHandler;
import uk.org.alienscience.HttpRequest;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
interface Node {

    /**
     * Finds the handler matching part of a path
     * @param request  The request containing the path to match against
     * @param path The path to match against
     * @param start The start index of the part to match against
     * @param end The end index + 1 of the part to match against
     * @return The matching handler if one exists, null otherwise
     */
    HttpHandler lookup(HttpRequest request, byte[] path, int start, int end);

    /**
     * Insert a lookup into this node
     * @return true if the lookup could be inserted, false if not
     */
    boolean insert(byte[] path, int start, int end, HttpHandler handler);

}
