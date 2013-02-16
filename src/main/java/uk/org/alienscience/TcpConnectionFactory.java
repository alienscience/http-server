package uk.org.alienscience;

import java.nio.channels.SocketChannel;

/**
 * Creates TcpConnection objects
 */
public interface TcpConnectionFactory {
    /**
     * Creates a runnable object that manages a new connection using the given channel.
         * @param channel A new channel that has just been accepted
         * @return A runnable to handle the channel
     */
    Runnable newConnection(SocketChannel channel);
}
