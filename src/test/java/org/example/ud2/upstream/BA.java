package org.example.ud2.upstream;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;

public class BA extends A<Integer,Integer,Integer> {
    protected BA(int valueDownStream, BiFunction<Integer, Integer, Integer> customLogic) {
        super(valueDownStream, customLogic);
    }

    @Contract("_, _ -> new")
    public  static @NotNull BA of(int valueDownStream, BiFunction<Integer, Integer, Integer> customLogic) {
        return new BA(valueDownStream,customLogic);
    }

    @Override
    public Integer serviceCustom(Integer integer) {
        return this.customLogic.apply(integer,1);
    }
}
