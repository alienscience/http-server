package uk.org.alienscience.routes;

import uk.org.alienscience.*;

import javax.annotation.concurrent.ThreadSafe;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Routing based on a decision tree
 */
@ThreadSafe
public class DecisionTree implements HttpRoutes {

    private static final Charset charset = Charset.forName("ISO-8859-1");
    
    // If this is not null then this tree is a leaf containing a single handler
    HttpHandler leaf;
   
    // If leaf is null then this tree is a branch
    AtomicReference<ArrayList<Node>> branchRef;

    // Create an empty tree
    public DecisionTree() {
        ArrayList<Node> nodes = new ArrayList<Node>();
        branchRef = new AtomicReference<ArrayList<Node>>(nodes);
    }
   
    // Create a leaf
    public DecisionTree(HttpHandler handler) {
        leaf = handler;
    }
    
    @Override
    public void add(String url, HttpHandler handler) {
        // TODO handle URL encoding
        byte[] path = url.getBytes(charset);
        
        // Find the first non / character
        int start = findNextStart(path, 0);
        
        createNode(path, start, handler);
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
 
    private void createNode(byte[] path, int start, HttpHandler handler) {
        int end = findNextSeparator(path, start);
        
        // The path defines the type of node to create
        // Consider wildcards
        if (path[start] == 0x2a) {
            // Wildcards always signify a leaf node
            DecisionTree tree = new DecisionTree(handler);
            
            if (end - start <= 1) {
                add(new WildCardNode(tree));
                return;
            }
            
            // Assume this is a glob start
            byte[] glob = Arrays.copyOfRange(path, start, end);
            add(new GlobStartNode(glob, tree));
            return;
        }
       
        // Consider captures
        if (path[start] == 0x7b && path[end] == 0x7d) {
            if (end - start <= 1) {
                // TODO throw an exception
            }
            // TODO this is unfinished, a new tree does not have to be created each time
            String name = new String(path, start +1, end -1, charset);
            Node node = new CaptureNode(name,)
            DecisionTree tree = new DecisionTree();
            int nextStart = findNextStart(path, end);
            tree.createNode(path, nextStart, handler);
            add(new CaptureNode(name, tree));
            return;
        }
        
        // Default to literal nodes
        byte[] literal = Arrays.copyOfRange(path, start, end);
        DecisionTree tree = new DecisionTree();
        int nextStart = findNextStart(path, end);
        tree.createNode(path, nextStart, handler);
        add(new LiteralNode(literal, tree));
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
    RouteLookup lookup(HttpRequest httpRequest, byte[] path, int start, int end) {
        
        // Is this a leaf?
        if (leaf != null) {
            return RouteLookup.found(leaf);
        }
       
        // Is there any path to look at?
        if (start >= path.length) return RouteLookup.notFound();
        
        ArrayList<Node> branch = branchRef.get();
        
        // Loop through the nodes in this branch
        for (Node node : branch) {
            HttpHandler handler = node.lookup(httpRequest, start, end);
            if (handler != null) {
                return RouteLookup.found(handler);
            }
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
