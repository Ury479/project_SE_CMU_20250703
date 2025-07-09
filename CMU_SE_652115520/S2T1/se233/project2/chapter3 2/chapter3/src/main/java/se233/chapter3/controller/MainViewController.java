package se233.chapter3.controller;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import se233.chapter3.Launcher;
import se233.chapter3.model.FileFreq;
import se233.chapter3.model.PDFdocument;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MainViewController {
    LinkedHashMap<String, ArrayList<FileFreq>> uniqueSets;
    @FXML
    private ListView<String> inputListView;
    @FXML
    private Button startButton;
    @FXML
    private ListView listView;
    @FXML
    private MenuItem closeButton;
    @FXML
    private MenuItem deleteButton;
    @FXML
    private MenuItem aboutButton;
    ArrayList<String> filePaths = new ArrayList<>();
    private Button cancelButton = new Button("Cancel");

    @FXML
    public void initialize() {


        inputListView.setOnDragOver(dragEvent -> {
            Dragboard db = dragEvent.getDragboard();
            final boolean isAccepted = db.getFiles().get(0).getName().toLowerCase().endsWith(".pdf");
            if (db.hasFiles() && isAccepted) {
                dragEvent.acceptTransferModes(TransferMode.COPY);
            } else {
                dragEvent.consume();
            }
        });
        deleteButton.setOnAction(actionEvent -> {
            inputListView.getItems().remove(inputListView.getSelectionModel().getSelectedItem());
        });
        aboutButton.setOnAction(actionEvent -> {
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.showAndWait();

        });
        inputListView.setOnDragDropped(dragEvent -> {
            Dragboard db = dragEvent.getDragboard();
            boolean success = false;
            if (db.hasFiles()) {
                success = true;
                String filePath;
                int total_files = db.getFiles().size();
                for (int i = 0; i < total_files; i++) {
                    File file = db.getFiles().get(i);
                    filePath = file.getAbsolutePath();
                    String fileName = file.getName();
                    filePaths.add(filePath);
//                    inputListView.getItems().addAll(filePath);
                    inputListView.getItems().addAll(fileName);
                }
            }
            dragEvent.setDropCompleted(success);
            dragEvent.consume();
        });
        startButton.setOnAction(event -> {
            Parent bgRoot = Launcher.stage.getScene().getRoot();
            Task<Void> processTask = new Task<Void>() {
                @Override
                public Void call() throws IOException {
                    ProgressIndicator pi = new ProgressIndicator();
                    VBox box = new VBox(pi);
                    box.setAlignment(Pos.CENTER);
                    Launcher.stage.getScene().setRoot(box);
                    ExecutorService executor = Executors.newFixedThreadPool(4);
                    final ExecutorCompletionService<Map<String, FileFreq>> completionService = new
                            ExecutorCompletionService<>(executor);
                    List<String> inputListViewItems = inputListView.getItems();
                    int total_files = inputListViewItems.size();
                    System.out.println("Total File "+total_files);
                    Map<String, FileFreq>[] wordMap = new Map[total_files];
                    for (int i = 0; i < total_files; i++) {
                        try {
//                            String filePath = inputListViewItems.get(i);
                            String filePath = filePaths.get(i);
                            PDFdocument p = new PDFdocument(filePath);
                            completionService.submit(new WordMapPageTask(p));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    for (int i = 0; i < total_files; i++) {
                        try {
                            Future<Map<String, FileFreq>> future = completionService.take();
                            wordMap[i] = future.get();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        WordMapMergeTask merger = new WordMapMergeTask(wordMap);
                        Future<LinkedHashMap<String, ArrayList<FileFreq>>> future = executor.submit(merger);
                        uniqueSets = future.get();

                        listView.getItems().addAll(uniqueSets.keySet());
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        executor.shutdown();
                    }
                    return null;
                }
            };
            processTask.setOnSucceeded(e -> {
                Launcher.stage.getScene().setRoot(bgRoot);
            });
            Thread thread = new Thread(processTask);
            thread.setDaemon(true);
            thread.start();
        });
        listView.setOnMouseClicked(event -> {
            ArrayList<FileFreq> listOfLinks = uniqueSets.get(listView.getSelectionModel()
                    .getSelectedItem());
            ListView<FileFreq> popupListView = new ListView<>();
            LinkedHashMap<FileFreq, String> lookupTable = new LinkedHashMap<>();
            for (int i = 0; i < listOfLinks.size(); i++) {
                lookupTable.put(listOfLinks.get(i), listOfLinks.get(i).getPath());
                popupListView.getItems().add(listOfLinks.get(i));
            }
            popupListView.setPrefHeight(popupListView.getItems().size() * 28);
            popupListView.setOnMouseClicked(innerEvent -> {
                Launcher.hs.showDocument("file:///" + lookupTable.get(popupListView
                        .getSelectionModel().getSelectedItem()));
                popupListView.getScene().getWindow().hide();
            });
            Popup popup = new Popup();
            popup.getContent().add(popupListView);
            popup.show(Launcher.stage);
            popupListView.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    KeyCode key = keyEvent.getCode();
                    if (key == KeyCode.ESCAPE)
                        popup.hide();
                }
            });
        });

        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage stage = (Stage) Launcher.stage.getScene().getWindow();
                stage.close();
            }
        });
    }
}

