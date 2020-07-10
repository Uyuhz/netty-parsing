package fun.uyuhz.netty.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ProtocolDelimiter {

    byte[] value() default {};

    byte[] headDelimiter() default {};

    byte[] tailDelimiter() default {};

}
