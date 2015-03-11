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
public class StringEquals {

    private String bar10_0, bar10_1, bar10_2, bar10_3, bar11;

    @Setup
    public void setup() {
        bar10_0 = "BarBarBarA";
        bar10_1 = "BarBarBarA";
        bar10_2 = "BarBarBarB";
        bar10_3 = "ABarBarBar";
        bar11   = "BarBarBarAB";
    }

    @Benchmark
    public boolean sameChar() {
        return bar10_0.equals(bar10_1);
    }

    @Benchmark
    public boolean sameLen_diffEnd() {
        return bar10_0.equals(bar10_2);
    }

    @Benchmark
    public boolean sameLen_diffStart() {
        return bar10_0.equals(bar10_3);
    }

    @Benchmark
    public boolean differentLen() {
        return bar10_0.equals(bar11);
    }

}
