package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串测试
 *
 */
public class AlgTest {
    public static void main(String[] args) {
        List<String[]> a = a("heeelloo");
        System.out.println("a方法执行结果：");
        for (String[] strings : a) {
            for (String string : strings) {
                System.out.print(string + "\t");
            }
            System.out.println();
        }
        System.out.println("=================================");
        System.out.println("b方法执行结果：");
        List<String> b = b2(a);
        for (String str : b) {
            System.out.println(str);
        }
    }

    private static List<String[]> a(String input){
        if(input == null || "".equals(input.trim())){
            return new ArrayList<String[]>(0);
        }
        int len = input.length();
        List<String[]> result = new ArrayList<String[]>();
        List<Integer> indexs = new ArrayList<Integer>();
        indexs.add(0);
        char lastChar = input.charAt(0);
        for (int i = 0; i < len; i++) {
            if(input.charAt(i) != lastChar){
                indexs.add(i);
                lastChar = input.charAt(i);
            }
        }
        indexs.add(len);
        for (int i = 0, size = indexs.size() - 1; i < size; i++) {
            String repeatWord = input.substring(indexs.get(i), indexs.get(i)+1);
            int repeatSize = indexs.get(i + 1) - indexs.get(i);
            String[] words = new String[repeatSize];
            words[0] = repeatWord;
            for (int j = 1; j < repeatSize; j++) {
                words[j] = words[j - 1] + repeatWord;
            }
            result.add(words);
        }

        return result;
    }

    private static List<String> b(List<String[]> strings){
        List<String> result = new ArrayList<String>(1);
        result.add("");
        for (String[] string : strings) {
            List<String> strs = new ArrayList<String>(string.length * result.size());
            for (String str : string) {
                for (String s : result) {
                    strs.add(s + str);
                }
            }
            result = strs;
        }
        return result;

    }

    private static List<String> b2(List<String[]> strings){
        int total = 1;
        List<Count> countList = new ArrayList<Count>(strings.size());
        int co = 1;
        for (int i = strings.size() - 1; i >= 0; i--) {
            String[] string = strings.get(i);
            total *= string.length;
            Count c = new Count();
            c.c = string[0].charAt(0);
            c.count = co = co * string.length;
            countList.add(c);
        }
        List<String> result = new ArrayList<String>(total);
        for (int i = 0; i < total; i++) {
            StringBuffer buffer = new StringBuffer("");
            int number = i;
            for (int a = countList.size() -1; a >= 0; a--){
                Count count = countList.get(a);
                int size = a == 0 ? number + 1 : (number / countList.get(a - 1).count + 1);
                for (int j = 0; j < size; j++) {
                    buffer.append(count.c);
                }
                number = number % (a == 0 ? 1 : countList.get(a - 1).count);
            }
            result.add(buffer.toString());
        }
        return result;
    }

    private static class Count{
        private char c;
        private int count;
    }
}
