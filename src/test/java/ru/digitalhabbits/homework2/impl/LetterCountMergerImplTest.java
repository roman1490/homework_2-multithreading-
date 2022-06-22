package ru.digitalhabbits.homework2.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.digitalhabbits.homework2.LetterCountMerger;

import java.util.HashMap;
import java.util.Map;

class LetterCountMergerImplTest {

    @Test
    void merge() {
        Map<Character, Long> map1 = new HashMap<>();
        map1.put('a', 3L);
        map1.put('b', 2L);
        map1.put('c', 1L);

        Map<Character, Long> map2 = new HashMap<>();
        map2.put('a', 3L);
        map2.put('b', 2L);
        map2.put('d', 8L);

        Map<Character, Long> expected = new HashMap<>();
        expected.put('a', 6L);
        expected.put('b', 4L);
        expected.put('c', 1L);
        expected.put('d', 8L);

        LetterCountMerger merger = new LetterCountMergerImpl();

        Assertions.assertEquals(expected, merger.merge(map1, map2));
    }
}