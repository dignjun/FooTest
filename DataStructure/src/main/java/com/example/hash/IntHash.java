package com.example.hash;

/**
 * 散列表数据结构中的散列函数
 * 关键字是整数的情况
 *
 * @author DINGJUN
 * @date 2019.04.01
 */
public class IntHash {

    // 将会产生很多的冲突
    public static int hash(int key, int length){
        return key / length;
    }
}
