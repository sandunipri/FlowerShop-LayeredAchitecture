package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.pos.bo.custom.StockBO;
import lk.ijse.pos.bo.custom.impl.StockBOImpl;
import lk.ijse.pos.dto.StockDTO;
import lk.ijse.pos.util.Regex;
import lk.ijse.pos.util.TextFeid;

public class StockFormController {
    @FXML
    private Button btnDelete;

    @FXML
    private Button btnadd;

    @FXML
    private AnchorPane rootnodeS;

    @FXML
    private TextField txtFCode;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtSName;

    StockBO stockBO = new StockBOImpl();

    public void setSupplierFormController(SupplierFormController supplierFormController) {
        this.supplierFormController = supplierFormController;
    }

    private SupplierFormController supplierFormController;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String STOCKID =txtSName.getText();
        String FCODE = txtFCode.getText();
        String qty = txtQty.getText();


        StockDTO stock = new StockDTO(STOCKID, FCODE, Integer.parseInt(qty));
        if (isValid()){
            try {

                boolean isadded = stockBO.ADD(stock);
                if (isadded) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Stock added!").show();
                    supplierFormController.getStockID();
                }

            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "something went wrong ").show();

                //clearFields();
            }

        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String STOCKID = txtSName.getText();

        try {
            boolean isDeleted = stockBO.delete(STOCKID);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Stock deleted!").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        }

    }

    public boolean isValid() {
        if (!Regex.setTextColor(TextFeid.StockId, txtSName)) {
            return false;
        }
        if (!Regex.setTextColor(TextFeid.QtyOnHand, txtQty)) {
            return false;
        }
        return true;
    }

    public void txtSNameKeyReleasedOnAction(KeyEvent keyEvent) {
        if (!Regex.setTextColor(TextFeid.StockId, txtSName)) {
        }
    }


    public void txtQtyKeyReleasedOnAction(KeyEvent keyEvent) {
        if (!Regex.setTextColor(TextFeid.QtyOnHand, txtQty)) {
        }
    }
}
