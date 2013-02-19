package uk.org.alienscience.routes;

import uk.org.alienscience.HttpHandler;
import uk.org.alienscience.HttpRequest;

import java.nio.ByteBuffer;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Matches glob patterns that start with '*' e.g *.html
 */
class GlobStartNode implements Node {

    private final ConcurrentHashMap<ByteBuffer,HttpHandler> handlers;

    GlobStartNode() {
        handlers = new ConcurrentHashMap<>();
    }
    
    @Override
    public HttpHandler lookup(HttpRequest request, byte[] path, int start, int end) {
        Enumeration<ByteBuffer> keys = handlers.keys();

        // TODO see if this should match the whole path or just part of it
        while (keys.hasMoreElements()) {
            ByteBuffer buffer = keys.nextElement();
            byte[] literal = buffer.array();

            // Match the literal from the end to the start
            int i;
            for (i = end -1; i >= start; i--) {
                int j = i - start;
                if (j < 0) break;
                if (path[i] != literal[j]) break;
            }
            if (i < start) {
                return handlers.get(buffer);
            }
        }

        return null;
    }

    @Override
    public boolean insert(byte[] path, int start, int end, HttpHandler handler) {
        // TODO get literal and add to handlers map
    }
}
