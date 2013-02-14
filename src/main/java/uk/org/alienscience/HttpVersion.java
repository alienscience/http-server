package uk.org.alienscience;

import java.nio.charset.Charset;

public enum HttpVersion {
    HTTP_1_0("HTTP/1.0"),
    HTTP_1_1("HTTP/1.1");

    public final byte[] bytes;

    HttpVersion(String text) {
        Charset charset = Charset.forName("ISO-8859-1");
        bytes = text.getBytes(charset);
    }

    public byte[] toBytes() {
        return bytes;
    }
}