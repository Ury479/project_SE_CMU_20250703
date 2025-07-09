module com.beginsecure.chapter1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.beginsecure.chapter1 to javafx.fxml;
    exports com.beginsecure.chapter1;
    exports se233.chapter1;
    opens se233.chapter1 to javafx.fxml;
}