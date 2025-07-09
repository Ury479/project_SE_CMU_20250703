package se233.chapter3.model;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;

public class PdfDocument {
    private String name;
    private String filePath;
    private PDDocument document;
    private LinkedHashMap<String, List<FileFreq>> uniqueSets;

    public PdfDocument(String filePath) throws IOException {
        this.name =  Paths.get(filePath).getFileName().toString();
        this.filePath = filePath;
        File pdfFile = new File(filePath);
        this.document = PDDocument.load(pdfFile);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public PDDocument getDocument() {
        return document;
    }

    public void setDocument(PDDocument document) {
        this.document = document;
    }

    public LinkedHashMap<String, List<FileFreq>> getUniqueSets() {
        return uniqueSets;
    }

    public void setUniqueSets(LinkedHashMap<String, List<FileFreq>> uniqueSets) {
        this.uniqueSets = uniqueSets;
    }
}
