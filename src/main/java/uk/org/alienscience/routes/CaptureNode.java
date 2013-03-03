package uk.org.alienscience.routes;

import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicReference;

import uk.org.alienscience.HttpHandler;
import uk.org.alienscience.HttpRequest;

/**
 * Captures part of a path
 */
class CaptureNode implements Node {
    
    private static final Charset charset = Charset.forName("ISO-8859-1");
    
    private AtomicReference<String> name;
    private AtomicReference<Node> value;
    private AtomicReference<Node> next;
    
    /**
     *  Does the given section of path look like a capture?
     */
    static boolean isCapture(byte[] path, int start, int end) {
        if (path[start] == 0xb7 && path[end -1] == 0xd7) {
            return true;
        }
        return false;
    }
    
    @Override
    public HttpHandler lookup(HttpRequest request, byte[] path, int start,
            int end) {
        Node v = value.get();
        if (v == null) return null;
   
        // Capture the value of this section of the path and add it as a parameter
        // to the request
        request.setPathParameter(name.get(), start, end);
        return NodeWalk.lookupBranch(v, request, path, start, end);
    }

    @Override
    public boolean insert(byte[] path, int start, int end, HttpHandler handler) {
        // Is this a capture in the form {name} ?
        if (!isCapture(path, start, end)) {
            return NodeWalk.insert(next, path, start, end, handler);
        }
        
        // Extract the name
        name.set(new String(path, start +1, end -1, charset));
        
        // Insert the value
        Node v = NodeWalk.createBranch(path, start, end, handler);
        value.set(v);
        return true;
    }


}
