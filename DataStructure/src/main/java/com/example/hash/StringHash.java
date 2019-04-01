package com.example.hash;

/**
 * @author DINGJUN
 * @date 2019.04.01
 */
public class StringHash {
    public static void main(String[] args) {
        int hash = hash("我们", 10);
        System.out.println(hash);
    }

    public static int hash(String key, int tableSize) {
        int hashValue = 0;
        for (int i = 0; i < key.length(); i++) {
            int t = key.charAt(i);
            hashValue += t;
        }
        return hashValue % tableSize;
    }

    public static int hash2(String key, int tableSize) {
        return ((key.charAt(0) + 27 * key.charAt(1) + 729 * key.charAt(2)) % tableSize);
    }

    public static int hash3(String key, int tableSize) {
        int hashValue = 0;
        for (int i = 0; i < key.length(); i ++) {
            hashValue = 37 * hashValue + key.charAt(i);
        }
        hashValue %= tableSize;
        if(hashValue < 0) {
            hashValue += tableSize;
        }
        return hashValue;
    }
}
