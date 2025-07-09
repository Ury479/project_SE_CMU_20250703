package se233.chapter3.controller;

import se233.chapter3.model.FileFreq;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class WordMapMergeTask implements Callable<LinkedHashMap<String, ArrayList<FileFreq>>> {
    private Map<String, FileFreq>[] wordMap;

    public WordMapMergeTask(Map<String, FileFreq>[] wordMap) {
        this.wordMap = wordMap;
    }

    @Override
    public LinkedHashMap<String, ArrayList<FileFreq>> call() throws Exception {
        LinkedHashMap<String, ArrayList<FileFreq>> uniqueSets = new LinkedHashMap<String, ArrayList<FileFreq>>();
        ;
        List<Map<String, FileFreq>> wordMapList = new ArrayList<>(Arrays.asList(
                wordMap));
        uniqueSets = wordMapList.stream()
                .flatMap(m -> m.entrySet().stream())
                .collect(Collectors.groupingBy(e -> e.getKey(),
                        Collector.of(
                                () -> new ArrayList<FileFreq>(),
                                (list, item) -> {

                                    list.add(item.getValue());
                                },
                                (current_list, new_items) -> {
                                    current_list.addAll(new_items);
                                    return current_list;
                                })

                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .sorted(Map.Entry.comparingByValue(new Comparator<ArrayList<FileFreq>>() {
                    @Override
                    public int compare(ArrayList<FileFreq> o1, ArrayList<FileFreq> o2) {
                        return Integer.compare(o2.get(0).freq, o1.get(0).freq);
                    }
                }))
                .collect(Collectors.toMap(e -> String.format("%-15s %s", e.getKey(), listToString(e.getValue())), e -> e.getValue(),
                        (v1, v2) -> v1, () -> new LinkedHashMap<>()));

        return uniqueSets;
    }

    private String listToString(ArrayList<FileFreq> fileFreqs) {
        ArrayList<Integer> arrayFreq = new ArrayList<Integer>();
        System.out.println(fileFreqs.size());

        fileFreqs.forEach(x -> arrayFreq.add(x.freq));

        return arrayFreq.toString();
    }

}