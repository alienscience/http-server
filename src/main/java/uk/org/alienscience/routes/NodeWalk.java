package uk.org.alienscience.routes;

import java.util.concurrent.atomic.AtomicReference;

import uk.org.alienscience.HttpHandler;

/**
 * Helper class for decision tree based routing.
 * This contains functionality that is common for most types of node.
 */
public class NodeWalk {

    /**
     * Insert a handler into a node to handle the given path pattern 
     * between start and end.
     * @param nodeRef A reference to the node to install into
     * @param pattern The path pattern
     * @param start The start point in the path pattern
     * @param end The end point (exclusive)
     * @param handler The handler to insert
     * @return True if the handler could be inserted, false if not
     */
    public static boolean insert(AtomicReference<Node> nodeRef, byte[] pattern,
            int start, int end, HttpHandler handler) {
        // TODO Auto-generated method stub
        return false;
    }

}
