package fun.uyuhz.netty.service.impl;

import fun.uyuhz.netty.protocol.MsgData;
import fun.uyuhz.netty.protocol.Protocol;
import fun.uyuhz.netty.service.MsgDataService;
import fun.uyuhz.netty.utils.ProtocolUtil;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MsgDataServiceImpl implements MsgDataService {

    @Override
    public void handle(ChannelHandlerContext ctx, Protocol protocol) throws Exception {
        MsgData msgData = ProtocolUtil.parsing(protocol, MsgData.class);
        log.info("设备{}发送消息给设备{}，消息内容:{}", msgData.getId(), msgData.getTo_id(), msgData.getMsg());
    }
}
