package ru.digitalhabbits.homework2.impl;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import ru.digitalhabbits.homework2.FileLetterCounter;
import ru.digitalhabbits.homework2.FileReader;
import ru.digitalhabbits.homework2.LetterCountMerger;
import ru.digitalhabbits.homework2.LetterCounter;

public class AsyncFileLetterCounter implements FileLetterCounter {

    @Override
    public Map<Character, Long> count(File input) {
        FileReader reader = new FileReaderImpl();
        LetterCounter counter = new LetterCounterImpl();
        LetterCountMerger merger = new LetterCountMergerImpl();

        Queue<String> queueStrings = new ConcurrentLinkedQueue<>();
        Queue<Map<Character, Long>> queueMaps = new ConcurrentLinkedQueue<>();
        Map<Character, Long> totalMap = new HashMap<>();

        Lock lock = new ReentrantLock();

        try {
            reader.readLines(input).forEach(queueStrings::add);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Runnable countTask = () -> {
            lock.lock();
            queueMaps.add(counter.count(queueStrings.poll()));
            lock.unlock();
        };
        Runnable mergeTask = () -> {
            lock.lock();
            merger.merge(queueMaps.poll(), totalMap);
            lock.unlock();
        };

        ExecutorService es = Executors.newFixedThreadPool(2);
        while (!queueStrings.isEmpty()) {
            es.submit(countTask);
            es.submit(mergeTask);
        }
        es.shutdown();
        return totalMap;
    }
}
