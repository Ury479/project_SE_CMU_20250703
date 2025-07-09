package se233.chapter3.controller;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import se233.chapter3.Launcher;
import se233.chapter3.model.FileFreq;
import se233.chapter3.model.PdfDocument;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;


public class MainViewController {
    LinkedHashMap<String, List<FileFreq>> uniqueSets;
    @FXML
    private ListView<String> inputListView;
    @FXML
    private Button startButton;
    @FXML
    private ListView<String> listView;

    @FXML
    public void closemenuBar(){
        Launcher.primaryStage.close();
    }

    private Map<String, String> fileNameToPathMap = new HashMap<>();

    @FXML
    public void initialize() {
        inputListView.setOnDragOver(event -> {
            Dragboard db = event.getDragboard();
            final boolean isAccepted = db.getFiles().get(0).getName().toLowerCase().endsWith(".pdf");
            if (db.hasFiles() && isAccepted) {
                event.acceptTransferModes(TransferMode.COPY);
            } else {
                event.consume();
            }
        });

        inputListView.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;

            if (db.hasFiles()) {
                success = true;
                String filePath;
                int totalFiles = db.getFiles().size();

                for (int i = 0; i < totalFiles; i++) {
                    File file = db.getFiles().get(i);
                    filePath = file.getAbsolutePath();
                    String fileName = file.getName();
                    fileNameToPathMap.put(fileName, filePath);
                    inputListView.getItems().add(fileName);
                }
            }
            event.setDropCompleted(success);
            event.consume();
        });



        startButton.setOnAction(event -> {
            Parent bgRoot = Launcher.primaryStage.getScene().getRoot();
            Task<Void> processTask = new Task<>() {
                @Override
                public Void call() throws IOException {
                    ProgressIndicator pi = new ProgressIndicator();
                    VBox box = new VBox(pi);
                    box.setAlignment(Pos.CENTER);
                    Launcher.primaryStage.getScene().setRoot(box);
                    ExecutorService executor = Executors.newFixedThreadPool(4);
                    final ExecutorCompletionService<Map<String, FileFreq>> completionService = new ExecutorCompletionService<>(executor);
                    List<String> inputListViewItems = inputListView.getItems();
                    int totalFiles = inputListViewItems.size();

                    Map<String, FileFreq>[] wordMap = new Map[totalFiles];

                    for (int i = 0; i < totalFiles; i++) {
                        try {
                            String fileName = inputListViewItems.get(i);
                            String filePath = fileNameToPathMap.get(fileName);
                            PdfDocument p = new PdfDocument(filePath);
                            completionService.submit(new WordCountMapTask(p));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    for (int i = 0; i < totalFiles; i++) {
                        try {
                            Future<Map<String, FileFreq>> future = completionService.take();
                            wordMap[i] = future.get();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    try {
                        WordCountReduceTask merger = new WordCountReduceTask(wordMap);
                        Future<LinkedHashMap<String, List<FileFreq>>> future = executor.submit(merger);
                        uniqueSets = future.get();
                        listView.getItems().clear();
                        for (Map.Entry<String, List<FileFreq>> entry : uniqueSets.entrySet()) {
                            String word = entry.getKey();
                            List<FileFreq> fileFreqs = entry.getValue();
                            String displayString = word + " (" + fileFreqs.stream()
                                    .map(FileFreq::getFreq)
                                    .sorted((a, b) -> b - a )
                                    .map(String::valueOf)
                                    .collect(Collectors.joining(", ")) + ")";
                            listView.getItems().add(displayString);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        executor.shutdown();
                    }
                    return null;
                }
            };
            processTask.setOnSucceeded(e -> Launcher.primaryStage.getScene().setRoot(bgRoot));

            Thread thread = new Thread(processTask);
            thread.setDaemon(true);
            thread.start();
        });

        listView.setOnMouseClicked(event -> {
            String selectedItem = listView.getSelectionModel().getSelectedItem();
            if (selectedItem == null) return;
            String selectedWord = selectedItem.split(" ")[0];
            List<FileFreq> listOfLinks = uniqueSets.get(selectedWord);

            ListView<FileFreq> popupListView = new ListView<>();
            LinkedHashMap<FileFreq, String> lookupTable = new LinkedHashMap<>();

            for (FileFreq fileFreq : listOfLinks) {
                lookupTable.put(fileFreq, fileFreq.getPath());
                popupListView.getItems().add(fileFreq);
            }

            popupListView.setPrefHeight(popupListView.getItems().size() * 28);

            popupListView.setOnMouseClicked(innerEvent -> {
                FileFreq selectedFileFreq = popupListView.getSelectionModel().getSelectedItem();
                if (selectedFileFreq == null) return;
                Launcher.hs.showDocument("file:///" + lookupTable.get(selectedFileFreq));
                popupListView.getScene().getWindow().hide();
            });

            Popup popup = new Popup();
            popupListView.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
                if (keyEvent.getCode() == KeyCode.ESCAPE) {
                    popup.hide();
                }
            });

            popupListView.requestFocus();
            popup.getContent().add(popupListView);
            popup.show(Launcher.primaryStage);

        });
    }
}
