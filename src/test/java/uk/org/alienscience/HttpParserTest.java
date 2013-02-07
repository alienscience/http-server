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

    // Test the given data parses correctly
    public void assertFullParse(byte[] data) throws UnsupportedEncodingException {
        HttpRequest request = new HttpRequest();
        HttpParser parser = new HttpParser(request);
        assertSuccessfulParse(parser, data);
    }

    // Test that HTTP headers in a single buffer can be parsed
    @Test
    public void testSingleBuffer() throws UnsupportedEncodingException {
        assertFullParse("GET /\r\n\r\n".getBytes());
        assertFullParse("HEAD /some_path\r\n\r\n".getBytes());
    }
}
