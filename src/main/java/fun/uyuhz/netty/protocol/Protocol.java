package fun.uyuhz.netty.protocol;

import fun.uyuhz.netty.annotation.ProtocolDelimiter;
import fun.uyuhz.netty.annotation.ProtocolField;
import fun.uyuhz.netty.utils.ToBCDFormatter;
import lombok.Data;

/**
 * 消息头 2byte | 消息类型 1byte |    设备id 5byte   |  消息长度 2byte | 消息体 Nbyte | 消息尾 2byte
 *   0xCC 0xCC |     0x01      |   0x1234567890   |    XXXXXXXXX   |  0xCC 0xCC
 *
 * ***************************************************
 *
 *   01.登录报文
 *   |                消息体             |
 *   |            token 5byte           |
 *
 *   示例登录报文：CCCC 01 1234567890 05 48454C4C4F CCCC
 *   token : HELLO
 * *************************************************
 *
 *   02.消息报文
 *   |                消息体             |
 *   |     to_id 5byte   |   msg Nbyte  |
 *
 *   示例消息报文：CCCC 02 1234567890 0A 1234567891 48454C4C4F CCCC
 *   to_id : 1234567891 , msg : HELLO
 */
@Data
@ProtocolDelimiter({(byte) 0xcc, (byte) 0xcc})
public class Protocol implements Resolvable{

    @ProtocolField(length = 1)
    private int type;

    @ProtocolField(length = 5, using = ToBCDFormatter.class)
    private String id;

    @ProtocolField(length = 1)
    private int length;

    @ProtocolField(lengthField = "length")
    private byte[] body;
}
