package org.example.ud2.upstream;

import java.util.function.BiFunction;

public abstract class A<T,U,R> {

    protected final int valueDownStream;

    protected final BiFunction<T,U,R> customLogic;


    protected A(int valueDownStream, BiFunction<T, U, R> customLogic) {
        this.valueDownStream = valueDownStream;
        this.customLogic = customLogic;
    }

   public abstract R serviceCustom(T t);

    public  int getValueDownStream() {
        return this.valueDownStream;
    }

}
