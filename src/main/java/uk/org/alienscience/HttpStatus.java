package uk.org.alienscience;

import java.nio.charset.Charset;

/**
 * HTTP status codes
 */
public enum HttpStatus {
    NOT_FOUND(404, "NOT FOUND"),
    METHOD_NOT_ALLOWED(405, "METHOD NOT ALLOWED");

    public final int code;
    public final String message;

    HttpStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }

    public byte[] toBytes(Charset charset) {
        return String.valueOf(code).getBytes(charset);
    }
}