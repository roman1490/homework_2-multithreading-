package ru.digitalhabbits.homework2.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.digitalhabbits.homework2.LetterCounter;

import java.util.Map;

class LetterCounterImplTest {

    @Test
    void count() {
        LetterCounter letterCounter = new LetterCounterImpl();

        String str = "bbcaaa";
        Map<Character, Long> expected = Map.of(
                'a', 3L,
                'b', 2L,
                'c', 1L
        );

        Assertions.assertEquals(expected, letterCounter.count(str));
    }
}