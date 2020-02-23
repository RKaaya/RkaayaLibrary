package com.rkaaya.library.java8.optional;

import com.rkaaya.library.java8.models.Hero;
import com.rkaaya.library.java8.models.Team;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class BasicOptional {

    public static void main(String[] args) {
        //Value is not present
        Optional<String> polisName = Optional.empty();
        log.info("Optional is not present: " + polisName.isPresent());

        //Value is present
        String str = "OrwoPolis";
        Optional<String> orwoPolisName = Optional.of(str);
        log.info("Optional is present: " + orwoPolisName.isPresent());
        log.info("Optional is contains a string: " + orwoPolisName.get());

        //Get default value if it's not present.
        Optional<String> forkyVilleNull = Optional.ofNullable(getBackANullValue());
        log.info("Optional is present: " + forkyVilleNull.isPresent());
        log.info("Optinal give back default value: " + forkyVilleNull.orElse("Default"));
        Optional<String> forkyVille = Optional.ofNullable(getBackAPolisName());
        log.info("Optional is contains a string: " + forkyVille.isPresent());
        log.info("Optional is give back the real string: " + forkyVille.orElse("Default"));

        //Get a list, or initialize it if it's null.
        List<String> listOfNames = Optional.ofNullable(getANull()).orElseGet(() -> new ArrayList<>());
        log.info("List is present and counts {} item.", listOfNames.size());

        //Get properties safely from Hero
        Optional<Hero> heroWoop = Optional.ofNullable(getANullHero());
        String woopTeamName = heroWoop
                .flatMap(Hero::getTeam)
                .flatMap(Team::getTeamName)
                .orElse("team name is unknown");
        log.info("Hero Woop's team name is: " + woopTeamName);

        Optional<Hero> heroWoop2 = Optional.ofNullable(getAHero());
        String woop2TeamName = heroWoop
                .flatMap(Hero::getTeam)
                .flatMap(Team::getTeamName)
                .orElse("team name is unknown");
        log.info("Hero Woop's team name is: " + woop2TeamName);

        //Make string upperCase or throw an exception
        String multyVille = null;
        Optional<String> valueOfMultyVille = Optional.ofNullable(multyVille);
        try {
            valueOfMultyVille.orElseThrow(RuntimeException::new).toUpperCase();
        } catch (Exception e) {
            log.info("MultyVille was null.");
        }
    }

    private static String getBackAPolisName() {
        return "ForkyVille";
    }

    private static String getBackANullValue() {
        return null;
    }

    private static List<String> getANull() {
        return null;
    }

    private static Hero getANullHero() {
        return null;
    }

    private static Hero getAHero() {
        return new Hero("Woop");
    }
}
