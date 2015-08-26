package at.er.ytbattle.util;

public class LongWrapper {

    private long number;

    public LongWrapper(long value) {
        this.set(value);
    }

    public void set(long value) {
        this.number = value;
    }

    public void add(long delta) {
        number += delta;
    }

    public void sub(long delta) {
        number -= delta;
    }

    public long get() {
        return this.number;
    }
}
