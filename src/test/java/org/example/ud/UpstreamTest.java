package org.example.ud;


import org.example.ud.downstream.B;
import org.example.ud.downstream.C;
import org.example.ud.upstream.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UpstreamTest {


    @Test
    void test1() {
        {
            var valueDownStream = 4;
            var valueUpStream = 5;
            var a = A.of(valueDownStream, (Object var1, Integer var2) -> Integer.parseInt(var1.toString()) + var2);
            var b = B.of(valueUpStream, a);

            // downstream
            Assertions.assertEquals(a.getValueDownStream() + 2, b.serviceSum());
            // upstream
            Assertions.assertEquals(b.getValueUpStream() + 1, a.serviceCustom(valueUpStream));
        }

        {
            var valueDownStream = 4;
            var valueUpStream = "str-c";
            var a = A.of(valueDownStream, (Object var1, Integer var2) -> var1.toString().hashCode() + var2);
            var c = C.of(valueUpStream, a);


            // downstream
            Assertions.assertEquals(a.getValueDownStream() + "2", c.serviceConcat());
            // upstream
            Assertions.assertEquals(c.getValueUpStream().hashCode() + 1, a.serviceCustom(valueUpStream));

        }

    }
}
