package fun.uyuhz.netty.protocol;

import lombok.Data;

@Data
public class BaseData implements Resolvable {

    private int type;

    private String id;

    private int length;
}
