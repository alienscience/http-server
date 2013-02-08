package uk.org.alienscience;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

/**
 * A runnable object that handles a single HttpConnection
 */
public class HttpConnection implements Runnable {
    // TODO: make the buffer size configurable
    private static final int BUFFER_SIZE_BYTES = 16384;
    private static final Charset charset = Charset.forName("UTF-8");

    private final SocketChannel channel;
    private final HttpRoutes routes;
    private final CharsetEncoder encoder;

    public HttpConnection(SocketChannel channel, HttpRoutes routes) {
        this.channel = channel;
        this.routes = routes;
        this.encoder = charset.newEncoder();
    }

    @Override
    public void run() {
        HttpParser parser = new HttpParser();
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE_BYTES);

        HttpRequest request = new HttpRequest();
        HttpResponse response = new HttpResponse(channel, encoder);

        try {
            do {
                // TODO: use slf4j
                System.out.println("Got connection");
                parser.reset(request);
                parseHeader(parser, buffer);
                callHandler(request, response);
            } while (request.keepalive);
        } finally {
            try {
                channel.close();
            } catch (IOException e) {
                // Ignored
            }
        }
    }

    // Parse a HTTP header
    // Returns true on success, false otherwise
    private boolean parseHeader(HttpParser parser, ByteBuffer buffer) {
        buffer.clear();

        try {
            while (channel.read(buffer) != -1) {
                buffer.flip();
                ParseState state = parser.parse(buffer);
                if (state != ParseState.INCOMPLETE) {
                    break;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Call a HttpHandler to handle the given request
    private void callHandler(HttpRequest request, HttpResponse response) {
        RouteLookup route = routes.lookup(request);
        if (!route.isAvailable()) {
            response.sendError(HttpResponse.SC_NOT_FOUND);
            return;
        }

        route.handle(request, response);
    }


}
