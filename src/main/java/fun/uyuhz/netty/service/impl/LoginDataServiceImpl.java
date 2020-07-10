package fun.uyuhz.netty.service.impl;

import fun.uyuhz.netty.protocol.LoginData;
import fun.uyuhz.netty.protocol.Protocol;
import fun.uyuhz.netty.service.LoginDataService;
import fun.uyuhz.netty.utils.ProtocolUtil;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoginDataServiceImpl implements LoginDataService {

    @Override
    public void handle(ChannelHandlerContext ctx, Protocol protocol) throws Exception {
        LoginData loginData = ProtocolUtil.parsing(protocol, LoginData.class);
        log.info("设备{}登录，token:{}", loginData.getId(), loginData.getToken());
    }
}
