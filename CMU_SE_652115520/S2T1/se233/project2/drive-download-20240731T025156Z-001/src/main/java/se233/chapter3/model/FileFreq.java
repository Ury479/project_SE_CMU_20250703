package se233.chapter3.model;

import java.util.ArrayList;

public class FileFreq extends ArrayList<FileFreq> {


    private String Name;
    private String path;
    private int Freq;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getFreq() {
        return Freq;
    }

    public void setFreq(int freq) {
        Freq = freq;
    }

    public FileFreq(String Name, String path, int Freq) {
        this.Name = Name;
        this.path = path;
        this.Freq = Freq;
    }
    @Override
    public String toString() {
        return String.format("{%s:%d}", Name, Freq);
    }

    public String getPath() {
        return path;
    }
}
