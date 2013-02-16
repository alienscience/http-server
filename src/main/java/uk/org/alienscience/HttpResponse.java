package uk.org.alienscience;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * An implementation of a HttpResponse
 * TODO: make similar to javax.servlet.http.HttpServletResponse
 */
public class HttpResponse {

    private final BufferedChannel channel;
    private final HttpHeader httpHeader;
    private final Charset charset;

    // Mutable variables so that the response can be reused
    private HttpVersion httpVersion;
    private int contentLength;
    private byte[] contentType;
    private HttpStatus status;
    boolean haveWrittenHeader;

    public HttpResponse(BufferedChannel channel) {
        this.channel = channel;
        this.httpHeader = new HttpHeader(channel);
        this.charset = Charset.forName("UTF-8");
    }

    // Called to reuse the response
    void reuse() {
    	status = HttpStatus.OK;
    	contentType = ContentType.HTML.toBytes();
    	contentLength = -1;
        haveWrittenHeader = false;
    }
    
    /**
     * Set the HTTP version of the response
     * @param version The HTTP version of the response
     */
    public void setHttpVersion(HttpVersion version) {
        this.httpVersion = version;
    }

    /**
     * Send a HTTP error to the client
     * @param status The HTTP status code to return to the client
     */
    public void sendError(HttpStatus status) throws IOException {
        byte[] message = status.message;
        setContentLength(message.length);
        setStatus(status);
        setContentType(ContentType.TEXT);
        write(message);
    }

    public void write(byte[] data) throws IOException {
        if (!haveWrittenHeader) {
            httpHeader.put(httpVersion, status, contentType, contentLength);
            haveWrittenHeader = true;
        }
        channel.put(data);
    }

    public void body(String s) throws IOException {
    	byte[] data = s.getBytes(charset);
    	setContentLength(data.length);
        write(data);
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType.toBytes();
    }
}
