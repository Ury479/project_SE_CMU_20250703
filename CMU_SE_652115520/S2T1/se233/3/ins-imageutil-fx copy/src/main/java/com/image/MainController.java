package com.image;

import com.image.undo.CommandManager;
import com.image.undo.EdgeDetectionCommand;
import com.image.undo.CropImageCommand;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;

public class MainController {
    @FXML
    private ImageView imageView;
    @FXML
    private Button detectEdgeBtn;
    @FXML
    private Button cropImageBtn;
    @FXML
    private Button undoBtn;

    private CommandManager commandManager = new CommandManager();

    @FXML
    private void detectEdge(ActionEvent event) {
        // 创建并执行边缘检测命令
        EdgeDetectionCommand command = new EdgeDetectionCommand(imageView);
        commandManager.executeCommand(command);

        // 更新撤销按钮状态
        undoBtn.setDisable(!commandManager.canUndo());
    }

    @FXML
    private void cropImage(ActionEvent event) {
        // 创建并执行裁剪命令
        CropImageCommand command = new CropImageCommand(imageView);
        commandManager.executeCommand(command);

        // 更新撤销按钮状态
        undoBtn.setDisable(!commandManager.canUndo());
    }

    @FXML
    private void undo(ActionEvent event) {
        // 撤销最后一个命令
        commandManager.undo();

        // 更新撤销按钮状态
        undoBtn.setDisable(!commandManager.canUndo());
    }
}
