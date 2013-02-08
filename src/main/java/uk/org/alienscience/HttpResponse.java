package uk.org.alienscience;

import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.CharsetEncoder;

/**
 * An implementation of a HttpResponse
 * TODO: implement javax.servlet.http.HttpServletResponse
 */
public class HttpResponse {
    public static final int SC_NOT_FOUND = 404;
    public static final int SC_METHOD_NOT_ALLOWED = 405;

    private final SocketChannel channel;
    private final CharsetEncoder encoder;

    public HttpResponse(SocketChannel channel, CharsetEncoder encoder) {
        this.channel = channel;
        this.encoder = encoder;
    }

    /**
     * TODO: implement this for both HTTP 1.0 and HTTP 1.1
     * TODO: use static strings in format call
     * TODO: connect status codes with messages
     * @param status The HTTP status code to return to the client
     */
    public void sendError(int status) {
        String statusMessage = "Oh noes";
        int contentLength = statusMessage.length();

        write(String.format(
                "HTTP/1.1 %d %s\r\n" +
                "Content-Type: text/plain\r\n" +
                "Content-Length: %d\r\n\r\n",
                status,
                statusMessage,
                contentLength));
    }

    public void write(String s) {
        try {
            channel.write(encoder.encode(CharBuffer.wrap(s)));
        } catch (Exception e) {
            // We may have already sent the header so leave an empty response.
        }
    }
}
