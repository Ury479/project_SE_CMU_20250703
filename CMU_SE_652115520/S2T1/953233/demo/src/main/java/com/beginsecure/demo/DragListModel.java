package com.beginsecure.demo;

// Required JavaFX imports
import javafx.scene.control.ListView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.event.EventHandler;

public class DragListModel {
    ListView<String> lV;
    public DragListModel() {
        lV = new ListView<String>();
        lV.setPrefSize(200, 200);
        lV.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                Dragboard dragboard = lV.startDragAndDrop(TransferMode.MOVE);
                String selectedItems = lV.getSelectionModel().getSelectedItem();
                ClipboardContent content = new ClipboardContent();
                content.putString(selectedItems);
                dragboard.setContent(content);
                event.consume();
            }
        });
        lV.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                Dragboard dragboard = event.getDragboard();
                if (event.getGestureSource() != lV && dragboard.hasString()) {
                    event.acceptTransferModes(TransferMode.MOVE);
                }
            }
        });
        lV.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                boolean dragCompleted = false;
                Dragboard dragboard = event.getDragboard();
                if (dragboard.hasString()) {
                    String list = dragboard.getString();
                    lV.getItems().addAll(list);
                    dragCompleted = true;
                }
                event.setDropCompleted(dragCompleted);
            }
        });
        lV.setOnDragDone(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                lV.getItems().remove(lV.getSelectionModel().getSelectedItem());
            }
        });
    }
}
