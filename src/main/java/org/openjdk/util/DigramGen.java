package org.openjdk.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class DigramGen {

    public static void main(String... args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, Charset.forName("UTF-8")));

        List<String> trimmed = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            String trim = line.trim();
            if (!trim.isEmpty()) {
                trimmed.add(trim);
            }
        }

        String[] words = trimmed.toArray(new String[0]);
        int size = words.length;
        int[] mult = new int[size];
        int[] hc = new int[size];

        for (int c = 0; c < size; c++) {
            hc[c] = words[c].hashCode();
            mult[c] = POWERS[words[c].length()];
        }

        System.out.println("Read " + size + " words");

        int eHC = " ".hashCode();

        IntStream.range(0, size).parallel().forEach(c1 -> {
            int hc1 = hc[c1] * 31 + eHC;
            for (int c2 = 0; c2 < size; c2++) {
                if ((hc1 * mult[c2] + hc[c2]) == TARGET_HASHCODE) {
                    String s = words[c1] + " " + words[c2];
                    System.out.printf("%8.5f%%, hit for \"%s\", hc = %d%n", 100.0 * PROCESSED.get() / size, s, s.hashCode());
                }
            }
            PROCESSED.incrementAndGet();
        });
    }

    static final int TARGET_HASHCODE = Integer.getInteger("targetHC", 0);

    static final AtomicInteger PROCESSED = new AtomicInteger();

    static final int[] POWERS;

    static {
        final int MAX = 10000;
        POWERS = new int[MAX];

        int r = 1;
        for (int c = 0; c < MAX; c++) {
            POWERS[c] = r;
            r *= 31;
        }
    }
}
