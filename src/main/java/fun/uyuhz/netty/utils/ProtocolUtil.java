package fun.uyuhz.netty.utils;

import fun.uyuhz.netty.annotation.ProtocolField;
import fun.uyuhz.netty.protocol.Protocol;
import fun.uyuhz.netty.protocol.Resolvable;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class ProtocolUtil {

    public static <T extends Resolvable> T parsing(Protocol protocol, Class<T> clazz) throws Exception {
        ByteBuf byteBuf = Unpooled.copiedBuffer(protocol.getBody());
        T t = parsing(byteBuf, clazz);
        BeanUtils.copyProperties(protocol, t);
        return t;
    }

    public static <T extends Resolvable> T parsing(ByteBuf byteBuf, Class<T> clazz) throws Exception {
        T t = clazz.newInstance();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            ProtocolField protocolField = field.getAnnotation(ProtocolField.class);
            if (protocolField == null) {
                continue;
            }

            Object value = null;
            int length = protocolField.length();
            String lengthField = protocolField.lengthField();
            Class<? extends ProtocolFieldFormatter> formatter = protocolField.using();

            boolean hasLength = length != 0;
            boolean hasLengthField = !StringUtils.isEmpty(lengthField);

            // 如果没有指定长度或者长度字段，则直接读readableBytes
            if (!hasLength && !hasLengthField) {
                length = byteBuf.readableBytes();
                value = readBytes(byteBuf, length);
            } else {
                // 指定length的情况
                if (hasLength) {
                    // 如果使用了格式化类，只读byte[]类型
                    if (formatter != ProtocolFieldFormatter.None.class) {
                        value = readBytes(byteBuf, length);
                    } else {
                        switch (length) {
                            case 1:
                                value = byteBuf.readByte();
                                break;
                            case 2:
                                value = byteBuf.readShort();
                                break;
                            case 4:
                                value = byteBuf.readInt();
                                break;
                            case 8:
                                value = byteBuf.readLong();
                                break;
                            default:
                                value = readBytes(byteBuf, length);
                        }
                    }
                }

                // 指定lengthField的情况
                if (!hasLength && hasLengthField) {
                    Method getMethod = getGetterMethod(clazz, clazz.getDeclaredField(lengthField));
                    length = (int) getMethod.invoke(t);
                    if (length != 0) {
                        value = readBytes(byteBuf, length);
                    }
                }
            }

            // 如果指定了格式化类，则进行格式化
            if (formatter != ProtocolFieldFormatter.None.class) {
                Method method = formatter.getMethod("format", Object.class);
                value = method.invoke(formatter.newInstance(), value);
            }

            Method setMethod = getSetterMethod(clazz, field);
            setMethod.invoke(t, value);
        }

        return t;
    }

    private static byte[] readBytes(ByteBuf byteBuf, int length) {
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);
        return bytes;
    }

    private static Method getSetterMethod(Class clazz, Field field) throws NoSuchMethodException {
        String fieldName = field.getName();
        String methodName = "set" + fieldName.toUpperCase().substring(0,1) + fieldName.substring(1);
        return clazz.getMethod(methodName, field.getType());
    }

    private static Method getGetterMethod(Class clazz, Field field) throws NoSuchMethodException {
        String fieldName = field.getName();
        String methodName = "get" + fieldName.toUpperCase().substring(0,1) + fieldName.substring(1);
        return clazz.getMethod(methodName);
    }
}
