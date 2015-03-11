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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(5)
@State(Scope.Thread)
public class StringDedup {

    @Param({"1000"})
    private int size;

    @Param({"0.0", "0.1", "0.2", "0.3", "0.4", "0.5", "0.6", "0.7", "0.8", "0.9", "1.0"})
    private double prob;

    private List<String> strings;
    private InternDeduplicator intern;
    private HMDeduplicator hm;
    private CHMDeduplicator chm;

    @Setup
    public void setup() {
        hm = new HMDeduplicator(prob);
        chm = new CHMDeduplicator(prob);
        intern = new InternDeduplicator(prob);
        strings = new ArrayList<>();
        for (int c = 0; c < size; c++) {
            strings.add("String" + c);
        }
    }

    @Benchmark
    public void intern(Blackhole bh) {
        for (String s : strings) {
            bh.consume(intern.dedup(s));
        }
    }

    @Benchmark
    public void chm(Blackhole bh) {
        for (String s : strings) {
            bh.consume(chm.dedup(s));
        }
    }

    @Benchmark
    public void hm(Blackhole bh) {
        for (String s : strings) {
            bh.consume(hm.dedup(s));
        }
    }

    public interface Deduplicator {
        String dedup(String s);
    }

    public static class InternDeduplicator implements Deduplicator {
        private final int prob;

        public InternDeduplicator(double prob) {
            this.prob = (int) (Integer.MIN_VALUE + prob * (1L << 32));
        }

        @Override
        public String dedup(String s) {
            if (ThreadLocalRandom.current().nextInt() < prob) {
                return s.intern();
            } else {
                return s;
            }
        }
    }

    public static class CHMDeduplicator implements Deduplicator {
        private final int prob;
        private final Map<String, String> map;

        public CHMDeduplicator(double prob) {
            this.prob = (int) (Integer.MIN_VALUE + prob * (1L << 32));
            this.map = new ConcurrentHashMap<>();
        }

        @Override
        public String dedup(String s) {
            if (ThreadLocalRandom.current().nextInt() < prob) {
                String exist = map.putIfAbsent(s, s);
                return (exist == null) ? s : exist;
            } else {
                return s;
            }
        }
    }

    public static class HMDeduplicator implements Deduplicator {
        private final int prob;
        private final Map<String, String> map;

        public HMDeduplicator(double prob) {
            this.prob = (int) (Integer.MIN_VALUE + prob * (1L << 32));
            this.map = new HashMap<>();
        }

        @Override
        public String dedup(String s) {
            if (ThreadLocalRandom.current().nextInt() < prob) {
                String exist = map.putIfAbsent(s, s);
                return (exist == null) ? s : exist;
            } else {
                return s;
            }
        }
    }

}
