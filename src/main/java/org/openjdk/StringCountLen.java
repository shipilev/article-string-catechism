package org.openjdk;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringCountLen {

    public static void main(String... args) {
        System.out.println(ClassLayout.parseClass(String.class).toPrintable());
    }

}
