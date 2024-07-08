package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import lk.ijse.pos.bo.custom.FlowerBO;
import lk.ijse.pos.bo.custom.impl.FlowerBOImpl;
import lk.ijse.pos.dto.FlowerDTO;
import lk.ijse.pos.util.Regex;
import lk.ijse.pos.util.TextFeid;

public class FlowerFormContriller {

    @FXML
    private Button btnadd;

    @FXML
    private TextField txtFCode;

    @FXML
    private TextField txtFname;

    FlowerBO flowerBO = new FlowerBOImpl();

    public void setSupplierFormController(SupplierFormController supplierFormController) {
        this.supplierFormController = supplierFormController;
    }

    private SupplierFormController supplierFormController;
    public void initialize() {
        nextFcode();

    }

    private void nextFcode() {
        try {
            String currentId = flowerBO.currentCode();
            String nextId = nextId(currentId);

            txtFCode.setText(nextId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String nextId(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("F");
            int id = Integer.parseInt(split[1],10);
            return "F" + String.format("%04d", ++id);
        }
        return "F0001";
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String FCODE = txtFCode.getText();
        String FNAME = txtFname.getText();


        FlowerDTO flower = new FlowerDTO(FCODE, FNAME);
        if (isValid()){
            try {

                boolean isadded = flowerBO.add(flower);
                if (isadded) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Flwer added!").show();
                    supplierFormController.getFCode();
                }

            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "something went wrong ").show();

                //clearFields();
            }

        }
    }
    @FXML
    void btnDeleteIOnAction(ActionEvent event) {
        String Fcode = txtFCode.getText();

        try {
            boolean isDeleted = flowerBO.delete(Fcode);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Flower deleted!").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        }

    }
    public boolean isValid() {
        if (!Regex.setTextColor(TextFeid.FName, txtFname)) {
            return false;
        }
        return true;
    }

    public void txtFNameKeyReleasedOnAction(KeyEvent keyEvent) {
        if (!Regex.setTextColor(TextFeid.FName, txtFname)) {
        }
    }
}
