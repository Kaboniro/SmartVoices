package com.example.smartvoices;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class dashController {
    @FXML
    void loadConvert(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("convert.fxml"));
            Parent root = loader.load();

            // create a new stage to display the new FXML file
            Stage stage = new Stage();
            stage.setTitle("Convert TTS");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
