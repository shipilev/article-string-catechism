package org.openjdk;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(5)
@State(Scope.Thread)
public class HashcodeTwister {

    private String str1;
    private String str2;

    /*
        "французишкам механические средства труда"
        "срежиссированное высокоинквизиторством"
        "рентгенокинематография прославленым"
        "лжеотождествление электровиолончели"
        "эмульгированного неузнаваемостью"
        "инплантированные смертоносностию",
        "закупориваемся эксцентриситетами",
        "полисклеротическая вагнеровата",
        "неприконченные староизраильцы",
        "европеизированные препроверки",
     */

    @Setup
    public void setup() {
        str1 = "лжеотождествление электровиолончели";
        str2 = "электровиолончели лжеотождествление";
    }

    @Benchmark
    public int test1() {
        return str1.hashCode();
    }

    @Benchmark
    public int test2() {
        return str2.hashCode();
    }

}
