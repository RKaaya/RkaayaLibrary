package com.rkaaya.library.java8.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
public class Team {
    private Optional<String> teamName;
}
