package com.example.smartvoices;



import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class dashController{
    public Button convert;

    @FXML
    void loadConvert() {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("convert.fxml"));
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setTitle("Convert TTS");
            stage.setScene(scene);
            stage.show();
        }


    }

