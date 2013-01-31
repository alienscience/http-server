package uk.org.alienscience;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;

/**
 * A threaded TCPServer
 */
public class TcpServer {
    private final int port;
    private final TcpConnectionFactory connectionFactory;
    private final ExecutorService threadPool;

    public TcpServer(int port, TcpConnectionFactory connectionFactory, ExecutorService threadPool) {
        this.port = port;
        this.connectionFactory = connectionFactory;
        this.threadPool = threadPool;
    }

    public void start() throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(InetAddress.getByName("localhost"), port);
        serverChannel.socket().bind(address);

        while (true) {
            SocketChannel channel = serverChannel.accept();
            threadPool.submit(connectionFactory.newConnection(channel));
        }
    }
}
