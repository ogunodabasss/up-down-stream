package org.example.ud2.upstream;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;

public class CA extends A<String,Integer, Integer> {

    protected CA(int valueDownStream, BiFunction<String, Integer, Integer> customLogic) {
        super(valueDownStream, customLogic);
    }

    @Contract("_, _ -> new")
    public  static @NotNull CA of(int valueDownStream, BiFunction<String, Integer, Integer> customLogic) {
        return new CA(valueDownStream,customLogic);
    }

    @Override
    public Integer serviceCustom(String s) {
        return this.customLogic.apply(s,1);
    }
}
