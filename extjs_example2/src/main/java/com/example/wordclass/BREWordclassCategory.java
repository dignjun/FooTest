package com.example.wordclass;

import java.util.List;

/**
 * @Description:
 * @Author: baoy
 * @Date: 2019/5/13 11:46
 */
public class BREWordclassCategory {

    private String name;
    private List<BREWordclassCategory> categories;
    private List<String> synoWords;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BREWordclassCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<BREWordclassCategory> categories) {
        this.categories = categories;
    }

    public List<String> getSynoWords() {
        return synoWords;
    }

    public void setSynoWords(List<String> synoWords) {
        this.synoWords = synoWords;
    }
}
