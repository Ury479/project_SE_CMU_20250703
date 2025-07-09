module se233.chapter5_2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.slf4j;
    requires org.apache.logging.log4j;

    opens se233.chapter5_2 to javafx.fxml;
    exports se233.chapter5_2;
}