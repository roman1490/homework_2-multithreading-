package ru.digitalhabbits.homework2.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.digitalhabbits.homework2.FileReader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class FileReaderImplTest {

    @Test
    void readLines() {
        String path = "src/test/resources/testFileReaderText.txt";
        File file = new File(path);

        List<String> expected = new ArrayList<>();
        expected.add("first string");
        expected.add("second string");

        FileReader reader = new FileReaderImpl();
        List<String> actual = new ArrayList<>();

        try {
            reader.readLines(file).forEach(actual::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(expected, actual);
    }
}