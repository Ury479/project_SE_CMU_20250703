package com.image;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Transform;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class MainController {

    private String suffix;

    private double iWidth;
    private double iHeight;
    private double pWidth;
    private double pHeight;

    private double oldPWidth;
    private double oldPHeight;
    private double pX;
    private double pY;
    private double wX;
    private double wY;
    private double wFont;

    @FXML
    private AnchorPane imagePane;
    @FXML
    private ImageView imageView;
    @FXML
    private Label imageLabel;


    @FXML
    private AnchorPane waterMarkConfig;
    @FXML
    private AnchorPane imageConfig;
    @FXML
    private Button export;


    @FXML
    private Label waterMark;
    @FXML
    private TextField waterMarkText;
    @FXML
    private Slider waterMarkSize;
    @FXML
    private Slider waterMarkRotate;
    @FXML
    private ColorPicker waterMarkColor;
    @FXML
    private Slider waterMarkOpacity;

    @FXML
    private Slider imageSize;
    @FXML
    private TextField imageWidth;
    @FXML
    private Label wInfo;
    @FXML
    private TextField imageHeight;
    @FXML
    private Label hInfo;

    @FXML
    public void initialize() {
        System.out.println("init");
    }

    public void imageDragOver(DragEvent event){
        if (event.getDragboard().hasFiles() || event.getDragboard().hasUrl()) {
            event.acceptTransferModes(javafx.scene.input.TransferMode.COPY);
            imagePane.setStyle("-fx-background-color: #aaaaaa; -fx-border-color: red;");
        }
        event.consume();
    }
    public void imageDragDropped(DragEvent event) throws IOException {
        if (event.getDragboard().hasFiles()) {
            File file = event.getDragboard().getFiles().get(0);
            suffix = getFileSuffix(file.getName());
            List<String> fileSuffixs = new ArrayList<>();
            fileSuffixs.add("png");
            fileSuffixs.add("jpg");
            fileSuffixs.add("jpeg");
            fileSuffixs.add("zip");
            if (!fileSuffixs.contains(suffix)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("please select 'png、jpg、jpeg' file");
                alert.show();
                imagePane.setStyle("-fx-background-color: #eeeeee; -fx-border-color: #dddddd;");
                return;
            }
            List<Image> images = new ArrayList<>();
             if (suffix.equals("zip")){
                ZipFile zipFile = new ZipFile(file);
                Enumeration<? extends ZipEntry> entries = zipFile.entries();

                while(entries.hasMoreElements()){
                    ZipEntry entry = entries.nextElement();
                    System.out.println("fileName:"+entry.getName()); //文件名
                    InputStream stream = zipFile.getInputStream(entry); //读取文件内容
                    Image image = new Image(stream);
                    if (image.getWidth() > 0 && image.getHeight() >0) {
                        images.add(image);
                    }
                }
            }else{
                Image image = new Image("file:" + file.getAbsolutePath());
                images.add(image);
            }

            this.reset();

            imageView.setImage(images.stream().findFirst().get());
            iWidth = imageView.getImage().getWidth();
            iHeight = imageView.getImage().getHeight();
            pWidth = imagePane.getPrefWidth();
            pHeight = imagePane.getPrefHeight();
            pX = imagePane.getLayoutX();
            pY = imagePane.getLayoutY();

            wX = waterMark.getLayoutX();
            wY = waterMark.getLayoutY();
            wFont = waterMark.getFont().getSize();

            imagePane.setStyle("-fx-background-color: #ffffff; -fx-border-color: #00000000; -fx-border-width: 0px;");
            imageLabel.setText("");
            this.centerImage(1);
            oldPWidth = imagePane.getPrefWidth();
            oldPHeight = imagePane.getPrefHeight();

            imageWidth.setText(String.format("%.0f", oldPWidth));
            imageHeight.setText(String.format("%.0f", oldPHeight));

            waterMarkConfig.setVisible(true);
            imageConfig.setVisible(true);
            export.setVisible(true);
        }
        event.setDropCompleted(true);
        event.consume();
    }



    public void changeWaterMarkText(KeyEvent event) {
        waterMark.setText(waterMarkText.getText());
    }

    public void changeWaterMarkSize(MouseEvent event) {
        waterMark.setFont(new Font(waterMarkSize.getValue()));
        wFont = waterMarkSize.getValue();
    }

    public void changeWaterMarkRotate(MouseEvent event) {
        waterMark.setRotate(waterMarkRotate.getValue());
    }

    public void changeWaterMarkColor(ActionEvent event) {
        waterMark.setTextFill(waterMarkColor.getValue());
    }

    public void changeWaterMarkOpacity(MouseEvent event) {
        waterMark.setOpacity(waterMarkOpacity.getValue());
    }

    public void imageSize(MouseEvent event) {
        double value = imageSize.getValue() * 0.01;

        imageView.setPreserveRatio(true);
        this.centerImage(value);

        imageWidth.setText(String.format("%.0f", imagePane.getPrefWidth()));
        imageHeight.setText(String.format("%.0f", imagePane.getPrefHeight()));
    }

    public void imageWidth(KeyEvent event) {
        if (imageWidth.getText() == null || imageWidth.getText().equals("")){
            return;
        }
        double value;
        try {
            value = Double.parseDouble(imageWidth.getText());
        } catch (Exception e){
            wInfo.setText("please enter a number");
            wInfo.setVisible(true);
            return;
        }
        if (value <= 0){
            return;
        }
        double radio = value / oldPWidth;
        if (radio < 0.5 || radio> 1.5) {
            wInfo.setText("between "+String.format("%.0f", 0.5*oldPWidth)+" and "+String.format("%.0f", 1.5*oldPWidth));
            wInfo.setVisible(true);
            return;
        }else{
            wInfo.setText("");
            wInfo.setVisible(false);
        }

        this.centerImage(radio);
        imageSize.setValue(radio * 100);
        imageHeight.setText(String.format("%.0f", oldPHeight * imageSize.getValue() * 0.01));
    }

    public void imageHeight(KeyEvent event) {
        if (imageHeight.getText() == null || imageHeight.getText().equals("")){
            return;
        }
        double value;
        try {
            value = Double.parseDouble(imageHeight.getText());
        } catch (Exception e){
            hInfo.setText("please enter a number");
            hInfo.setVisible(true);
            return;
        }
        if (value <= 0){
            return;
        }
        double radio = value / oldPHeight;
        if (radio < 0.5 || radio>1.5) {
            hInfo.setText("between "+String.format("%.0f",0.5*oldPHeight)+" and "+String.format("%.0f",1.5*oldPHeight));
            hInfo.setVisible(true);
            return;
        }else{
            hInfo.setText("");
            hInfo.setVisible(false);
        }

        this.centerImage(radio);
        imageSize.setValue(radio*100);
        imageWidth.setText(String.format("%.0f", oldPWidth * imageSize.getValue() * 0.01));
    }

    public void exportNewImage(ActionEvent event){
        SnapshotParameters sp =  new SnapshotParameters();
        sp.setTransform(Transform.scale(5, 5));

        WritableImage imgReturn = imagePane.snapshot(sp, null);
        DirectoryChooser directoryChooser =new DirectoryChooser();
        File directory = directoryChooser.showDialog(new Stage());
        String fileName = directory + "/newImage." + suffix;
        File file = new File(fileName);
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(imgReturn, null);
        boolean write = false;
        try {
            write = ImageIO.write(bufferedImage, "png", file);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("file write error");
            alert.show();
            System.out.println(e);
            return;
        }
        if (write){
            file.renameTo(new File(this.getFileName(file.getPath()) + "." + suffix));
        }
    }

    private void centerImage(double size) {
        double newWidth = pWidth * size;
        double newHeight = pHeight * size;
        Image img = imageView.getImage();
        if (img != null) {
            double w = 0;
            double h = 0;

            double reducCoeff = this.getReducCoeff(newWidth, newHeight);

            w = iWidth * reducCoeff;
            h = iHeight * reducCoeff;

            imagePane.setLayoutX(pX + (pWidth - w) / 2);
            imagePane.setLayoutY(pY + (pHeight - h) / 2);
            waterMark.setLayoutX(wX * reducCoeff);
            waterMark.setLayoutY(wY * reducCoeff);
            waterMark.setFont(new Font(wFont*size ));

            imagePane.setPrefWidth(w);
            imagePane.setMaxWidth(w);
            imagePane.setMinWidth(w);

            imagePane.setPrefHeight(h);
            imagePane.setMaxHeight(h);
            imagePane.setMinHeight(h);

            imageView.setFitWidth(w);
            imageView.setFitHeight(h);


        }
    }
    private double getReducCoeff(double newWidth, double newHeight){
        double ratioW = newWidth / iWidth;
        double ratioH = newHeight / iHeight;
        double reducCoeff = 0;
        if(ratioW >= ratioH) {
            reducCoeff = ratioH;
        } else {
            reducCoeff = ratioW;
        }
        return reducCoeff;
    }

    private void reset(){
        if (pWidth>0) {
            imagePane.setPrefWidth(pWidth);
        }
        if (pHeight>0) {
            imagePane.setPrefHeight(pHeight);
        }

        imagePane.setLayoutX(150);
        imagePane.setLayoutY(60);

        waterMark.setText("");
        waterMark.setFont(new Font(36));
        waterMark.setRotate(0);
        waterMark.setTextFill(Color.web("#333333"));
        waterMark.setOpacity(0.5);

        waterMarkText.setText("");
        waterMarkSize.setValue(36);
        waterMarkRotate.setValue(0);
        waterMarkColor.setValue(Color.web("#333333"));
        waterMarkOpacity.setValue(0.5);

        imageSize.setValue(100);
        imageWidth.setText("");
        imageHeight.setText("");
    }

    private String getFileName(String name){
        return name.substring(0, name.lastIndexOf("."));
    }

    private String getFileSuffix(String name){
        return name.substring(name.lastIndexOf(".")+1);
    }
}
