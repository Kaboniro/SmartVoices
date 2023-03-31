module com.example.smartvoices {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires eu.hansolo.tilesfx;
    requires java.desktop;
    requires java.sql;
    requires mysql.connector.j;
    requires marytts.builder;


    opens com.example.smartvoices to javafx.fxml;
    exports com.example.smartvoices;
}