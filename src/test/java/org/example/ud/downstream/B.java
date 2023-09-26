package org.example.ud.downstream;

import org.example.ud.upstream.A;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class B {

    private final int valueUpStream;
    private final A a;

    private B(int valueUpStream, A a) {
        this.valueUpStream = valueUpStream;
        this.a = a;
    }

    @Contract(value = "_, _ -> new", pure = true)
    public static @NotNull B of(int valueUpStream, A a) {
        return new B(valueUpStream,a);
    }

    public int serviceSum() {
        return a.getValueDownStream()+2;
    }


    public int getValueUpStream() {
        return valueUpStream;
    }

}
