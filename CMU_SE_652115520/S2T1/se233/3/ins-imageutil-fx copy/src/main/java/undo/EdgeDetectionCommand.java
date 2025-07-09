package com.image.undo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EdgeDetectionCommand implements Command {
    private final ImageView imageView;
    private final Image previousImage; // 保存之前的图像
    private Image newImage; // 保存操作后的图像

    public EdgeDetectionCommand(ImageView imageView) {
        this.imageView = imageView;
        this.previousImage = imageView.getImage();
    }

    @Override
    public void execute() {
        // 执行边缘检测操作
        newImage = performEdgeDetection(previousImage);
        imageView.setImage(newImage);
    }

    @Override
    public void undo() {
        // 撤销到之前的图像
        imageView.setImage(previousImage);
    }

    private Image performEdgeDetection(Image image) {
        // 在这里实现你自己的边缘检测逻辑
        // 返回处理后的图像
        return image; // 这是个占位符，替换成实际处理后的图像
    }
}
