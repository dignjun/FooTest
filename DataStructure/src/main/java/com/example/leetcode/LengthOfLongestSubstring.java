package com.example.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 3.无重复字符的最长子串
 *
 * @author DINGJUN
 * @date 2019.03.27
 */
public class LengthOfLongestSubstring {

    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++) // 字符串的第一个索引
            for (int j = i + 1; j <= n; j++) // 字符串的第二个索引
                if (unique(s, i, j)) ans = Math.max(ans, j - i);
        return ans;
    }

    public boolean unique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            Character ch = s.charAt(i);
            if (set.contains(ch))
                return false;
            set.add(ch);
        }
        return true;
    }


    public static void main(String[] args) {
//        LengthOfLongestSubstring ls = new LengthOfLongestSubstring();
//        int res = ls.lengthOfLongestSubstring("asddasdfsasdf");
//        System.out.println("无重复字符最长子串:" + res);

        String s = " ";
        HashSet<Character> set = new HashSet<Character>();
        int i = 0;
        int j = 0;
        int n = s.length();
        int result = 0;
        while(i < s.length() && j < s.length()) {
            if(set.add(s.charAt(j++))){
               result = Math.max(result, j-i);
            } else {
                set.clear();
                i++;
                j = i;
            }
        }
        System.out.println("length:" + result);
    }

    public int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }
}
