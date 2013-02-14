package uk.org.alienscience;

import javax.annotation.concurrent.NotThreadSafe;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Combines a ByteBuffer with a SocketChannel to provide blocking buffered writes
 */
@NotThreadSafe
public class BufferedChannel {

    private final SocketChannel channel;
    private final ByteBuffer buffer;

    public BufferedChannel(SocketChannel channel, ByteBuffer buffer) {
        this.channel = channel;
        this.buffer = buffer;
    }

    /**
     * Adds the given bytes to the buffer writing the channel on overflow
     * @param data The data to put in the buffer
     */
    public BufferedChannel put(byte[] data) throws IOException {
        int remaining = buffer.remaining();

        if (remaining >= data.length) {
            buffer.put(data);
        } else {
            buffer.put(data, 0, remaining);
            buffer.flip();
            channel.write(buffer);
            buffer.reset();
            buffer.put(data, remaining, data.length - remaining);
        }
        return this;
    }

    public void flush() throws IOException {
        buffer.flip();
        channel.write(buffer);
        buffer.reset();
    }
}
