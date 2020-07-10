package fun.uyuhz.netty.server;

import fun.uyuhz.netty.enums.ProtocolType;
import fun.uyuhz.netty.protocol.Protocol;
import fun.uyuhz.netty.service.LoginDataService;
import fun.uyuhz.netty.service.MsgDataService;
import fun.uyuhz.netty.utils.ProtocolUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@ChannelHandler.Sharable
@Slf4j
@Component
public class NettyHandler extends SimpleChannelInboundHandler {

    @Autowired
    private LoginDataService loginDataService;
    @Autowired
    private MsgDataService msgDataService;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("连接建立, {}", ctx.channel().remoteAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("连接异常", cause);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object o) throws Exception {
        Protocol protocol = (Protocol) o;

        ProtocolType protocolType = ProtocolType.valueOf(protocol.getType());
        switch (protocolType) {
            case LOGIN:
                loginDataService.handle(ctx, protocol);
                break;
            case MSG:
                msgDataService.handle(ctx, protocol);
                break;
            default:
                throw new RuntimeException("不支持的协议类型");
        }
    }
}
