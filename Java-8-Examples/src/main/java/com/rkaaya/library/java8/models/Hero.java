package com.rkaaya.library.java8.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Hero {
    private String name;

    public static boolean isHeroNameIsLurker(Hero hero) {
        return "Lurker".equals(hero.getName());
    }

    public boolean isHeroNameIsEquals(Hero hero) {
        return name.equals(hero.getName());
    }
}
