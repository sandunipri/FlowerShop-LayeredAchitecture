package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class OrderFormController {
    @FXML
    private Button event;

    @FXML
    private Button products;

    @FXML
    private AnchorPane rootNodeO;

    @FXML
    private AnchorPane rootNodeOO;
    public void initialize() throws IOException {
        productEvent();
    }

    @FXML
    private void productEvent() throws IOException {
        AnchorPane anchorPane= FXMLLoader.load(this.getClass().getResource("/view/productEvent_form.fxml"));
        this.rootNodeOO.getChildren().clear();
        this.rootNodeOO.getChildren().add(anchorPane);

    }

    @FXML
    void btnEventOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane= FXMLLoader.load(this.getClass().getResource("/view/eventOrder_Form.fxml"));
        this.rootNodeOO.getChildren().clear();
        this.rootNodeOO.getChildren().add(anchorPane);

    }

    @FXML
    void btnProductOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane= FXMLLoader.load(this.getClass().getResource("/view/ProductOrder_form.fxml"));
        this.rootNodeOO.getChildren().clear();
        this.rootNodeOO.getChildren().add(anchorPane);
    }

    public void btnPEOnAction(ActionEvent actionEvent) throws IOException {
        productEvent();

    }

    public void btnbackOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/main_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.rootNodeO.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("dashBoard");
        stage.show();
    }
}
