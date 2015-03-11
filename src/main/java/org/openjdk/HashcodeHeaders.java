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
import org.openjdk.jmh.infra.Blackhole;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(5)
@State(Scope.Thread)
public class HashcodeHeaders {

    @Param({"1", "10", "100", "1000", "10000"})
    private int size;

    private Map<String, String> httpHeaders;

    @Setup
    public void setup() {
        httpHeaders = new HashMap<>();
        for (String suffix : generateSuffixes(size)) {
            httpHeaders.put("X-Conference-" + suffix, "JokerConf 2014: Why So Serious?");
        }
    }

    public static List<String> generateSuffixes(int n) {
        List<String> list = Arrays.asList("Aa", "BB");
        for (int i = 1; i < n; i *= 2) {
            List<String> result = new ArrayList<>();
            for (String b : new String[]{"Aa", "BB"}) {
                for (String str : list) {
                    result.add(b + str);
                }
            }
            list = result;
        }

        return list.subList(0, n);
    }


    @Benchmark
    public void testKeyset(Blackhole bh) {
        for (String key : httpHeaders.keySet()) {
            bh.consume(httpHeaders.get(key));
        }
    }

    @Benchmark
    public void testEntryset(Blackhole bh) {
        for (Map.Entry<String, String> e : httpHeaders.entrySet()) {
            bh.consume(e);
        }
    }

}
