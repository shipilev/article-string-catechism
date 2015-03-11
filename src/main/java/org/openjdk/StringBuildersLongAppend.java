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
public class StringBuildersLongAppend {

    private String s1, s2, s3, s4, s5, s6;

    @Setup
    public void setup() {
        s1 = "Foo";
        s2 = "Bar";
        s3 = "Baz";
        s4 = "Qux";
        s5 = "Cor";
        s6 = "Gra";
    }

    @Benchmark
    public String sb_1() {
        return new StringBuilder().append(s1).toString();
    }

    @Benchmark
    public String sb_2() {
        return new StringBuilder().append(s1).append(s2).toString();
    }

    @Benchmark
    public String sb_3() {
        return new StringBuilder().append(s1).append(s2).append(s3).toString();
    }

    @Benchmark
    public String sb_4() {
        return new StringBuilder().append(s1).append(s2).append(s3).append(s4).toString();
    }

    @Benchmark
    public String sb_5() {
        return new StringBuilder().append(s1).append(s2).append(s3).append(s4).append(s5).toString();
    }

    @Benchmark
    public String sb_6() {
        return new StringBuilder().append(s1).append(s2).append(s3).append(s4).append(s5).append(s6).toString();
    }

    @Benchmark
    public String string_1() {
        return s1;
    }

    @Benchmark
    public String string_2() {
        return s1 + s2;  // always newly created
    }

    @Benchmark
    public String string_3() {
        return s1 + s2 + s3;
    }

    @Benchmark
    public String string_4() {
        return s1 + s2 + s3 + s4;
    }

    @Benchmark
    public String string_5() {
        return s1 + s2 + s3 + s4 + s5;
    }

    @Benchmark
    public String string_6() {
        return s1 + s2 + s3 + s4 + s5 + s6;
    }

}
