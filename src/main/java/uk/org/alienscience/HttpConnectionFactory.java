package uk.org.alienscience;

import java.nio.channels.SocketChannel;

/**
 * Creates new connection objects when a new connection is made
 */
public class HttpConnectionFactory implements TcpConnectionFactory {
    private final HttpRoutes routes;

    public HttpConnectionFactory(HttpRoutes routes) {
        this.routes = routes;
    }

    @Override
    public Runnable newConnection(SocketChannel channel) {
        return new HttpConnection(channel, routes);
    }
}
