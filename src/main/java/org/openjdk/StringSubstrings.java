package org.openjdk;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jol.info.GraphLayout;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(5)
@State(Scope.Thread)
public class StringSubstrings {

    @Param({"0", "30", "60", "90", "120"})
    private int limit;

    private String str;

    @Setup
    public void setup() {
        str = "JokerConf 2014: Why So Serious? " +
              "JokerConf 2014: Why So Serious? " +
              "JokerConf 2014: Why So Serious? " +
              "JokerConf 2014: Why So Serious? ";
    }

    @Benchmark
    public String head() {
        return str.substring(limit);
    }

    @Benchmark
    public String tail() {
        return str.substring(0, limit);
    }

}
