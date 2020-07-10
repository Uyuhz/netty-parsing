package fun.uyuhz.netty.utils;

public class ToStringFormatter extends ProtocolFieldFormatter {

    @Override
    public String format(Object value) {
        return new String((byte[]) value);
    }
}
