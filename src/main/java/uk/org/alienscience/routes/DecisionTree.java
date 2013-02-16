package uk.org.alienscience.routes;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

import javax.annotation.concurrent.ThreadSafe;

import uk.org.alienscience.HttpHandler;
import uk.org.alienscience.HttpRequest;
import uk.org.alienscience.HttpRoutes;
import uk.org.alienscience.RouteLookup;
import uk.org.alienscience.SocketEventHandler;

/**
 * Routing based on a decision tree
 */
@ThreadSafe
public class DecisionTree implements HttpRoutes {

    // If this is not null then this tree is a leaf containing a single handler
    HttpHandler leaf;
   
    // If leaf is null then this tree is a branch
    AtomicReference<ArrayList<Node>> branchRef;
    
    @Override
    public void add(String url, HttpHandler handler) {
        // TODO Auto-generated method stub

    }

    @Override
    public void add(String url, SocketEventHandler handler) {
        // TODO Auto-generated method stub

    }
    
    @Override
    public RouteLookup lookup(HttpRequest httpRequest) {
        byte[] path = httpRequest.getPath();
        
        // Find the first non / character
        int start = findNextStart(path, 0);
        
        // Find the next / character
        int end = findNextSeparator(path,start);
        return lookup(httpRequest, path, start, end);
    }
  
    // Find the start of a path, returns -1 if not found
    static private int findNextStart(byte[] path, int from) {
        int i;
        for (i = from; i < path.length; i++) {
            if (path[i] != 0x2f) {
                return i;
            }
        }
        return path.length;
    }
   
    // Find the next '/' character in a path or the end of the path
    static private int findNextSeparator(byte[] path, int start) {
        int i;
        for (i = start; i < path.length; i++) {
            if (path[i] == 0x2f) {
                return i;
            }
        }
        return path.length;
    }
    
    // Lookup a route using part of a path
    private RouteLookup lookup(HttpRequest httpRequest, byte[] path, int start, int end) {
        
        // Is this a leaf?
        if (leaf != null) {
            return RouteLookup.found(leaf);
        }
       
        // Is there any path to look at?
        if (start >= path.length) return RouteLookup.notFound();
        
        ArrayList<Node> branch = branchRef.get();
        
        // Loop through the nodes in this branch
        for (Node node : branch) {
            if (node.matches(httpRequest, start, end)) {
                int nextStart = findNextStart(path, end);
                int nextEnd = findNextSeparator(path, nextStart);
                return node.getTree().lookup(httpRequest, path, nextStart, nextEnd);
            }
        }
       
        // If this point is reached - no match occured
        return RouteLookup.notFound();
    }

}
