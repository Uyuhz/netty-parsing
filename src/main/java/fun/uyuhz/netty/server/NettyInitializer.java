package fun.uyuhz.netty.server;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NettyInitializer extends ChannelInitializer<SocketChannel> {

    private static final byte[] DELIMITER = {(byte) 0xcc, (byte) 0xcc};

    @Autowired
    private NettyHandler nettyHandler;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
         socketChannel.pipeline()
                 .addLast(new DelimiterBasedFrameDecoder(100,
                         Unpooled.copiedBuffer(DELIMITER),
                         Unpooled.copiedBuffer(DELIMITER, DELIMITER)))
                 .addLast(new NettyDecoder())
                 .addLast(nettyHandler);
    }
}
