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
public class StringBuildersSideEffect {

    private int x;

    @Setup
    public void setup() {
        x = 1709;
    }

    @Benchmark
    public String just_concat() {
        return "" + x;
    }

    @Benchmark
    public String side_concat() {
        x--;
        return "" + (x++);
    }

    @Benchmark
    public String just_integerToString() {
        return Integer.toString(x);
    }

    @Benchmark
    public String side_integerToString() {
        x--;
        return Integer.toString(x++);
    }

    @Benchmark
    public String just_stringValueOf() {
        return String.valueOf(x);
    }

    @Benchmark
    public String side_stringValueOf() {
        x--;
        return String.valueOf(x++);
    }

}
