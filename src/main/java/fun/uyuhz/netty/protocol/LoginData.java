package fun.uyuhz.netty.protocol;

import fun.uyuhz.netty.annotation.ProtocolField;
import fun.uyuhz.netty.utils.ToStringFormatter;
import lombok.Data;

@Data
public class LoginData extends BaseData {

    @ProtocolField(length = 5, using = ToStringFormatter.class)
    private String token;
}
