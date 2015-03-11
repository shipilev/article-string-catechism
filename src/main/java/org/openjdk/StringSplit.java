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

import java.util.Collections;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(5)
@State(Scope.Thread)
public class StringSplit {

    private String text;
    private String textDup;
    private Pattern pattern;

    @Setup
    public void setup() {
        text = "Глокая куздра штеко будланула бокра и курдячит бокрёнка.";
        textDup = text.replaceAll(" ", "  ");
        pattern = Pattern.compile("  ");
    }

    @Benchmark
    public String[] charSplit() {
        return text.split(" ");
    }

    @Benchmark
    public String[] strSplit() {
        return textDup.split("  ");
    }

    @Benchmark
    public String[] strSplit_pattern() {
        return pattern.split(textDup);
    }

}
