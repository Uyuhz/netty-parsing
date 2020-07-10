package fun.uyuhz.netty.utils;

public class ToBCDFormatter extends ProtocolFieldFormatter {

    @Override
    public String format(Object value) {
        byte[] bytes = (byte[]) value;
        StringBuffer temp = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            temp.append((byte) ((bytes[i] & 0xf0) >>> 4));
            temp.append((byte) (bytes[i] & 0x0f));
        }
        return temp.toString().substring(0, 1).equalsIgnoreCase("0") ? temp.toString().substring(1) : temp.toString();
    }
}
