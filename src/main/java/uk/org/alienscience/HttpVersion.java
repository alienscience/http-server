package uk.org.alienscience;

import java.nio.charset.Charset;

public enum HttpVersion {
    HTTP_1_0("HTTP/1.0"),
    HTTP_1_1("HTTP/1.1");

    public final String text;

    HttpVersion(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    public byte[] toBytes(Charset charset) {
        return text.getBytes(charset);
    }
}