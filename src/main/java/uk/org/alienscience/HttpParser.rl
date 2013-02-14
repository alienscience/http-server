
package uk.org.alienscience;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

class HttpParser {

    //------------ Start of generated code ------------------------------------
%%{
    machine http_request;
    
    alphtype int;

    # Actions to capture parts of the request line
    action method_is_get {
        request.setMethod(HttpRequest.Method.GET);
    }

    action method_is_put { 
        request.setMethod(HttpRequest.Method.PUT);
    }

    action method_is_post { 
        request.setMethod(HttpRequest.Method.POST);
    }

    action method_is_head { 
        request.setMethod(HttpRequest.Method.HEAD);
    }

    action method_is_delete { 
        request.setMethod(HttpRequest.Method.DELETE);
    }

    action method_is_unsupported {
        ret = ParseState.METHOD_IS_UNSUPPORTED;
        fbreak;
    }

    get    = "GET"i    %method_is_get;
    put    = "PUT"i    %method_is_put;
    post   = "POST"i   %method_is_post;
    head   = "HEAD"i   %method_is_head;
    delete = "DELETE"i %method_is_delete;

    method = get | put | post | head | delete  $!method_is_unsupported;

    action mark_path { 
        markStart = p;
    }

    action save_path { 
        request.setPath(saveString(data, p));
    }

    # URI, rfc2396
    host = alnum ( alnum | "."  | "-" )* ;
    port = digit+ ;
    hostport = host ( ":" port )? ;
    mark = "-" | "_" | "." | "!" | "~" | "*" | "'" | "(" | ")" ;
    unreserved = alnum | mark ; 
    reserved = ";" | "/" | "?" | ":" | "@" | "&" | "=" | "+" | "$" | "," ;
    escaped = "%" xdigit xdigit ;
    pchar = ( unreserved | escaped | ":" | "@" | "&" | "=" | "+" | "$" | "," ) ;
    abs_path = ( "/" ( pchar | "/")* ) >mark_path %save_path;
    uric = reserved | unreserved | escaped ;
    query = uric* ;
    abs_uri = "http://" hostport abs_path ("?" query )? ;
    uri = abs_uri | abs_path ;

    # HTTP request line, section 5.1
    # Allow use of netcat by using \n as a delimiter
    crlf = ( "\n" | "\r\n" );
    http_1_0 = "HTTP/1.0" %{ 
        request.setHttpVersion(HttpVersion.HTTP_1_0);
    };
    http_1_1 = "HTTP/1.1" %{ 
        request.setHttpVersion(HttpVersion.HTTP_1_1);
    };
    http_version = ( http_1_0 | http_1_1 );
    request_line = method space uri space? http_version? crlf;

    # Actions to capture header fields 
    action mark_host { markStart = p; }
    action save_host { request.setHost(saveString(data, p)); }

    action mark_close {
        request.setKeepAlive(false);
    }

    action mark_keepalive {
        request.setKeepAlive(true);
    }

    # Request header fields, section 5.3 
    hostname = hostport >mark_host %save_host;
    hostfield = "Host:" space+ hostname crlf;
    keepalive = "Keep-Alive" %mark_keepalive;
    close = "close" %mark_close;
    connection = "Connection:" space+ ( keepalive | close ) crlf;
    ignored_header = (any - crlf)+ crlf;
    header_field = ( hostfield | connection | ignored_header );

    # An entire HTTP request 
    request_end = crlf;
    http_request := request_line header_field* request_end;

    write data;
}%%

    //------------ End of generated code --------------------------------------

    private HttpRequest request;

    // Member variables required by Ragel
    private int cs;   // Current state

    // Index used to mark the start of interesting byte ranges. A value of -1 indicates no mark
    // has been set
    private int markStart;

    // Partially built marked strings
    StringBuilder markedString;

    /**
     * Reset the parser and associate it with the given HttpRequest
     */
    public void reset(HttpRequest request) {
        this.request = request;
        this.markStart = -1;
        this.markedString = new StringBuilder();

        // --- Generated code ---
        %%write init;
    }

    // Extract a string starting at markStart
    private String extractString(byte[] data, int to) throws UnsupportedEncodingException {
        if (markStart == -1) return "";
        return new String(data, markStart, to - markStart, "ISO-8859-1");
    }

    // Save a marked string for the next round of parsing
    private void saveMark(byte[] data, int to) throws UnsupportedEncodingException {
        if (markStart >= 0) {
            markedString.append(extractString(data, to));
            markStart = 0;
        }
    }

    // Save a marked string for external use
    private String saveString(byte[] data, int to) throws UnsupportedEncodingException {
        markedString.append(extractString(data, to));
        String ret = markedString.toString();
        markedString.delete(0, markedString.length());
        markStart = -1;
        return ret;
    }

    /**
     * Parses a HTTP header
     * @param buffer The buffer to read from. This should be in a state that is ready for reading. The buffer will
     *        be left in a state where it can be written to.
     * @return Indicates if the parsing is complete, incomplete or has had an error.
     */
    ParseState parse(ByteBuffer buffer) throws UnsupportedEncodingException {

        ParseState ret = ParseState.INCOMPLETE;

        int eof = -1;     // EOF code - required by Ragel

        // Initialise the current position for the Ragel parser
        int offset = buffer.arrayOffset();
        int p = buffer.position() + offset;
        assert(p == offset);

        // The end of the data in the buffer
        int pe = buffer.limit() + offset;

        // The block of data to parse
        final byte[] data = buffer.array();

        //--- Start of generated code ---
        
        %%write exec;

        if (cs == %%{ write error; }%% ) {
            System.err.println("Failed to parse p=" + p);
            return ParseState.ERROR;
        }

        if (cs < %%{ write first_final; }%% ) {
            // Get the buffer ready for writing
            saveMark(data, p);
            buffer.position(p);
            buffer.compact();
            return ret;
        }

        return ParseState.COMPLETE;
    }

}

