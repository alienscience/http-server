package uk.org.alienscience.routes;

import uk.org.alienscience.HttpHandler;
import uk.org.alienscience.HttpRequest;

import java.nio.ByteBuffer;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Matches glob patterns that start with '*' e.g *.html
 */
class GlobStartNode implements Node {

    private final ConcurrentHashMap<ByteBuffer,HttpHandler> handlers;
    private AtomicReference<Node> next;

    GlobStartNode() {
        handlers = new ConcurrentHashMap<>();
    }

    /**
     *  Does the given section of path look like a glob start node?
     */
    static boolean isGlobStart(byte[] path, int start, int end) {
        // Is this a glob in the form *.ext or *ext
        return (path[start] == 0x2a && end - start > 2 );
    }

    @Override
    public HttpHandler lookup(HttpRequest request, byte[] path, int start, int end) {
        Enumeration<ByteBuffer> keys = handlers.keys();
        
        // Match the whole path
        int from = path.length - 1;
        
        // Loop through the globs
        while (keys.hasMoreElements()) {
            ByteBuffer buffer = keys.nextElement();
            byte[] literal = buffer.array();

            // Match the literal from the end to the start
            int i, j;
            for (i = from, j = literal.length; 
                    i >= start && j >= 0; 
                    i--, j--) {
                if (path[i] != literal[j]) break;
            }
            if (j < 0) {
                return handlers.get(buffer);
            }
        }

        return null;
    }

    @Override
    public boolean insert(byte[] path, int start, int end, HttpHandler handler) {
        // Is this a glob in the form *.ext or *ext 
        if (!isGlobStart(path, start, end)) {
            return NodeWalk.insert(next, path, start, end, handler);
        }
        
        // Insert into the current node
        ByteBuffer key = ByteBuffer.wrap(path, start+1, end - start - 1);
        handlers.put(key, handler);
        return true;
    }
}
