package uk.org.alienscience;

/**
 * Information obtained from HTTP headers
 */
public class HttpRequest {
	enum Method {
		GET, PUT, POST, HEAD, DELETE;
	}
	enum Version {
		HTTP_1_0, HTTP_1_1;
	}
	
	Version httpVersion;
	boolean keepalive;
	Method method;
	String host;
	String path;
}
