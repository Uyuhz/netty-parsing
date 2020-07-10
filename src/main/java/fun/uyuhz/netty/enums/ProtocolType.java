package fun.uyuhz.netty.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ProtocolType {

    LOGIN(1),

    MSG(2);

    private int value;

    public static ProtocolType valueOf(int value) {
        for (ProtocolType protocolType : ProtocolType.values()) {
            if (protocolType.getValue() == value) {
                return protocolType;
            }
        }
        return null;
    }
}
