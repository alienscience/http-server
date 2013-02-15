package uk.org.alienscience;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Class that outputs a HTTP header
 */
class HttpHeader {

    static final byte[] SPACE = convertToBytes(" ");
    static final byte[] CRNL = convertToBytes("\r\n");
    static final byte[] CONTENT_TYPE = convertToBytes("Content-Type: ");
    static final byte[] CONTENT_LENGTH = convertToBytes("Content-Length: ");

    private final BufferedChannel channel;
    private final Charset charset;

    HttpHeader(BufferedChannel channel) {
        this.channel = channel;
        charset = Charset.forName("ISO-8859-1");
    }

    // Convert the given string to a byte array encoded for a HTTP header
    static byte[] convertToBytes(String s) {
        Charset headerCharset = Charset.forName("ISO-8859-1");
        return s.getBytes(headerCharset);
    }

    // Convert the given string to a byte array encoded for a HTTP header
    static byte[] convertToBytes(String s, Charset charset) {
        return s.getBytes(charset);
    }

    // Put a header into the buffered channel
    void put(HttpVersion httpVersion,
             HttpStatus httpStatus,
             byte[] contentType,
             int contentLength ) throws IOException {
    	// TODO: output chunked if content length == -1
        byte[] contentLengthBytes = convertToBytes(String.valueOf(contentLength), charset);
        channel.put(httpVersion.toBytes()).put(SPACE);
        channel.put(httpStatus.code).put(SPACE);
        channel.put(httpStatus.message).put(CRNL);
        channel.put(CONTENT_TYPE).put(contentType).put(CRNL);
        channel.put(CONTENT_LENGTH).put(contentLengthBytes).put(CRNL);
        channel.put(CRNL);
    }
}
