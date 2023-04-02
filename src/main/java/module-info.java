module com.example.smartvoices {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;

    requires java.desktop;
    requires java.sql;
    requires javafx.media;
//    requires marytts.runtime;
//    requires marytts.common;
//    requires marytts.signalproc;


    opens com.example.smartvoices to javafx.fxml;
    exports com.example.smartvoices;
}