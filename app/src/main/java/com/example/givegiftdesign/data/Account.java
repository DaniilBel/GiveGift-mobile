package com.example.givegiftdesign.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account {
    private String id;
    private String nickname;
    private String email;
    private String about;
    private String birth_date;
    private static List<String> interests = new ArrayList<>();
    private static List<Integer> price_range = new ArrayList<>();

    public static void tempInterests() {
        interests.add("Кино");
        interests.add("Романтика");
        interests.add("Книги");

        price_range.add(100);
        price_range.add(10000);
    }

    public static void updateInterests(List<String> inter) {
        interests = inter;
    }

    public static void initPrice_range(float v1, float v2) {
        price_range.add((int)v1);
        price_range.add((int)v2);
    }
    public static void setPrice_range(float v1, float v2) {
        price_range.set(0, (int)v1);
        price_range.set(1, (int)v2);
    }

    public static List<String> getInterests() {
        return interests;
    }

    public static List<Integer> getPrice_range() {
        return price_range;
    }
}
