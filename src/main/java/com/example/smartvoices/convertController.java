package com.example.smartvoices;

import javafx.stage.FileChooser;
import marytts.LocalMaryInterface;
import marytts.MaryInterface;
import marytts.exceptions.MaryConfigurationException;
import marytts.exceptions.SynthesisException;
import marytts.util.data.audio.AudioPlayer;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class convertController {


    @FXML
    private TextArea inputTextArea;

    private Connection connection;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/smartvoices";
    private static final String DB_USERNAME = "emma";
    private static final String DB_PASSWORD = "namaste";

    @FXML
    private void convertAndSave() throws MaryConfigurationException {
        MaryInterface marytts = new LocalMaryInterface();
        AudioPlayer audioPlayer = new AudioPlayer();

        String text = inputTextArea.getText();
        if(!text.isEmpty()){
            try {
                AudioInputStream audioInputStream = marytts.generateAudio(text);
                audioPlayer.setAudio(audioInputStream);

                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save Audio file");
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("WAV files *.wav"));
                File audiofile = fileChooser.showSaveDialog(inputTextArea.getScene().getWindow());

                if(audiofile != null){
                    AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, audiofile);

                }
                String path = audiofile.getAbsolutePath();

                String hash = computeHash(path);
                saveToDatabase(path, hash);

            } catch (SynthesisException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private String computeHash(String path) {
        String hashh = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            InputStream is = new FileInputStream(path);
            byte[] buffer = new byte[1024];
            int nread;
            while ((nread = is.read(buffer)) != -1) {
                md.update(buffer, 0, nread);
            }
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            hashh = sb.toString();
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
        return hashh;
    }

    private void saveToDatabase(String path, String hash) {
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            }
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO audios (path, hash) VALUES (?, ?)");
            preparedStatement.setString(1, path);
            preparedStatement.setString(2, hash);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
