package org.example.ud2.downstream;

import org.example.ud2.upstream.A;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class C {
    private final String valueUpStream;
    private final A<String,Integer,Integer> a;

    private C(String valueUpStream, A<String,Integer,Integer> a) {
        this.valueUpStream = valueUpStream;
        this.a = a;
    }

    @Contract(value = "_, _ -> new", pure = true)
    public static @NotNull C of(String valueUpStream, A<String,Integer,Integer> a) {
        return new C(valueUpStream,a);
    }

    public String serviceConcat() {
        return Integer.toString(a.getValueDownStream()).concat("2");
    }

    public String getValueUpStream() {
        return valueUpStream;
    }

}
