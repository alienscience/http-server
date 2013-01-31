package uk.org.alienscience.routes;

import uk.org.alienscience.HttpHandler;
import uk.org.alienscience.HttpRequest;
import uk.org.alienscience.HttpRoutes;
import uk.org.alienscience.RouteLookup;

/**
 * HTTP routes implemented as a binary tree
 */
public class BinaryTree implements HttpRoutes {
    @Override
    public void add(String url, HttpHandler handler) {
        // TODO implement
    }

    @Override
    public RouteLookup lookup(HttpRequest httpRequest) {
        // TODO implement
        return null;
    }
}
