package uk.org.alienscience.routes;

import uk.org.alienscience.HttpHandler;
import uk.org.alienscience.HttpRequest;

import javax.annotation.concurrent.ThreadSafe;
import java.nio.ByteBuffer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

/**
 * A node that matches a literal sequence of bytes
 */
@ThreadSafe
class LiteralNode implements Node {

    private final ConcurrentHashMap<ByteBuffer,Node> nodes;
    private AtomicReference<Node> next;


    LiteralNode() {
        nodes = new ConcurrentHashMap<>();
    }
    
    @Override
    public HttpHandler lookup(HttpRequest request, byte[] path, int start, int end) {
        ByteBuffer key = ByteBuffer.wrap(path, start, end - start);
        Node value = nodes.get(key);
        if (value != null) {
            return NodeWalk.lookup(value, request, path, start, end);
        }
        return NodeWalk.lookup(next, request, path, start, end);
    }

    @Override
    public boolean insert(byte[] path, int start, int end, HttpHandler handler) {
        if (isLiteral(path, start, end)) {
            ByteBuffer key = ByteBuffer.wrap(path, start, end - start);
            Node value = nodes.get(key);

            if (value != null) {
                // Insert the new branch into the existing matching node
                return NodeWalk.insert(value, path, start, end, handler);
            } else {
                // Decide what type of node to create and then add it
                Node newNode = NodeWalk.create(path, start, end, handler);

                // Thread safe insertion of a new node
                value = nodes.putIfAbsent(key, newNode);
                if (value != null) {
                    return NodeWalk.insert(value, path, start, end, handler);
                }
                return true;
            }
        } else {
            // TODO put this logic into Nodewalk and call:
            //      NodeWalk.insert(next, path, start, end, handler)
            if (next.get() == null) {
                Node newNode = NodeWalk.create(path, start, end, handler);
                if (next.compareAndSet(null, newNode)) {
                    return true;
                }
            }
            return NodeWalk.insert(next.get(), path, start, end, handler);
        }
    }
}
