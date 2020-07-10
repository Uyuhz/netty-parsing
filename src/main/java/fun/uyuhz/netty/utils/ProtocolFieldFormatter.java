package fun.uyuhz.netty.utils;


public abstract class ProtocolFieldFormatter {

    public abstract Object format(Object value);

    public abstract static class None extends ProtocolFieldFormatter {
        public None() {
        }
    }
}
