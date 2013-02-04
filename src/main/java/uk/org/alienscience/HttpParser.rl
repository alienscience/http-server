
package uk.org.alienscience;

import java.io.UnsupportedEncodingException;

class HttpParser {

    //------------ Start of generated code ------------------------------------
%%{
    machine http_request;
    
    alphtype byte;

    # Actions to capture parts of the request line
    action method_is_get { 
        request.method = HttpRequest.Method.GET; 
    }

    action method_is_put { 
        request.method = HttpRequest.Method.PUT; 
    }

    action method_is_post { 
        request.method = HttpRequest.Method.POST; 
    }

    action method_is_head { 
        request.method = HttpRequest.Method.HEAD; 
    }

    action method_is_delete { 
        request.method = HttpRequest.Method.DELETE; 
    }

    action method_is_unsupported { 
        return ParseState.METHOD_IS_UNSUPPORTED;
    }

    get    = "GET"i    %method_is_get;
    put    = "PUT"i    %method_is_put;
    post   = "POST"i   %method_is_post;
    head   = "HEAD"i   %method_is_head;
    delete = "DELETE"i %method_is_delete;

    method = get | put | post | head | delete  $!method_is_unsupported;

    action mark_path { 
        markPath = p;
    }

    action save_path { 
        request.path = extractString(markPath,p);
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
    abs_path = ( "/" pchar ( pchar | "/")* ) >mark_path %save_path;
    uric = reserved | unreserved | escaped ;
    query = uric* ;
    abs_uri = "http://" hostport abs_path ("?" query )? ;
    uri = abs_uri | abs_path ;

    # HTTP request line, section 5.1
    # Allow use of netcat by using \n as a delimiter
    crlf = ( "\n" | "\r\n" );
    http_1_0 = "HTTP/1.0" %{ 
        request.httpVersion = HttpRequest.Version.HTTP_1_0; 
    };
    http_1_1 = "HTTP/1.1" %{ 
         request.httpVersion = HttpRequest.Version.HTTP_1_1; 
    };
    http_version = ( http_1_0 | http_1_1 );
    request_line = method space uri space? http_version? crlf;

    # Actions to capture header fields 
    action mark_host { markHost = p; }
    action save_host { request.host = extractString(markHost, p); }

    action mark_close { 
        request.keepalive = false;
    }

    action mark_keepalive {
        request.keepalive = true;
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

    private final byte data[];
    private final HttpRequest request;

    // Member variables required by Ragel
    private int cs;   // Current state
    private int p;    // Data pointer
    private int pe;   // Pointer to end of data

    // Indices used to mark the start of interesting byte ranges
    private int markHost;
    private int markPath;
 

    HttpParser(byte data[], HttpRequest request) {
        // TODO: consider buffer refills
        this.data = data;
        this.request = request;
        this.pe = data.length;
    }

    private String extractString(int from, int to) throws UnsupportedEncodingException {
        return new String(data, from, to - from, "ISO-8859-1");
    }

    void start() {
        %%write init;
    }

    /**
     * Indicates if the parsing is complete, incomplete or has had an error
     */
    ParseState parse() throws UnsupportedEncodingException {
        int eof;  // EOF code         - required by ragel
        int ts;   // TODO: see if this is needed (section 6.3)
        int te;   // TODO: see if this is needed (section 6.3)

        int path_start;   // The start of the path in the URI

        //--- Start of generated code ---
        
        %%write exec;

        if (cs == %%{ write error; }%% ) {
            return ParseState.ERROR;
        }

        if (cs < %%{ write first_final; }%% ) {
            return ParseState.INCOMPLETE;
        }

        return ParseState.COMPLETE;
    }

}

