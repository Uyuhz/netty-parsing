package fun.uyuhz.netty.protocol;

import fun.uyuhz.netty.annotation.ProtocolField;
import fun.uyuhz.netty.utils.ToBCDFormatter;
import fun.uyuhz.netty.utils.ToStringFormatter;
import lombok.Data;

@Data
public class MsgData extends BaseData{

    @ProtocolField(length = 5, using = ToBCDFormatter.class)
    private String to_id;

    @ProtocolField(using = ToStringFormatter.class)
    private String msg;
}
