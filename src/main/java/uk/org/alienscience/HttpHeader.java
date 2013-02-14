package uk.org.alienscience;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * Class that outputs a HTTP header
 */
class HttpHeader {

    static final byte[] SPACE = convertToBytes(" ");
    static final byte[] CRNL = convertToBytes("\r\n");
    static final byte[] CONTENT_TYPE = convertToBytes("Content-Type: ");
    static final byte[] CONTENT_LENGTH = convertToBytes("Content-Length: ");

    private final SocketChannel channel;
    private final ByteBuffer buffer;
    private final Charset charset;

    HttpHeader(SocketChannel channel, ByteBuffer buffer) {
        this.channel = channel;
        this.buffer = buffer;
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

    // Put a header into the given byte buffer
    // TODO: output on buffer overflow
    void put(HttpVersion httpVersion,
             HttpStatus httpStatus,
             String contentType,
             int contentLength ) {
        byte[] version = convertToBytes(httpVersion.toString(), charset);
        byte[] statusCode = convertToBytes(String.valueOf(httpStatus.code), charset);
        byte[] statusMessage = convertToBytes(httpStatus.toString(), charset);
        byte[] contentTypeBytes = convertToBytes(contentType, charset);
        byte[] contentLengthBytes = convertToBytes(String.valueOf(contentLength), charset);
        buffer.put(version).put(SPACE);
        buffer.put(statusCode).put(SPACE);
        buffer.put(statusMessage).put(CRNL);
        buffer.put(CONTENT_TYPE).put(contentTypeBytes).put(CRNL);
        buffer.put(CONTENT_LENGTH).put(contentLengthBytes).put(CRNL);
        buffer.put(CRNL);
    }
}
