module se233.chapter3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires pdfbox;
            
                            
    opens se233.chapter3 to javafx.fxml;
    opens se233.chapter3.controller to javafx.fxml;
    exports se233.chapter3;
    exports se233.chapter3.controller;
}