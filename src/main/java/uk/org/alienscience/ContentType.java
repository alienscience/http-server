package uk.org.alienscience;

import java.nio.charset.Charset;

/**
 * MIME content types
 */
public enum ContentType {
    TEXT("text/plain"),
    HTML("text/html");

    private byte[] bytes;

    ContentType(String identifier) {
        Charset charset = Charset.forName("ISO-8859-1");
        this.bytes = identifier.getBytes(charset);
    }

    /**
     * Get the MIME type as a byte array for including in a HTTP header
     * @return The MIME type as a byte array
     */
    public byte[] toBytes() {
        return bytes;
    }
}
