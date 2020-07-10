package fun.uyuhz.netty.server;

import fun.uyuhz.netty.protocol.Protocol;
import fun.uyuhz.netty.utils.ProtocolUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class NettyDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ch, ByteBuf byteBuf, List<Object> list) throws Exception {

        String msg = ByteBufUtil.hexDump(byteBuf);
        log.info("收到{}的报文：{}", ch.channel().remoteAddress(), "cccc" + msg + "cccc");

        Protocol protocol = ProtocolUtil.parsing(byteBuf, Protocol.class);
//        int length = byteBuf.readShort();
//
//        byte[] body = new byte[length];
//        byteBuf.readBytes(body);
//
//        Protocol protocol = new Protocol();
//        protocol.setLength(length);
//        protocol.setBody(body);
        list.add(protocol);
    }
}
