package uk.org.alienscience;

import java.io.IOException;
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
        // TODO: The following
        // - Parse header
        // - Route lookup
        // - If blocking, setup request/response and call handler
        // - If non-blocking, send to non-blocking thread


        // TODO: move this into HttpResponse
        CharsetEncoder encoder = charset.newEncoder();

        try {
            System.out.println("Got connection");
            for (int i = 0; i < 10; i++) {
                channel.write(encoder.encode(CharBuffer.wrap("Hello world\n")));
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
}
