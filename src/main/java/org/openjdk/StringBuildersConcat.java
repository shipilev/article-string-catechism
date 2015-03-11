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
public class StringBuildersConcat {

    private int x;

    @Setup
    public void setup() {
        x = 1709;
    }

    @Benchmark
    public String concat_Pre() {
        return "" + x;
    }

    @Benchmark
    public String concat_Post() {
        return x + "";
    }

    @Benchmark
    public String integerToString() {
        return Integer.toString(x);
    }

    @Benchmark
    public String stringValueOf() {
        return String.valueOf(x);
    }


}
