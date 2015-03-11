package org.openjdk;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.CompilerControl;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(5)
@State(Scope.Thread)
public class StringCharArray {

    @Param({"1", "10", "100", "1000"})
    private int size;

    private String text;

    @Setup
    public void setup() {
        StringBuilder sb = new StringBuilder();
        for (int c = 0; c < size; c++) {
            sb.append(c);
        }
        text = sb.toString();
    }

    @Benchmark
    public int charAt() {
        int r = 0;
        for (int c = 0; c < text.length(); c++) {
            r += text.charAt(c);
        }
        return r;
    }

    @Benchmark
    public int charAt_spoil() {
        int r = 0;
        for (int c = 0; c < text.length(); c++) {
            spoiler();
            r += text.charAt(c);
        }
        return r;
    }

    @Benchmark
    public int toCharArray() {
        int r = 0;
        char[] chars = text.toCharArray();
        for (int c = 0; c < text.length(); c++) {
            r += chars[c];
        }
        return r;
    }

    @Benchmark
    public int toCharArray_spoil() {
        int r = 0;
        char[] chars = text.toCharArray();
        for (char c : chars) {
            spoiler();
            r += c;
        }
        return r;
    }

    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    private void spoiler() {
        // Здесь дедушка Ленин что-то написал молоком
    }

}
