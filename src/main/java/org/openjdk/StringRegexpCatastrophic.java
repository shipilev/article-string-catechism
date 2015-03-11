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

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(5)
@State(Scope.Thread)
public class StringRegexpCatastrophic {

    @Param({"2", "4", "6", "8", "10", "12", "14", "16"})
    private int size;

    private String text1, text2;
    private Pattern image;

    @Setup
    public void setup() {
        StringBuilder sb = new StringBuilder();
        for (int c = 0; c < size; c++) {
            sb.append('x');
        }
        text1 = sb.toString();
        sb.append('y');
        text2 = sb.toString();
        image = Pattern.compile("(x+x+)+y");
    }

    @Benchmark
    public boolean text1() {
        return image.matcher(text1).matches();
    }

    @Benchmark
    public boolean text2() {
        return image.matcher(text2).matches();
    }

}
