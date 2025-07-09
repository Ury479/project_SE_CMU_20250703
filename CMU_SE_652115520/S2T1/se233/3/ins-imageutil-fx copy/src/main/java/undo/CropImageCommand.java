package com.image.undo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CropImageCommand implements Command {
    private final ImageView imageView;
    private final Image previousImage; // 保存之前的图像
    private Image croppedImage; // 保存裁剪后的图像

    public CropImageCommand(ImageView imageView) {
        this.imageView = imageView;
        this.previousImage = imageView.getImage();
    }

    @Override
    public void execute() {
        // 执行裁剪操作
        croppedImage = performCrop(previousImage);
        imageView.setImage(croppedImage);
    }

    @Override
    public void undo() {
        // 撤销到之前的图像
        imageView.setImage(previousImage);
    }

    private Image performCrop(Image image) {
        // 在这里实现你自己的裁剪逻辑
        // 返回裁剪后的图像
        return image; // 这是个占位符，替换成实际裁剪后的图像
    }
}
