package uk.org.alienscience;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * A runnable object that handles a single HttpConnection
 */
public class HttpConnection implements Runnable {
    // TODO: make the buffer size configurable
	// TODO: use same buffer for input and output
    private static final int BUFFER_SIZE_BYTES = 16384;

    private final SocketChannel channel;
    private final HttpRoutes routes;

    public HttpConnection(SocketChannel channel, HttpRoutes routes) {
        this.channel = channel;
        this.routes = routes;
    }

    @Override
    public void run() {
        HttpParser parser = new HttpParser();
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE_BYTES);
        BufferedChannel bufferedChannel = new BufferedChannel(channel, buffer);

        HttpRequest request = new HttpRequest();
        HttpResponse response = new HttpResponse(bufferedChannel);

        try {
            do {
                // TODO: use slf4j
                System.out.println("Got connection");
                
                // Parse the request
                parser.reset(request);
                parseHeader(parser, buffer);
               
                // Initialise the response
                response.reuse();
                response.setHttpVersion(request.getHttpVersion());
                
                // Call the handler
                callHandler(request, response);
                bufferedChannel.flush();
            } while (request.isKeepAlive());
        } catch (IOException e) {
        	System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
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
    private void callHandler(HttpRequest request, HttpResponse response) throws IOException {
        RouteLookup route = routes.lookup(request);
        if (!route.isAvailable()) {
            response.sendError(HttpStatus.NOT_FOUND);
            return;
        }

        route.handle(request, response);
    }


}
