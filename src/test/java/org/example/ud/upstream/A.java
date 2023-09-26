package org.example.ud.upstream;


import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;

public class A {

    private final int valueDownStream;

    private final BiFunction<Object, Integer, Integer> customLogic;

    private A(int valueDownStream, BiFunction<Object, Integer, Integer> customLogic) {
        this.valueDownStream = valueDownStream;
        this.customLogic = customLogic;
    }

    @Contract(value = "_, _ -> new", pure = true)
    public static @NotNull A of(int valueDownStream, BiFunction<Object, Integer, Integer> customLogic) {
        return new A(valueDownStream, customLogic);
    }

    public int serviceCustom(Object upStream) {
        return customLogic.apply(upStream, 1);
    }

    public int getValueDownStream() {
        return this.valueDownStream;
    }

}
