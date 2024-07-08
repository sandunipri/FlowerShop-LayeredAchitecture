package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.ProductPaymentBO;
import lk.ijse.pos.bo.custom.impl.ProductPaymentBOImpl;
import lk.ijse.pos.db.DbConnection;
import lk.ijse.pos.dto.ProductOrderDTO;
import lk.ijse.pos.dto.ProductPaymentDTO;
import lk.ijse.pos.util.Regex;
import lk.ijse.pos.util.TextFeid;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductPaymentController {
    @FXML
    private JFXComboBox<String> cmbCustomerTelNo;

    @FXML
    private JFXComboBox<String> cmbProductOrder;

    @FXML
    private Label lblPaymentId;

    @FXML
    private TextField txtAmount;

    @FXML
    private Label lblDate;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtBalance;

    @FXML
    private TextField txtCustomerName;



    @FXML
    private TextField txtcustomerTelNo;

    @FXML
    private TextField txtPaidPayment;

    ProductPaymentBO productPaymentBO = (ProductPaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PRODUCTPAYMENT);

    String paymentId;

    public void initialize() {
        setDate();
        // getCustomerTelNo();
        NextPaymentId();
        getProductOrderNo();
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        lblDate.setText(String.valueOf(now));
    }

    private void getProductOrderNo() {
        ObservableList<String> observableList  = FXCollections.observableArrayList();
        try{
            List<String> list = productPaymentBO.getid();
            for(String id : list){
                observableList.add(id);
            }
            cmbProductOrder.setItems(observableList);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void NextPaymentId() {
        try {
            String currentId = productPaymentBO.currentId();
            String nextId = nextId(currentId);

            lblPaymentId.setText(nextId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private String nextId(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("X");
            int id = Integer.parseInt(split[1],10);
            return "X" + String.format("%04d", ++id);
        }
        return "X0001";
    }

    @FXML
    void cmbProductOrderOnAction(ActionEvent event) {
        String id = cmbProductOrder.getValue();

        try {
            ProductOrderDTO productOrder = productPaymentBO.searchByPOID(id);

            txtCustomerId.setText(productOrder.getCID());


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    public void btnAddOnAction(ActionEvent actionEvent) {
        paymentId =lblPaymentId.getText();
        String CID = txtCustomerId.getText();
        String POID =cmbProductOrder.getValue();
        Date date = Date.valueOf(LocalDate.now());
        double paidPayment = Double.parseDouble(txtPaidPayment.getText());
        double Amount = Double.parseDouble(txtAmount.getText());
        double balance = Double.parseDouble(txtBalance.getText());

        ProductPaymentDTO productPayment = new ProductPaymentDTO(paymentId, CID, POID, date,paidPayment, Amount, balance);
        if (isValid()) {
            try {

                boolean isadded = productPaymentBO.add(productPayment);
                if (isadded) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Customer added!").show();
                }

            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "something went wrong ").show();
            }
        }
        // clearFields();

    }
    @FXML
    public void btnBillOnAction(ActionEvent actionEvent) throws JRException, SQLException {

        JasperDesign report = JRXmlLoader.load("src/main/resources/reports/Paymentproduct_report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(report);

        Map<String , Object> data = new HashMap<>();
        // System.out.println(data);
        data.put("productPayment",lblPaymentId.getText());

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, data, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint, false);

    }

    public void txtCusIdOnAction(ActionEvent actionEvent) {
    }

    public boolean isValid() {
        if (!Regex.setTextColor(TextFeid.paidPayment, txtPaidPayment)) {
            return false;
        }

        if (!Regex.setTextColor(TextFeid.Amount, txtAmount)) {
            return false;
        }
        if (!Regex.setTextColor(TextFeid.Balance, txtBalance)) {
            return false;
        }
        return true;
    }
    public void txtAmounttKeyReleasedOnAction(KeyEvent keyEvent) {
        if (!Regex.setTextColor(TextFeid.Amount, txtAmount)) {
        }
    }

    public void txtBalanceKeyReleasedOnAction(KeyEvent keyEvent) {
        if (!Regex.setTextColor(TextFeid.Balance, txtBalance)
        ){}
    }
    public void txtPaymentKeyReleasedOnAction(KeyEvent keyEvent) {
        if (!Regex.setTextColor(TextFeid.paidPayment, txtPaidPayment)
        ){}
    }
}
