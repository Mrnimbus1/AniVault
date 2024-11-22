module com.example.anivault {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires json.simple;

    opens com.example.anivault to javafx.fxml;
    exports com.example.anivault;
}