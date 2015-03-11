package org.openjdk;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(5)
@State(Scope.Thread)
public class LoopedConcat {

    @Benchmark
    public String string() {
        String s = "Foo";
        for (int c = 0; c < 1000; c++) {
            s += "Bar";
        }
        return s;
    }

    @Benchmark
    public String stringBuilder() {
        StringBuilder sb = new StringBuilder();
        for (int c = 0; c < 1000; c++) {
            sb.append("Bar");
        }
        return sb.toString();
    }

    @Benchmark
    public String stringBuffer() {
        StringBuffer sb = new StringBuffer();
        for (int c = 0; c < 1000; c++) {
            sb.append("Bar");
        }
        return sb.toString();
    }

}
