package org.example.ud2.downstream;

import org.example.ud2.upstream.A;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class B {

    private final int valueUpStream;
    private final A<Integer,Integer,Integer> a;

    private B(int valueUpStream, A<Integer,Integer,Integer> a) {
        this.valueUpStream = valueUpStream;
        this.a = a;
    }

    @Contract(value = "_, _ -> new", pure = true)
    public static @NotNull B of(int valueUpStream, A<Integer,Integer,Integer> a) {
        return new B(valueUpStream,a);
    }

    public int serviceSum() {
        return a.getValueDownStream()+2;
    }

    public int getValueUpStream() {
        return valueUpStream;
    }

}
