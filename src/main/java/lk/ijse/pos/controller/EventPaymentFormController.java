package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import lk.ijse.pos.bo.custom.EventPaymentBO;
import lk.ijse.pos.bo.custom.impl.EventPaymentBOImpl;
import lk.ijse.pos.db.DbConnection;
import lk.ijse.pos.dto.EventOrderDTO;
import lk.ijse.pos.dto.EventPaymentDTO;
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

public class EventPaymentFormController {

    @FXML
    private Button Add;

    @FXML
    private Button bill;

    @FXML
    private JFXComboBox<String> cmbEventOrder;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblPaymentId;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtBalance;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtPaidPayment;

    EventPaymentBO eventPaymentBO = new EventPaymentBOImpl();

    public void initialize() {
        setDate();
        // getCustomerTelNo();
        NextPaymentId();
        getEventOrderNo();
    }

    private void getEventOrderNo() {
        ObservableList<String> observableList  = FXCollections.observableArrayList();
        try{
            List<String> list = eventPaymentBO.getid();
            for(String id : list){
                observableList.add(id);
            }
            cmbEventOrder.setItems(observableList);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void NextPaymentId() {
        try {
            String currentId = eventPaymentBO.CurrentId();
            String nextId = nextId(currentId);

            lblPaymentId.setText(nextId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String nextId(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("I");
            int id = Integer.parseInt(split[1],10);
            return "I" + String.format("%04d", ++id);
        }
        return "I0001";
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        lblDate.setText(String.valueOf(now));
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String paymentId =lblPaymentId.getText();
        String CID = txtCustomerId.getText();
        String POID =cmbEventOrder.getValue();
        Date date = Date.valueOf(LocalDate.now());
        double paidPayment = Double.parseDouble(txtPaidPayment.getText());
        double Amount = Double.parseDouble(txtAmount.getText());
        double balance = Double.parseDouble(txtBalance.getText());

        EventPaymentDTO eventPayment = new EventPaymentDTO(paymentId, CID, POID, date,paidPayment, Amount, balance);
        if (isValid()) {
            try {

                boolean isadded = eventPaymentBO.add(eventPayment);
                if (isadded) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Customer added!").show();
                }

            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "something went wrong ").show();
            }
        }
    }

    @FXML
    void btnBillOnAction(ActionEvent event) throws SQLException, JRException {


        JasperDesign report = JRXmlLoader.load("src/main/resources/reports/EventPayment.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(report);

        Map<String , Object> data = new HashMap<>();
        // System.out.println(data);
        data.put("eventPayment",lblPaymentId.getText());

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, data, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint, false);

    }

    @FXML
    void cmbEventOrderOnAction(ActionEvent event) {
        String id = cmbEventOrder.getValue();

        try {
            EventOrderDTO eventOrder = eventPaymentBO.searchByPOID(id);

            txtCustomerId.setText(eventOrder.getCID());
            txtAmount.setText(String.valueOf(eventOrder.getAmount()));


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void txtCusIdOnAction(ActionEvent event) {

    }
    public boolean isValid(){
        if (!Regex.setTextColor(TextFeid.paidPayment, txtPaidPayment)) {
            return false;
        }
        if (!Regex.setTextColor(TextFeid.Balance, txtBalance)) {
            return false;
        }
        return true;
    }

    public void txtPaymentKeyReleasedOnAction(KeyEvent keyEvent) {
        if (!Regex.setTextColor(TextFeid.paidPayment, txtPaidPayment)) {
        }

    }

    public void txtBalanceKeyReleasedOnAction(KeyEvent keyEvent) {
        if (!Regex.setTextColor(TextFeid.Balance, txtBalance)) {
        }

    }
}
