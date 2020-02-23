package com.rkaaya.library.java8.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

@Getter
@AllArgsConstructor
public class Hero {
    private String name;
    private Optional<Team> team;

    public Hero(String name) {
        this.name = name;
    }

    public static boolean isHeroNameIsLurker(Hero hero) {
        return "Lurker".equals(hero.getName());
    }

    public boolean isHeroNameIsEquals(Hero hero) {
        return name.equals(hero.getName());
    }
}
