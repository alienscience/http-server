package uk.org.alienscience;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Class that outputs a HTTP header
 */
class HttpHeader {

    private static Charset charset = Charset.forName("ISO-8859-1");
	
    // Convert the given string to a byte array encoded for a HTTP header
    static byte[] convertToBytes(String s) {
        return s.getBytes(charset);
    }

    static final byte[] SPACE = convertToBytes(" ");
    static final byte[] CRNL = convertToBytes("\r\n");
    static final byte[] DATE = convertToBytes("Date: ");
    static final byte[] CONTENT_TYPE = convertToBytes("Content-Type: ");
    static final byte[] CONTENT_LENGTH = convertToBytes("Content-Length: ");

    // TODO: support older date formats
    private static final SimpleDateFormat RFC1123 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
   
    static {
        RFC1123.setTimeZone(TimeZone.getTimeZone("GMT"));
    }
    
    // This is updated by a timer
    public static volatile byte[] date;
    
    private final BufferedChannel channel;

    HttpHeader(BufferedChannel channel) {
        this.channel = channel;
    }

    // Put a header into the buffered channel
    void put(HttpVersion httpVersion,
             HttpStatus httpStatus,
             byte[] contentType,
             int contentLength ) throws IOException {
    	// TODO: output chunked if content length == -1
    	// TODO: date header
        byte[] contentLengthBytes = convertToBytes(String.valueOf(contentLength));
        channel.put(httpVersion.toBytes()).put(SPACE);
        channel.put(httpStatus.code).put(SPACE);
        channel.put(httpStatus.message).put(CRNL);
        channel.put(DATE).put(date).put(CRNL);
        channel.put(CONTENT_TYPE).put(contentType).put(CRNL);
        channel.put(CONTENT_LENGTH).put(contentLengthBytes).put(CRNL);
        channel.put(CRNL);
    }
   
    /**
     * Update the date - this is assumed to be called by a Timer
     */
    public static void updateDate() {
       String dateString = RFC1123.format(new Date());
       date = dateString.getBytes(charset);
    }
}
