package org.example.ud2;


import org.example.ud2.downstream.B;
import org.example.ud2.downstream.C;
import org.example.ud2.upstream.BA;
import org.example.ud2.upstream.CA;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpstreamTest {

    @Test
    void test1() {
        {
            var valueDownStream = 4;
            var valueUpStream = 5;
            var a = BA.of(valueDownStream, Integer::sum);
            var b = B.of(valueUpStream, a);

            // downstream
            assertEquals(a.getValueDownStream() + 2, b.serviceSum());
            // upstream
            assertEquals(b.getValueUpStream() + 1, a.serviceCustom(valueUpStream));
        }

        {
            var valueDownStream = 4;
            var valueUpStream = "str-c";
            var a = CA.of(valueDownStream, (String var1, Integer var2) -> var1.hashCode() + var2);
            var c = C.of(valueUpStream, a);


            // downstream
            assertEquals(a.getValueDownStream() + "2", c.serviceConcat());
            // upstream
            assertEquals(c.getValueUpStream().hashCode() + 1, a.serviceCustom(valueUpStream));

        }

    }
}
