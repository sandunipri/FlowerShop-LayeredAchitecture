package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeFormController {
    @FXML
    private AnchorPane rootNodeH;
    @FXML
    private AnchorPane rootNodeHH;

    @FXML
    private Button signIN;

    @FXML
    void btnSignOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.rootNodeH.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("login form");
        stage.show();
    }
    public void initialize() throws IOException {
        about();
    }
    @FXML
    private void about() throws IOException {
        AnchorPane anchorPane= FXMLLoader.load(this.getClass().getResource("/view/about_form.fxml"));
        this.rootNodeHH.getChildren().clear();
        this.rootNodeHH.getChildren().add(anchorPane);
    }
    @FXML
    public void btnAboutOnAction(ActionEvent actionEvent) throws IOException {
        about();
    }
}
