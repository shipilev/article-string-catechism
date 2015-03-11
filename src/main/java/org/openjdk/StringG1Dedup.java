package org.openjdk;

import org.openjdk.jol.info.GraphLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringG1Dedup {

    static final int COUNT = 100;
    static final int DUP_RATE = 100;

    public static void main(String... args) {
        List<String> strs = new ArrayList<>();
        for (int s = 0; s < DUP_RATE; s++) {
            for (int c = 0; c < COUNT; c++) {
                strs.add("first" + "String" + c);
            }
        }

        String last = GraphLayout.parseInstance(strs).toFootprint();
        System.out.println("*** Original:");
        System.out.println(last);

        for (int gc = 0; gc < 100; gc++) {
            String cur = GraphLayout.parseInstance(strs).toFootprint();

            if (!cur.equals(last)) {
                System.out.println("*** GC changed:");
                System.out.println(cur);
                last = cur;
            }

            System.gc();
        }

        dedup(strs);

        System.out.println("*** Dedup:");
        System.out.println(GraphLayout.parseInstance(strs).toFootprint());
    }

    private static void dedup(List<String> list) {
        Map<String, String> map = new HashMap<>();
        for (int c = 0; c < list.size(); c++) {
            String s = list.get(c);
            String ms = map.get(s);
            if (ms == null) {
                map.put(s, s);
                ms = s;
            }
            list.set(c, ms);
        }
    }

}
