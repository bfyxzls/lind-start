package com.lind.common;

import com.lind.common.tree.DictionarySearch;

import java.util.HashMap;

public class DictionarySearchTest {
    public static void main(String[] args) {
        DictionarySearch ds = new DictionarySearch();
        ds.insertKeyword("美国");
        ds.insertKeyword("美海军");
        ds.insertKeyword("美国海军");
        ds.insertKeyword("战斗机");
        HashMap<String, Integer> result = ds.search("报道称，美国海军正面临严重的战机荒。……");
        System.out.println(result);
    }
}
