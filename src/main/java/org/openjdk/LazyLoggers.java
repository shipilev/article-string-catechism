package org.openjdk;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.CompilerControl;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(5)
@State(Scope.Thread)
public class LazyLoggers {

    private int x;
    private boolean enabled;

    @Setup
    public void setup() {
        x = 1709;
        enabled = false;
    }

    @Benchmark
    public void heap_lambda() {
        log(() -> "Wow, x is such " + x + " now");
    }

    @Benchmark
    public void heap_string() {
        log("Wow, x is such " + x + " now");
    }

    @Benchmark
    public void heap_string_guarded() {
        if (enabled) {
            log("Wow, x is such " + x + " now");
        }
    }

    @Benchmark
    public void local_lambda() {
        int lx = x;
        log(() -> "Wow, x is such " + lx + " now");
    }

    @Benchmark
    public void local_string() {
        int lx = x;
        log("Wow, x is such " + lx + " now");
    }

    @Benchmark
    public void local_string_guarded() {
        int lx = x;
        if (enabled) {
            log("Wow, x is such " + lx + " now");
        }
    }

    @Benchmark
    public void noArg_lambda() {
        log(() -> "Such log message, wow.");
    }

    @Benchmark
    public void noArg_string() {
        log("Such log message, wow.");
    }

    @Benchmark
    public void noArg_string_guarded() {
        if (enabled) {
            log("Such log message, wow.");
        }
    }

    @CompilerControl(CompilerControl.Mode.INLINE)
    public void log(Supplier<String> msg) {
        if (enabled) {
            System.out.println(msg.get());
        }
    }

    @CompilerControl(CompilerControl.Mode.INLINE)
    public void log(String msg) {
        if (enabled) {
            System.out.println(msg);
        }
    }

}
