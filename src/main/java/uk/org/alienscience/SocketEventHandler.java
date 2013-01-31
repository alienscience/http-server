package uk.org.alienscience;

/**
 * Handles events on non-blocking socket.
 * The first call to a SocketEventHandler will always be @{see SocketEventHandler#startNonBlocking}
 */
public interface SocketEventHandler {

    /**
     * The HTTP header has been read and the socket has
     * been set to non-blocking
     */
    public void startNonBlocking();

    /**
     * The socket is available for reading
     */
    public void canRead();

    /**
     * The socket is available for writing
     */
    public void canWrite();
}
