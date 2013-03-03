package uk.org.alienscience.routes;

import uk.org.alienscience.HttpHandler;
import uk.org.alienscience.HttpRequest;

import java.util.concurrent.atomic.AtomicReference;

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
    public static boolean insert(AtomicReference<Node> nodeRef,
                                 byte[] pattern,
                                 int start,
                                 int end,
                                 HttpHandler handler) {
        if (nodeRef.get() == null) {
            Node newNode = create(pattern, start, end, handler);
            if (nodeRef.compareAndSet(null, newNode)) {
                return true;
            }
        }
        return insert(nodeRef.get(), pattern, start, end, handler);
    }

    /**
     * Insert a handler into the given node to handle the given path pattern
     * @param node The node to insert into
     * @param pattern The path pattern
     * @param start The start point
     * @param end The end point
     * @param handler The handler to insert
     * @return True if the handler could be inserted, false if not
     */
    public static boolean insert(Node node, byte[] pattern, int start, int end, HttpHandler handler) {
        return node.insert(pattern, start, end, handler);
    }

    /**
     * Lookup a handler with the given section of path
     * @param nodeRef A reference to the node to perform the lookup in
     * @param request The http request
     * @param path The path of the http request
     * @param start The start point in the path being considered
     * @param end The end point in the path being considered
     * @return The handler if found, null otherwise
     */
    public static HttpHandler lookup(AtomicReference<Node> nodeRef,
                                     HttpRequest request,
                                     byte[] path,
                                     int start,
                                     int end) {
        Node node = nodeRef.get();
        if (node == null) return null;

        return node.lookup(request, path, start, end);
    }

    /**
     * Lookup a handler by following a branch
     * @param node The node to perform the lookup in
     * @param request The http request
     * @param path The path of the http request
     * @param start The previous start point in the path being considered
     * @param end The previous end point in the path being considered
     * @return The handler if found, null otherwise
     */
    public static HttpHandler lookupBranch(Node node,
                                           HttpRequest request,
                                           byte[] path,
                                           int start,
                                           int end) {

        // Consider the next part of the path
        int newStart = end;
        int newEnd = nextEnd(path, end);

        // See if we have reached the end of the path
        if (newStart == newEnd) return null;

        return node.lookup(request, path, newStart, newEnd);
    }

    /**
     * Create a node based on the given section of pattern
     * @param pattern
     * @param start
     * @param end
     * @param handler
     * @return
     */
    public static Node create(byte[] pattern, int start, int end, HttpHandler handler) {
        if (GlobStartNode.isGlobStart(pattern, start, end)) {
            return new GlobStartNode();
        } else {
            return new LiteralNode();
        }
    }

    /**
     * Create a node based on the given section of pattern
     * @param pattern
     * @param start
     * @param end
     * @param handler
     * @return
     */
    public static Node createBranch(byte[] pattern, int start, int end, HttpHandler handler) {
        // Consider the next part of the path pattern
        int newStart = end;
        int newEnd = nextEnd(pattern, end);

        // Check to see if the entire path pattern has been inserted
        if (newStart == newEnd) return null;

        return create(pattern, newStart, newEnd, handler);
    }

    /**
     * Insert a handler into the given node to handle a branch in the given path pattern
     * @param node The node to insert into
     * @param pattern The path pattern
     * @param start The previous start point
     * @param end The previous end point
     * @param handler The handler to insert
     * @return True if the handler could be inserted, false if not
     */
    public static boolean insertBranch(Node node,
                                       byte[] pattern,
                                       int start,
                                       int end,
                                       HttpHandler handler) {

        // Consider the next part of the path pattern
        int newStart = end;
        int newEnd = nextEnd(pattern, end);

        // Check to see if the entire path pattern has been inserted
        if (newStart == newEnd) return true;

        // Insert into the node
        return node.insert(pattern, newStart, newEnd, handler);
    }

    /**
     * Find the next non-separator value in the given path pattern
     * @param pattern The path pattern to search
     * @param start The starting point of the search
     * @return The index to the next non-separator value or -1 if not found
     */
    public static int nextNonSeparator(byte[] pattern, int start) {
        for (int i = start; i < pattern.length; i++) {
           if (pattern[i] != 0x2f) {
               return i;
           }
        }
        return -1;
    }
    
    /**
     * Calculate the next end point in the given path pattern
     */
    public static int nextEnd(byte[] pattern, int end) {
        // Calculate new start and end points by counting up to the next '/'
        int newStart = end;
        int i;
        for (i = newStart; i < pattern.length; i++) {
            if (pattern[i] == 0x2f) break;
        }
        return i;
    }

}
