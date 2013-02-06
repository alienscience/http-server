package uk.org.alienscience;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

/**
 * A runnable object that handles a single HttpConnection
 */
public class HttpConnection implements Runnable {
    private static final Charset charset = Charset.forName("UTF-8");

    private final SocketChannel channel;
    private final HttpRoutes routes;

    public HttpConnection(SocketChannel channel, HttpRoutes routes) {
        this.channel = channel;
        this.routes = routes;
    }

    @Override
    public void run() {
        HttpRequest request = parseHeader();

        // TODO: Route lookup
        // TODO: If blocking, setup request/response and call handler
        // TODO: If non-blocking, send to non-blocking thread


        // TODO: move this into HttpResponse
        CharsetEncoder encoder = charset.newEncoder();

        try {
            System.out.println("Got connection");
            for (int i = 0; i < 10; i++) {
                channel.write(encoder.encode(CharBuffer.wrap(request.path)));
            }
            System.out.println("Wrote buffer");
        } catch (CharacterCodingException e) {
            System.err.println("CharacterCodingException:" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Parse a HTTP header
    private HttpRequest parseHeader() {
        HttpRequest request = new HttpRequest();
        HttpParser parser = new HttpParser(request);
        ByteBuffer buffer = ByteBuffer.allocate(4096);
        buffer.clear();

        try {
            while (channel.read(buffer) != -1) {
                bufferStats(buffer);
                buffer.flip();
                ParseState state = parser.parse(buffer);
                System.out.println(state);
                if (state != ParseState.INCOMPLETE) {
                    break;
                }
                bufferStats(buffer);
            }
            return request;
        } catch (Exception e) {
            return null;
        }

    }

    // Temporary method
    private void bufferStats(ByteBuffer buffer) {
        System.out.println(buffer.position() + ", " + buffer.limit());
    }
}
