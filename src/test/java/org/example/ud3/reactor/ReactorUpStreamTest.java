package org.example.ud3.reactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class ReactorUpStreamTest {

    @Test
    void test1() {
        {
            String key = "message";
            Flux<String> r = Flux.deferContextual(ctx ->
                    Flux.just("Hello " + ctx.get(key))
            ).contextWrite(ctx ->
                    ctx.put(key, "Reactor")
            ).flatMap(s ->
                    Flux.deferContextual(ctx ->
                            Flux.just(s + " " + ctx.get(key))
                    )
            ).contextWrite(ctx -> ctx.put(key, "World"));
            r.subscribe(System.out::println, Throwable::printStackTrace, () -> System.out.println("Completed4!"));
        }
        System.out.println();
        {
            String key = "message";
            Flux<String> r = Flux.just("Hello1", "Hello2", "Hello3")
                    .flatMap(s -> Flux.deferContextual(ctxView -> Flux.just(s + " " + ctxView.get(key))))
                    .flatMap(s -> Flux.deferContextual(ctxView -> Flux.just(s + " " + ctxView.get(key)))
                            .contextWrite(ctx -> ctx.put(key, "Reactor")))
                    .contextWrite(ctx -> ctx.put(key, "World"));
            r.subscribe(System.out::println, Throwable::printStackTrace, () -> System.out.println("Completed5!"));
        }
        System.out.println();

    }

    @Test
    void test2() throws InterruptedException {
        {
            {
                Scheduler s = Schedulers.newParallel("parallel-sec", 2);

                final Flux<String> flux = Flux
                        .range(1, 5)
                        .map(i -> i + 10)
                        .publishOn(s)
                        .doOnComplete(() -> System.out.println("complete.."))
                        .map(i -> "value " + i);

                var t = new Thread(() -> flux.subscribe(
                        System.out::println,
                        System.err::println,
                        () -> System.out.println("Completed!")
                ));
                t.start();
                t.join();
                s.dispose();
            }
        }
    }
}
