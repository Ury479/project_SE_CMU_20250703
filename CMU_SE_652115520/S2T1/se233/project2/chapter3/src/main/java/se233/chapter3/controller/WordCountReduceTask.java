package se233.chapter3.controller;

import se233.chapter3.model.FileFreq;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class WordCountReduceTask implements Callable<LinkedHashMap<String, List<FileFreq>>> {
    private Map<String, FileFreq>[] wordMap;
    public WordCountReduceTask(Map<String, FileFreq>[] wordMap) {
        this.wordMap = wordMap;
    }
    @Override
    public LinkedHashMap<String, List<FileFreq>> call() throws Exception {
        LinkedHashMap<String, List<FileFreq>> uniqueSets;
        List<Map<String, FileFreq>> wordMapList = new ArrayList<>(Arrays.asList(wordMap));
        uniqueSets = wordMapList.stream()
                .flatMap(m-> m.entrySet().stream())
                .collect(Collectors.groupingBy(
                        e->e.getKey(),
                        Collector.of(
                                ()-> new ArrayList<FileFreq>(),
                                (list, item)-> list.add(item.getValue()),
                                (current_list, new_items)-> {
                                    current_list.addAll(new_items);
                                    return current_list; })
                ))
                .entrySet()
                .stream()
                .sorted((entry1, entry2) -> {
                    // Sum frequencies for each entry
                    int totalFreq1 = entry1.getValue().stream().mapToInt(FileFreq::getFreq).sum();
                    int totalFreq2 = entry2.getValue().stream().mapToInt(FileFreq::getFreq).sum();
                    // Sort in descending order
                    return Integer.compare(totalFreq2, totalFreq1);
                })
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> e.getValue(),
                        (v1, v2) -> v1,
                        () -> new LinkedHashMap<>()
                ));

        return uniqueSets;
    }
}
