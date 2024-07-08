package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.ProductBO;
import lk.ijse.pos.bo.custom.impl.ProductBOImpl;
import lk.ijse.pos.dto.ProductDTO;
import lk.ijse.pos.util.Regex;
import lk.ijse.pos.util.TextFeid;

public class AddproductFormController {
    @FXML
    private Button btnAdd;

    @FXML
    private Button delete;

    @FXML
    private TextField txtPCode;

    @FXML
    private TextField txtPName;

    @FXML
    private TextField txtProductPrice;

    ProductBO productBO = (ProductBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PRODUCT);

    public void setProductOrderFormController   (ProductOrderFormController productOrderFormController) {
        this.productOrderFormController = productOrderFormController;

    }
    private ProductOrderFormController productOrderFormController;
    public void initialize(){
        getProductId();

    }

    private void getProductId() {
        try {
            String currentId = productBO.currentId();
            String nextId = nextId(currentId);

            txtPCode.setText(nextId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String nextId(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("P");
            int id = Integer.parseInt(split[1],10);
            return "P" + String.format("%04d", ++id);
        }
        return "P0001";
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String PCODE = txtPCode.getText();

        try {
            boolean isDeleted = productBO.delete(PCODE);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Product deleted!").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        }
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        String PCODE = txtPCode.getText();
        String PNAME = txtPName.getText();
        double price = Double.parseDouble(txtProductPrice.getText());


        ProductDTO product = new ProductDTO(PCODE, PNAME, price);
        if (isValid()){
            try {

                boolean isadded = productBO.add(product);
                if (isadded) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Flwer added!").show();
                    productOrderFormController.getProductName();
                }

            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "something went wrong ").show();

                //clearFields();
            }

        }
    }
    public boolean isValid() {
        if (!Regex.setTextColor(TextFeid.PName, txtPName)) {
            return false;
        }
        if (!Regex.setTextColor(TextFeid.price, txtProductPrice)) {
            return false;
        }
        return true;
    }

    public void txtPNameKeyRelesedOnAction(KeyEvent keyEvent) {
        if (!Regex.setTextColor(TextFeid.PName, txtPName)) {
        }

    }

    public void txtPpriceKeyReleasedOnAction(KeyEvent keyEvent) {
        if (!Regex.setTextColor(TextFeid.price, txtProductPrice)) {
        }
    }
}
