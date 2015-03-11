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

        List<String> lines = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            String trim = line.trim();
            if (!trim.isEmpty()) {
                lines.add(trim);
            }
        }

        int[] mult = new int[lines.size()];
        int[] hashcode = new int[lines.size()];

        for (int c = 0; c < lines.size(); c++) {
            hashcode[c] = lines.get(c).hashCode();
            mult[c] = powers[lines.get(c).length()];
        }

        int size = lines.size();
        System.out.println("Read " + size + " words");

        int eHC = " ".hashCode();

        IntStream.range(0, lines.size()).parallel().forEach(c1 -> {
            int hc1 = hashcode[c1] * 31 + eHC;
            for (int c2 = 0; c2 < size; c2++) {
                if (hc1 * mult[c2] + hashcode[c2] == 0) {
                    String s = lines.get(c1) + " " + lines.get(c2);
                    System.out.printf("%8.5f%%, hit for \"%s\", hc = %d%n", 100.0 * processed.get() / size, s, s.hashCode());
                }
            }
            processed.incrementAndGet();
        });
    }

    static AtomicInteger processed = new AtomicInteger();

    static int[] powers;

    static {
        final int POWERS = 10000;
        powers = new int[POWERS];

        int r = 1;
        for (int c = 0; c < POWERS; c++) {
            powers[c] = r;
            r *= 31;
        }
    }
}
