package fun.uyuhz.netty.annotation;

import fun.uyuhz.netty.utils.ProtocolFieldFormatter;
import fun.uyuhz.netty.utils.ProtocolFieldFormatter.None;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ProtocolField {

    int length() default 0;

    String lengthField() default "";

    Class<? extends ProtocolFieldFormatter> using() default None.class;
}


