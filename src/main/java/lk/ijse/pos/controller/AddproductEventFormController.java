package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AddproductEventFormController {
    @FXML
    private AnchorPane rootNodePEADD;
    public void initialize() throws IOException {
        addproduct();

    }

    private void addproduct() throws IOException {
        AnchorPane anchorPane= FXMLLoader.load(this.getClass().getResource("/view/AddProduct_form.fxml"));
        this.rootNodePEADD.getChildren().clear();
        this.rootNodePEADD.getChildren().add(anchorPane);
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {

    }

    public void btnAddOnAction(ActionEvent actionEvent) {

    }

    public void btnAddProductOnAction(ActionEvent actionEvent) throws IOException {
        addproduct();
    }

    public void btnAddEventOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane= FXMLLoader.load(this.getClass().getResource("/view/AddEvent_form.fxml"));
        this.rootNodePEADD.getChildren().clear();
        this.rootNodePEADD.getChildren().add(anchorPane);
    }
}
