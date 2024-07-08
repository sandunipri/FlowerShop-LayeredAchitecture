package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MainFormController {

    public AnchorPane rootNodeMain;
    @FXML
    private Button CustomerM;

    @FXML
    private Button EmployeeM;

    @FXML
    private Button OrderM;

    @FXML
    private Button StockM;

    @FXML
    private Button SupplierM;

    @FXML
    private Label txtDate;


    @FXML
    private AnchorPane rootNodeMM;

    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;

    @FXML
    private void initialize() throws Exception {
        LocalTime time = LocalTime.now();
        LocalDate date = LocalDate.now();
        lblTime.setText(time.format(DateTimeFormatter.ofPattern("hh:mm a")));
        lblDate.setText(date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        DashBoard();


    }

    private void DashBoard() throws IOException {
        AnchorPane anchorPane= FXMLLoader.load(this.getClass().getResource("/view/dasbBoard_form.fxml"));
        this.rootNodeMM.getChildren().clear();
        this.rootNodeMM.getChildren().add(anchorPane);


    }


    public void btnEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane= FXMLLoader.load(this.getClass().getResource("/view/employee_form.fxml"));
        this.rootNodeMM.getChildren().clear();
        this.rootNodeMM.getChildren().add(anchorPane);

    }

    public void btnSupplierOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane= FXMLLoader.load(this.getClass().getResource("/view/supplier_form.fxml"));
        this.rootNodeMM.getChildren().clear();
        this.rootNodeMM.getChildren().add(anchorPane);
    }

    public void btnCustomerOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane= FXMLLoader.load(this.getClass().getResource("/view/customer_form.fxml"));
        this.rootNodeMM.getChildren().clear();
        this.rootNodeMM.getChildren().add(anchorPane);
    }

    public void btnOrderOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane= FXMLLoader.load(this.getClass().getResource("/view/order_form.fxml"));
        this.rootNodeMM.getChildren().clear();
        this.rootNodeMM.getChildren().add(anchorPane);
    }

   /* public void btnStockOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane= FXMLLoader.load(this.getClass().getResource("/view/stock_form.fxml"));
        this.rootNodeMM.getChildren().clear();
        this.rootNodeMM.getChildren().add(anchorPane);
    }*/

    public void btnhomeOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/home _form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.rootNodeMain.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("login form");
        stage.show();

    }

    public void btnDashboardOnAction(ActionEvent actionEvent) throws IOException {
        DashBoard();
    }


    public void btnPaymentOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane= FXMLLoader.load(this.getClass().getResource("/view/payment_form.fxml"));
        this.rootNodeMM.getChildren().clear();
        this.rootNodeMM.getChildren().add(anchorPane);
    }

    public void btnPEOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane= FXMLLoader.load(this.getClass().getResource("/view/addproductEvent_form.fxml"));
        this.rootNodeMM.getChildren().clear();
        this.rootNodeMM.getChildren().add(anchorPane);
    }
}
