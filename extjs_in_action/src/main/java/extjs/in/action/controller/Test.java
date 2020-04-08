package extjs.in.action.controller;

import java.util.HashSet;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
        Set<String> set = new HashSet<String>();
        set.add("ad");
        set.add("324");
        set.add("756");
        for (String s: set) {
            System.out.println(s);
        }
        set.add("hgj5");
        set.add("hgj532");
        for (String s: set) {
            System.out.println(s);
        }
    }
}
