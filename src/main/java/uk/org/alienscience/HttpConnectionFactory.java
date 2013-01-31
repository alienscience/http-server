package uk.org.alienscience;

import java.nio.channels.SocketChannel;

/**
 * TODO: Document
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
