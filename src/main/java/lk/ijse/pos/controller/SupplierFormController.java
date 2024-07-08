package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.SupplierBO;
import lk.ijse.pos.bo.custom.UpdateBO;
import lk.ijse.pos.bo.custom.impl.SupplierBOImpl;
import lk.ijse.pos.bo.custom.impl.UpdateBOImpl;
import lk.ijse.pos.dto.FlowerDTO;
import lk.ijse.pos.dto.SupplierDTO;
import lk.ijse.pos.dto.UpdateDTO;
import lk.ijse.pos.util.Regex;
import lk.ijse.pos.util.TextFeid;
import lk.ijse.pos.view.tdm.SupplierTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SupplierFormController {
    @FXML
    private Button add;

    @FXML
    private JFXComboBox<String> cmFCode;

    @FXML
    private JFXComboBox<String> cmbStockId;

    @FXML
    private JFXComboBox<String> cmbUID;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colFCode;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colQTY;

    @FXML
    private TableColumn<?, ?> colStockId;

    @FXML
    private TableColumn<?, ?> colTelNo;

    @FXML
    private TableColumn<?, ?> colPrice;


    @FXML
    private Label lblSID;

    @FXML
    private AnchorPane rootNodeS;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtFName;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQTY;

    @FXML
    private TextField txtTelNo;

    @FXML
    private TableView<SupplierTm> tblSupplierCart;


    private FlowerFormContriller flowerFormContriller;

    private StockFormController stockFormController;
    private ObservableList<SupplierTm> cartList = FXCollections.observableArrayList();

    @FXML
    private Button update;

    SupplierBO supplierBO = (SupplierBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLIER);

    UpdateBO updateBO = (UpdateBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.UPDATE);
    public void initialize(){
        NextSID();
        getStockID();
        getUserID();
        getFCode();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colTelNo.setCellValueFactory(new PropertyValueFactory<>("TelNo"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colStockId.setCellValueFactory(new PropertyValueFactory<>("StockID"));
        colFCode.setCellValueFactory(new PropertyValueFactory<>("FCode"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("QTY"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));

    }

    public void getFCode() {
        ObservableList<String> observableList  = FXCollections.observableArrayList();
        try{
            List<String> FCOde = supplierBO.getFcode();
            for(String code : FCOde){
                observableList.add(code);
            }
            cmFCode.setItems(observableList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void getUserID() {
        ObservableList<String> observableList  = FXCollections.observableArrayList();
        try{
            List<String> UserID = supplierBO.getuserId();
            for(String USERID : UserID){
                observableList.add(USERID);
            }
            cmbUID.setItems(observableList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void getStockID() {

        ObservableList<String> observableList  = FXCollections.observableArrayList();
        try{
            List<String> StockId = supplierBO.getCodes();
            for(String STOCKID : StockId){
                observableList.add(STOCKID);
            }
            cmbStockId.setItems(observableList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void NextSID() {
        try {
            String currentId = supplierBO.currentSId();
            String nextId = nextSId(currentId);

            lblSID.setText(nextId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String nextSId(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("S");
            int id = Integer.parseInt(split[1],10);
            return "S" + String.format("%04d", ++id);
        }
        return "S0001";
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {

        String Name = txtName.getText();
        String TelNo = txtTelNo.getText();
        String Address = txtAddress.getText();
        String StockId = cmbStockId.getValue();
        String Fcode = cmFCode.getValue();
        int qty = Integer.parseInt(txtQTY.getText());
        double price = Double.parseDouble(txtPrice.getText());

        JFXButton btnRemove = new JFXButton("remove");
        btnRemove.setCursor(Cursor.HAND);

        btnRemove.setOnAction((e) -> {
            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if(type.orElse(no) == yes) {
                cartList.remove(tblSupplierCart.getSelectionModel().getSelectedItem());
                tblSupplierCart.refresh();
                System.out.println("ok");
            }
        });
        SupplierTm supplierTm = new SupplierTm(Name, TelNo, Address, StockId, Fcode, qty, price, btnRemove);

        cartList.add(supplierTm);

        tblSupplierCart.setItems(cartList);
        // txtQTY.setText("");

        tblSupplierCart.refresh();
        //txtQTY.setText("");

    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) throws Exception {

        List<SupplierDTO> supplierList = new ArrayList<>();
        for (int i = 0; i < tblSupplierCart.getItems().size(); i++) {
            SupplierTm supplierTm = cartList.get(i);


            String SID = lblSID.getText();
            String UID = cmbUID.getValue();

            SupplierDTO supplier = new SupplierDTO(
                    SID,
                    supplierTm.getStockID(),
                    UID,
                    supplierTm.getName(),
                    supplierTm.getAddress(),
                    supplierTm.getTelNo(),
                    supplierTm.getFCode(),
                    supplierTm.getQTY(),
                    supplierTm.getPrice()
            );
            supplierList.add(supplier);

        }
        UpdateDTO update1 = new UpdateDTO(supplierList);
        if (isValid()) {
            try {
                boolean isUpdated =updateBO.update(update1);
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Supplier updated!").show();

                } else {
                    new Alert(Alert.AlertType.WARNING, "Supplier not updated").show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }

        }
    }

    @FXML
    void cmbFCodeOnAction(ActionEvent event) {


        String Fcode = cmFCode.getValue();

        try {
            FlowerDTO flower = supplierBO.searchByFlowerCode(Fcode);


            txtFName.setText(flower.getFName());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void txtQtyOnAction(ActionEvent event) {
        btnAddOnAction(event);
    }

    public boolean isValid() {
        if (!Regex.setTextColor(TextFeid.QTY, txtQTY)) {
            return false;
        }
        if (!Regex.setTextColor(TextFeid.Price, txtPrice)) {
            return false;
        }
        if (!Regex.setTextColor(TextFeid.TelNo, txtTelNo)) {
            return false;
        }
        if (!Regex.setTextColor(TextFeid.SName, txtName)) {
            return false;
        }
        if (!Regex.setTextColor(TextFeid.Address, txtAddress)) {
            return false;
        }

        return true;
    }

    public void txtTelNoKeyReleased(KeyEvent keyEvent) {

        if (!Regex.setTextColor(TextFeid.TelNo, txtTelNo)) {}
    }

    public void txtPriceKeyReleased(KeyEvent keyEvent) {
        if (!Regex.setTextColor(TextFeid.Price, txtPrice)) {

        }
    }
    @FXML
    void txtQtyKeyReleased(KeyEvent event) {
        if (!Regex.setTextColor(TextFeid.QTY, txtQTY)) {

        }
    }

    public void txtNameKeyReleased(KeyEvent keyEvent) {
        if (!Regex.setTextColor(TextFeid.SName, txtName)){}
    }

    public void txtAddressKeyReleased(KeyEvent keyEvent) {
        if (!Regex.setTextColor(TextFeid.Address, txtAddress)){}
    }

    public void btnNEWOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/flower_form.fxml"));

        AnchorPane rootNodePayment = loader.load();


        Stage popupStage = new Stage();

        flowerFormContriller = loader.getController();
        flowerFormContriller.setSupplierFormController(this);

        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Popup Window");
        popupStage.setScene(new Scene(rootNodePayment));
        popupStage.showAndWait();
    }

    public void btnNewOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/stock_form.fxml"));

        AnchorPane rootNodePayment = loader.load();


        Stage popupStage = new Stage();

        stockFormController = loader.getController();
        stockFormController.setSupplierFormController(this);

        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Popup Window");
        popupStage.setScene(new Scene(rootNodePayment));
        popupStage.showAndWait();
    }
}
