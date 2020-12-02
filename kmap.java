package com.company;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    //用来反转大小写的
    public static String reverseCase(String text) {
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (Character.isUpperCase(c)) {
                chars[i] = Character.toLowerCase(c);
            } else if (Character.isLowerCase(c)) {
                chars[i] = Character.toUpperCase(c);
            }
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        /*
        思路：用字典的键来表示逻辑关系，值表示该逻辑上有值没
        然后遍历每一个key value
        将每一个key 的 每一个字母大小写分别变换，去字典中查找，有没有可以消去的项
         */

        //控制台获取输入
        System.out.println("请输入信息：");
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        String str2 = sc.nextLine();
        //split 来获取需要的数据
        String[] strs1 = str1.split(" ");
        String[] strs2 = str2.split(" ");

        // 用 LinkedHashMap 来保存放入数据之后 ，数据保存放入的状态；所以这里不用 HashMap
        Map<String, String> map = new LinkedHashMap<String, String>();

        Map<String, Integer> res = new LinkedHashMap<String, Integer>();
        //初始化map
        map.put("ABC", strs1[0]);
        map.put("aBC", strs1[1]);
        map.put("abC", strs1[2]);
        map.put("AbC", strs1[3]);

        map.put("ABc", strs2[0]);
        map.put("aBc", strs2[1]);
        map.put("abc", strs2[2]);
        map.put("Abc", strs2[3]);
        //最后用来拼接逻辑关系的备份
        Map<String, String> mapback = new LinkedHashMap<String, String>();
        mapback.putAll(map);

//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            System.out.println("key:" + entry.getKey() + "   value:" + entry.getValue());
//        }
        //主要的循环遍历
        for (Map.Entry<String, String> entry : map.entrySet()) {

            //判断该位置的逻辑量是否纯在值
            if (!entry.getValue().equals("0")) {
                //1.分割key
                String[] its = entry.getKey().split("");
                String[] _its = new String[its.length];
                for (int i = 0; i < its.length; i++) {
                    _its[i] = reverseCase(its[i]);
                }
                //2.分别反转大小写
                String _str1 = _its[0] + its[1] + its[2];
                String _str2 = its[0] + _its[1] + its[2];
                String _str3 = its[0] + its[1] + _its[2];
                //3. 查看反转大小写后 是否有值纯在
                if (!map.get(_str1).equals("0")) {
                    //
                    map.put(_str1, "0");
                    map.put(entry.getKey(), "0");
                    res.put(entry.getKey(), 1);
                } else if (!map.get(_str2).equals("0")) {
                    map.put(_str2, "0");
                    map.put(entry.getKey(), "0");
                    res.put(entry.getKey(), 2);
                } else if (!map.get(_str3).equals("0")) {
                    map.put(_str3, "0");
                    map.put(entry.getKey(), "0");
                    res.put(entry.getKey(), 3);
                }
            }
        }

        //拼接最后的逻辑字符串
        String finalres = "";
        for (Map.Entry<String, String> entry : mapback.entrySet()) {

            if (res.get(entry.getKey()) != null){
                String[] tmps=entry.getKey().split("") ;
                String tmp = "";
                for (int i=0;i<3;i++){
                    if (i == res.get(entry.getKey())-1){
                    }else {
                        tmp += tmps[i];
                    }

                }

                finalres += tmp+"+";
            }
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (!entry.getValue().equals("0") ){
                finalres += entry.getKey();
            }

        }
        //输出结果
        System.out.println(finalres);
    }
}
