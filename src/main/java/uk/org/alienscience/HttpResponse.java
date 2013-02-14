package uk.org.alienscience;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;

/**
 * An implementation of a HttpResponse
 * TODO: make similar to javax.servlet.http.HttpServletResponse
 */
public class HttpResponse {


    private final SocketChannel channel;
    private final HttpHeader httpHeader;

    // Mutable variables so that the response can be reused
    private HttpVersion httpVersion;
    private int contentLength;
    private String contentType;
    private HttpStatus status;

    // The response is built into a bytebuffer
    ByteBuffer outputBuffer;

    public HttpResponse(SocketChannel channel, int bufferSize) {
        this.channel = channel;
        this.outputBuffer = ByteBuffer.allocate(bufferSize);
        this.httpHeader = new HttpHeader(channel, outputBuffer);
    }

    /**
     * Set the HTTP version of the response
     * @param version The HTTP version of the response
     */
    public void setHttpVersion(HttpVersion version) {
        this.httpVersion = version;
    }

    /**
     * TODO: implement this for both HTTP 1.0 and HTTP 1.1
     * TODO: use static strings in format call
     * TODO: connect status codes with messages
     * @param status The HTTP status code to return to the client
     */
    public void sendError(HttpStatus status) {
        String message = status.message;
        int contentLength = message.length();
        StringBuffer response = new StringBuffer(httpVersion.toString());

        response.append(" ").append(status.code).append(" ").append(message).append("\r\n")
                .append("Content-Type: text/plain\r\n" )
                .append("Content-Length: ").append(contentLength).append("\r\n");
        write(response.toString());
    }

    private void writeWithHeader(String s) {
        httpHeader.put(httpVersion, status, contentType, contentLength);
        // TODO: set encoding
        ByteBuffer body = bodyEncoder.encode(CharBuffer.wrap(s));
        outputBuffer.put(body);
        // TODO: see if this has to be looped
        outputBuffer.flip();
        channel.write(outputBuffer);
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
