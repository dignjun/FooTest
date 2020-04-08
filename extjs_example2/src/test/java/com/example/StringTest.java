package com.example;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTest {

    /**
     * 查询重复词，并组成词
     * 如：abbcc
     * -[a],[b,bb],[c,cc]
     * -abc,abbc,abcc,abbcc
     */
    @Test
    public void test1() {
        String str = "aaaabbbbccccc";
        List<String[]> repeatList = a(str);
        List<String> worlds = b(repeatList);
        System.out.println(worlds);
    }

    /**
     * 抽取字符串中重复的字符串并转化为词组，[eee]-[e,ee,eee]
     * @param s
     * @return
     */
    public List<String[]> a(String s) {
        ArrayList<String[]> result = new ArrayList<>();
        // 1.获取重复的单词[h,eee,ll,oo]
        char last = s.charAt(0);
        int start = 0;
        List<String> ls = new ArrayList<>();
        for(int i = 1; i < s.length(); i++) {
            if(s.charAt(i) != last) {
                ls.add(s.substring(start, i));
                last = s.charAt(i);
                start = i;
            }
            if(i == s.length() - 1) {
                ls.add(s.substring(start, i + 1));
            }
        }
        // 2.重复单词排列[h]-[h] [eee]-[e,ee,eee]
        for (String sn: ls) {
            int length = sn.length();
            String[] arrays = new String[length];
            for(int i = 0; i < length; i ++) {
                arrays[i] = sn.substring(0, i + 1);
            }
            result.add(arrays);
        }
        for (String[] sa: result) { // 输出
            for (String ss: sa) {
                System.out.print(ss + " ");
            }
            System.out.println("");
        }
        return result;
    }

    /**
     * 获取词
     * @param ls
     * @return
     */
    public List<String> b(List<String[]> ls) {
        int worldnum = 1;
        List<String> tmp = new ArrayList<>();
        // 填词
        boolean first = true;
        for (String[] sa: ls) {
            if(first) {
                for (int i=0; i < sa.length; i++) {
                    tmp.add(sa[i]);
                }
                worldnum = 1 * sa.length;
                first = false;
            } else {
                List<String> lsb = new ArrayList<>();
                for (int i=0; i < tmp.size(); i ++) {
                    lsb.add(tmp.get(i));
                }
                int len = sa.length; // 2，则长度扩大两倍
                for(int i = 0 ;i < sa.length ; i ++) {// 需要被添加的数组sa[i] 是其中一个要添加到某一组的数据
                    for (int j = 0; j < worldnum; j++) {// 做其中一个元素的添加
                        if(i == 0) {
                            String sff = tmp.remove(worldnum * i + j);
                            tmp.add(worldnum * i + j, sff.concat(sa[i]));
                        } else {
                            tmp.add(lsb.get(j).concat(sa[i]));
                        }
                    }
                }
                worldnum *= sa.length;
            }
        }
        return tmp;
    }

    @Test
    public void test22() {
        String s = "abc *afsadfasdfbb";
        String[] split = s.split("\\*");
        System.out.println(split[0] +":"+split[1]);
        String s2 = "abc *afsadfasdfbb";
        Pattern p = Pattern.compile("\\*" + "(?=\\(|;|$)");
        Matcher matcher = p.matcher(s2);
        System.out.println(matcher.find());
    }
}
