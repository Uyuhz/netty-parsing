package fun.uyuhz.netty.service;

import fun.uyuhz.netty.protocol.Protocol;
import io.netty.channel.ChannelHandlerContext;

public interface ProtocolService {

    void handle(ChannelHandlerContext ctx, Protocol protocol) throws Exception;
}
