package uk.org.alienscience;

import java.nio.charset.Charset;

/**
 * HTTP status codes
 */
public enum HttpStatus {
    NOT_FOUND(404, "NOT FOUND"),
    METHOD_NOT_ALLOWED(405, "METHOD NOT ALLOWED");

    public final byte[] code;
    public final byte[] message;

    HttpStatus(int code, String message) {
        Charset charset = Charset.forName("ISO-8859-1");
        this.code = String.valueOf(code).getBytes(charset);
        this.message = message.getBytes(charset);
    }

}