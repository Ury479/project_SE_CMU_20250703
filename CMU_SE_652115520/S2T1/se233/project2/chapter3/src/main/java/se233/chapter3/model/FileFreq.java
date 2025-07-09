package se233.chapter3.model;

public class FileFreq {
    private String name;
    private String path;
    private Integer freq;
    public FileFreq(String name,String path , Integer freq){
        this.name = name;
        this.path = path;
        this.freq = freq;
    }

    @Override
    public String toString() {
        return String.format("{%s:%d}",name,freq);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getFreq() {
        return freq;
    }

    public void setFreq(Integer freq) {
        this.freq = freq;
    }


}
