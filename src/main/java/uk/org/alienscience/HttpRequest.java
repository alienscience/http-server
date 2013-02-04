package uk.org.alienscience;

/**
 * TODO: Document
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
