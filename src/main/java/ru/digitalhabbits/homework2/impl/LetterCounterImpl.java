package ru.digitalhabbits.homework2.impl;

import ru.digitalhabbits.homework2.LetterCounter;

import java.util.Map;
import java.util.stream.Collectors;

public class LetterCounterImpl implements LetterCounter {
    @Override
    public Map<Character, Long> count(String input) {
        return input.chars()
                .mapToObj(ch -> (char) ch)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
    }
}
