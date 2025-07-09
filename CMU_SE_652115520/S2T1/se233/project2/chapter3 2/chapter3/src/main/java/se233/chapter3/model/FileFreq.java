package se233.chapter3.model;

public class FileFreq {

    public String name;
    private String path;
    public Integer freq;


    public String getPath() {
        return path;
    }

    public FileFreq(String name, String path, Integer freq) {
        this.name = name;
        this.path = path;
        this.freq = freq;
    }

    @Override
    public String toString() {
        return String.format("{%s:%d}", name, freq);
    }
}