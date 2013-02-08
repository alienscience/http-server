package uk.org.alienscience;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import static org.junit.Assert.assertEquals;

/**
 * Test of the HTTP Parser
 */
public class HttpParserTest {

    // Test the the given data parses correctly with the given parser
    public void assertSuccessfulParse(HttpParser parser, byte[] data) throws UnsupportedEncodingException {
        ByteBuffer buffer = ByteBuffer.wrap(data);
        ParseState result = parser.parse(buffer);
        assertEquals(ParseState.COMPLETE, result);
    }

    // Test the given data parses correctly and has the given path
    public void assertPath(byte[] data, String path) throws UnsupportedEncodingException {
        HttpRequest request = new HttpRequest();
        HttpParser parser = new HttpParser(request);
        assertSuccessfulParse(parser, data);
        assertEquals(path, request.path);
    }

    // Test that HTTP headers in a single buffer can be parsed
    @Test
    public void testSingleBuffer() throws UnsupportedEncodingException {
        assertPath("GET /\r\n\r\n".getBytes(), "/");
        assertPath("HEAD /some_path\r\n\r\n".getBytes(), "/some_path");

        assertPath(("GET /some-path HTTP/1.1\r\n" +
                    "User-Agent: curl/7.27.0\r\n" +
                    "Host: localhost:8090\r\n" +
                    "Accept: */*\r\n\r\n").getBytes(),
                "/some-path");
    }
}
