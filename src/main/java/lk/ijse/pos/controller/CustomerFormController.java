package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.pos.bo.custom.CustomerBO;
import lk.ijse.pos.bo.custom.impl.CustomerBOImpl;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.UserDTO;
import lk.ijse.pos.util.Regex;
import lk.ijse.pos.util.TextFeid;
import lk.ijse.pos.view.tdm.CustomerTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerFormController {
    @FXML
    private JFXComboBox<String> Cidcombox;
    @FXML
    private JFXComboBox<String> cmbUID;

    @FXML
    private Button add;

    @FXML
    private Button back;

    @FXML
    private Button delete;

    @FXML
    private AnchorPane rootnodeC;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCid;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtTelNo;

    @FXML
    private TextField txtUid;

    @FXML
    private Button update;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colTelNo;

    @FXML
    private TableColumn<?, ?> colUID;


    @FXML
    private TableView<CustomerTm> tblCustomercart;
    private List<CustomerDTO> customerList = new ArrayList<>();

    CustomerBO customerBO = new CustomerBOImpl();

    public void setEventOrderFormController(EventOrderFormController eventOrderFormController) {
        this.eventOrderFormController = eventOrderFormController;

    }
    private EventOrderFormController eventOrderFormController;

    public void setProductOrderFormController(ProductOrderFormController productOrderFormController) {
        this.productOrderFormController = productOrderFormController;

    }
    private ProductOrderFormController productOrderFormController;

    public void initialize(){
        this.customerList = getAllCustomers();
        getCustomerId();
        setCellValueFactory();
        loadCustomerTable();
        nextCusId();
        getUserId();


    }

    private void getUserId() {
        ObservableList<String> observableList  = FXCollections.observableArrayList();
        try{
            List<String> IDList = customerBO.getUserId();
            for(String C_id : IDList){
                observableList.add(C_id);
            }
            cmbUID.setItems(observableList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void nextCusId() {
        try {
            String currentId = customerBO.currentId();
            String nextId = nextId(currentId);

            txtCid.setText(nextId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String nextId(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("C");
            int id = Integer.parseInt(split[1],10);
            return "C" + String.format("%04d", ++id);
        }
        return "C0001";
    }

    private void loadCustomerTable() {
        ObservableList<CustomerTm> tmList = FXCollections.observableArrayList();

        for (CustomerDTO customer : customerList) {
            CustomerTm customerTm = new CustomerTm(
                    customer.getCid(),
                    customer.getUid(),
                    customer.getCname(),
                    customer.getAddress(),
                    customer.getTelNo()

            );

            tmList.add(customerTm);
        }
        tblCustomercart.setItems(tmList);
        CustomerTm selectedItem = tblCustomercart.getSelectionModel().getSelectedItem();
        System.out.println("selectedItem = " + selectedItem);
    }

    private List<CustomerDTO> getAllCustomers() {
        List<CustomerDTO> customerList = null;
        try {
            customerList = customerBO.getAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return customerList;
    }


    private void setCellValueFactory() {
        colCID.setCellValueFactory(new PropertyValueFactory<>("CID"));
        colUID.setCellValueFactory(new PropertyValueFactory<>("UID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colTelNo.setCellValueFactory(new PropertyValueFactory<>("TelNo"));

    }

    private void getCustomerId() {
        ObservableList<String> observableList  = FXCollections.observableArrayList();
        try{
            List<String> IDList = customerBO.getCid();
            for(String C_id : IDList){
                observableList.add(C_id);
            }
            Cidcombox.setItems(observableList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    public void btnCidOnAction(ActionEvent actionEvent) {
        String cid = txtCid.getText();

        try {
            CustomerDTO customer = customerBO.searchByID(cid);

            if (customer!= null) {
                txtCid.setText(customer.getCid());
                txtUid.setText(customer.getUid());
                txtName.setText(customer.getCname());
                txtAddress.setText(customer.getAddress());
                txtTelNo.setText(customer.getTelNo());
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void cmbCutomerOnAction(ActionEvent actionEvent) {
        String id = (String) Cidcombox.getValue();
        try {
            CustomerDTO customer = customerBO.searchByID(id);
            if (customer != null) {
                txtCid.setText(customer.getCid());
                txtUid.setText(customer.getUid());
                txtName.setText(customer.getCname());
                txtAddress.setText(customer.getAddress());
                txtTelNo.setText(customer.getTelNo());
            } else {

                clearFields();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    @FXML
    void cmbUIDOnAction(ActionEvent event) {
        String id =  cmbUID.getValue();
        try {
            UserDTO user = customerBO.searchByuserID(id);
            if (user != null) {
                txtUid.setText(user.getUid());

            } else {

                clearFields();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/main_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.rootnodeC.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("dashBoard");
        stage.show();

    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        String cid =txtCid.getText();
        String uid = txtUid.getText();
        String name =txtName.getText();
        String address = txtAddress.getText();
        String telno = txtTelNo.getText();

        CustomerDTO customer = new CustomerDTO(cid,uid,name,address,telno);
        if (isValid()) {
            try {

                boolean isadded = customerBO.add(customer);
                if (isadded) {

                    new Alert(Alert.AlertType.CONFIRMATION, "Customer added!").show();
                    initialize();

                    productOrderFormController.getCustomerTelNo();

                    eventOrderFormController.getCustomerTelNo();
                }

            } catch (Exception e) {
                // new Alert(Alert.AlertType.ERROR, "something went wrong ").show();
            }
        }
        clearFields();



    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String cid =txtCid.getText();
        String uid = txtUid.getText();
        String name =txtName.getText();
        String address = txtAddress.getText();
        String telno = txtTelNo.getText();

        CustomerDTO customer = new CustomerDTO(cid,uid,name,address,telno);

        try{
            boolean isupdate=  customerBO.update(customer);
            if (isupdate){
                new Alert(Alert.AlertType.CONFIRMATION,"customer updated!").show();
                initialize();
            }

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"something went wrong ").show();
        }
        clearFields();


    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String cid = txtCid.getText();

        try {
            boolean isDeleted =customerBO.delete(cid);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer deleted!").show();
                initialize();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        }

    }
    private void clearFields() {
        txtCid.clear();
        txtUid.clear();
        txtName.clear();
        txtAddress.clear();
        txtTelNo.clear();
    }
    public boolean isValid() {
        if (!Regex.setTextColor(TextFeid.TelNo, txtTelNo)) {
            return false;
        }
        if (!Regex.setTextColor(TextFeid.Uid, txtUid)) {
            return false;
        }
        if(!Regex.setTextColor(TextFeid.Cname, txtName)){
            return false;
        }
        if (!Regex.setTextColor(TextFeid.Address, txtAddress)) {
            return false;
        }

        return true;
    }

    public void txtcustomerIDKeyReleased(KeyEvent keyEvent) {

    }

    public void txtUserIDKeyReleased(KeyEvent keyEvent) {
        if (!Regex.setTextColor(TextFeid.Uid, txtUid)){}
    }

    public void txtTelNoKeyReleased(KeyEvent keyEvent) {
        if (!Regex.setTextColor(TextFeid.TelNo, txtTelNo)) {
        }
    }

    public void txtNameKeyReleased(KeyEvent keyEvent) {
        if (!Regex.setTextColor(TextFeid.Cname, txtName)) {}
    }

    public void txtAddressKeyReleased(KeyEvent keyEvent) {
        if (!Regex.setTextColor(TextFeid.Address, txtAddress)) {}
    }
}
