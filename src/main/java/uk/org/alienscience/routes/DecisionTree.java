package uk.org.alienscience.routes;

import net.jcip.annotations.ThreadSafe;
import uk.org.alienscience.*;

import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Routing based on a decision tree
 */
@ThreadSafe
public class DecisionTree implements HttpRoutes {

    private static final Charset charset = Charset.forName("ISO-8859-1");
    
    // The root of the tree
    private final AtomicReference<Node> root;
    
    // Create an empty tree
    public DecisionTree() {
        root = new AtomicReference<Node>();
    }
   
    
    @Override
    public void add(String pattern, HttpHandler handler) {
        // TODO handle URL encoding
        byte[] path = pattern.getBytes(charset);
        
        // Find the first non / character
        int start = NodeWalk.nextNonSeparator(path, 0);
        if (start == -1) throw new IllegalArgumentException("Not a valid path pattern:" + pattern);
        
        // Find the next / character
        int end = NodeWalk.nextEnd(path, start);
    
        // Insert
        NodeWalk.insert(root, path, start, end, handler);
    }

    @Override
    public void add(String pattern, SocketEventHandler handler) {
        // TODO implement
    }
    
    @Override
    public RouteLookup lookup(HttpRequest httpRequest) {
        byte[] path = httpRequest.getPath();
        
        // Find the first non / character
        int start = NodeWalk.nextNonSeparator(path, 0);
        int end;
        if (start == -1) {
            // Empty path lookup
            start = 0;
            end = 0;
        } else {
            // Find the next / character
            end = NodeWalk.nextEnd(path, start);
        }
        
        // Lookup
        HttpHandler handler = NodeWalk.lookup(root, httpRequest, path, start, end);
        
        // Wrap the result
        if (handler == null) return RouteLookup.notFound();
        return RouteLookup.found(handler);
    }
 
}
