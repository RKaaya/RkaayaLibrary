package com.rkaaya.library.java8.stream;

import com.rkaaya.library.java8.models.Hero;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
public class MethodReferences {

    public static List<Hero> getHeroes() {
        List<Hero> heroes = new ArrayList<>();
        heroes.add(new Hero("Woltar"));
        heroes.add(new Hero("Forky"));
        heroes.add(new Hero("Lurker"));
        return heroes;
    }

    public static List<String> getHeroNames() {
        return Arrays.asList("Woltar", "Forky", "Lurker");
    }

    public static void main(String[] args) {
        List<Hero> heroes = getHeroes();

        //Reference to a static method with lambda expression
        boolean isLurker = heroes.stream().anyMatch(h -> Hero.isHeroNameIsLurker(h));
        log.info("List contains Lurker: " + isLurker);

        //Reference to a static method
        isLurker = heroes.stream().anyMatch(Hero::isHeroNameIsLurker);
        log.info("List contains Lurker: " + isLurker);

        //Reference to an instance method
        Hero heroForky = new Hero("Forky");
        boolean heroIsForky = heroes.stream().anyMatch(heroForky::isHeroNameIsEquals);
        log.info("List contains heroForky: " + heroIsForky);

        //Count how many hero name is empty
        long noNameCount = heroes.stream().map(Hero::getName).filter(String::isEmpty).count();
        log.info("List contains {} empty named heroes.", noNameCount);

        //Negate method
        long nameCount = heroes.stream().map(Hero::getName).filter(((Predicate<String>)String::isEmpty).negate()).count();
        log.info("List contains {} named heroes.", nameCount);

        //Reference to a constructor
        List<String> heroNames = getHeroNames();
        List<Hero> newHeroesFromString = heroNames.stream().map(Hero::new).collect(Collectors.toList());
        log.info("Created {} new hero.", newHeroesFromString.size());

    }
}
