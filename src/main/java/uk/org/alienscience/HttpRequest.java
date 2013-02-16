package uk.org.alienscience;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Information obtained from HTTP headers
 */
public class HttpRequest {
    enum Method {
        GET, PUT, POST, HEAD, DELETE;
    }

    private HttpVersion httpVersion;
    private boolean keepAlive;
    private Method method;
    private byte[] host;
    private byte[] path;
    private Map<String,byte[]> pathParameters;

    public HttpRequest() {
        pathParameters = new HashMap<String,byte[]>();
    }
    
    public void reuse() {
        keepAlive = true;
        pathParameters.clear();
    }
    
    public byte[] getPath() {
        return path;
    }

    public void setPath(byte[] path) {
        this.path = path;
    }

    public HttpVersion getHttpVersion() {
        return httpVersion;
    }

    public void setHttpVersion(HttpVersion httpVersion) {
        this.httpVersion = httpVersion;
    }

    public boolean isKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public byte[] getHost() {
        return host;
    }

    public void setHost(byte[] host) {
        this.host = host;
    }

    /**
     * Set a parameter obtained from the path
     * @param name The name of the parameter
     * @param start The start index in the path
     * @param end The end index (not inclusive) in the path
     */
    public void setPathParameter(String name, int start, int end) {
        pathParameters.put(name, Arrays.copyOfRange(path, start, end));
    }

}
