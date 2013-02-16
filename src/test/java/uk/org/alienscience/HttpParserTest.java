package uk.org.alienscience;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    // Test the the given data parses incomplete with the given parser
    public void assertIncompleteParse(HttpParser parser, byte[] data) throws UnsupportedEncodingException {
        ByteBuffer buffer = ByteBuffer.wrap(data);
        ParseState result = parser.parse(buffer);
        assertEquals(ParseState.INCOMPLETE, result);
    }

    // Test the given data parses correctly and has the given path
    public void assertPath(byte[] data, String path) throws UnsupportedEncodingException {
        HttpRequest request = new HttpRequest();
        HttpParser parser = new HttpParser();
        parser.reset(request);
        assertSuccessfulParse(parser, data);
        assertTrue(Arrays.equals(path.getBytes(), request.getPath()));
    }


    // Parse data in chunks
    public void assertSuccessfulParseInChunks(byte[] wholeData, int bufferSize, String path)
        throws UnsupportedEncodingException {
        int numBlocks = (int) Math.ceil(wholeData.length / (double) bufferSize);
        HttpRequest request = new HttpRequest();
        HttpParser parser = new HttpParser();
        parser.reset(request);

        // Loop through the incomplete parses
        for (int i = 0; i < numBlocks - 1; i++) {
            int start = bufferSize *i;
            byte[] buffer = Arrays.copyOfRange(wholeData, start, start + bufferSize );
            assertIncompleteParse(parser, buffer);
        }

        // The final call to the parser should be complete
        int start = bufferSize * (numBlocks - 1);
        byte[] buffer = Arrays.copyOfRange(wholeData, start, wholeData.length );
        assertSuccessfulParse(parser, buffer);
    }

    // Test that HTTP headers in a single buffer write can be parsed
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


    // Test that HTTP headers spread across multiple buffer writes can be parsed
    @Test
    public void testMultipleBuffer() throws UnsupportedEncodingException {
       assertSuccessfulParseInChunks(
               "GET /some/long/path/that/goes/on/\r\n\r\n".getBytes(),
               8, "/some/long/path/that/goes/on/");

        assertSuccessfulParseInChunks(("GET /some-path HTTP/1.1\r\n" +
                "User-Agent: curl/7.27.0\r\n" +
                "Host: localhost:8090\r\n" +
                "Accept: */*\r\n\r\n").getBytes(),
                32,
                "/some-path");
    }

}
