package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.EmployeeBO;
import lk.ijse.pos.bo.custom.impl.EmployeeBOImpl;
import lk.ijse.pos.dto.EmployeeDTO;
import lk.ijse.pos.dto.UserDTO;
import lk.ijse.pos.util.Regex;
import lk.ijse.pos.util.TextFeid;
import lk.ijse.pos.view.tdm.EmployeeTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeFormController {
    @FXML
    private TableView<EmployeeTm> tblEmployeeCart;

    @FXML
    private JFXComboBox<String> cmbUID;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colJobRole;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colTelNo;

    @FXML
    private TableColumn<?, ?> coleid;

    @FXML
    private TableColumn<?, ?> coluid;


    @FXML
    private JFXComboBox<String> eidcmbbox;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEid;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtJobRole;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtTelNo;

    @FXML
    private TextField txtUid;

    @FXML
    private AnchorPane rootnodeE;
    //  private ObservableList<EmployeeTm> cartList = FXCollections.observableArrayList();

    private List<EmployeeDTO> employeeList = new ArrayList<>();

    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);

    public void initialize() {
        this.employeeList = getAllEmployees();
        getEmployeeId();
        setCellValueFactory();
        loadEmployeeTable();
        nextEMPID();
        getUserId();


    }

    private void getUserId() {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        try {
            List<String> IDList = employeeBO.getId();
            for (String UID : IDList) {
                observableList.add(UID);
            }
            cmbUID.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void nextEMPID() {
        try {
            String currentId = employeeBO.currentId();
            String nextId = nextId(currentId);

            txtEid.setText(nextId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String nextId(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("E");
            int id = Integer.parseInt(split[1],10);
            return "E" + String.format("%04d", ++id);
        }
        return "E0001";
    }

    private List<EmployeeDTO> getAllEmployees() {
        List<EmployeeDTO> employeeList = null;
        try {
            employeeList = employeeBO.getEmployeeAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return employeeList;
    }

    private void loadEmployeeTable() {
        ObservableList<EmployeeTm> tmList = FXCollections.observableArrayList();

        for (EmployeeDTO employee : employeeList) {
            EmployeeTm employeeTm = new EmployeeTm(
                    employee.getEid(),
                    employee.getUid(),
                    employee.getEname(),
                    employee.getAddress(),
                    employee.getEmail(),
                    employee.getJobRole(),
                    employee.getTelNo()

            );

            tmList.add(employeeTm);
        }
        tblEmployeeCart.setItems(tmList);
        EmployeeTm selectedItem = tblEmployeeCart.getSelectionModel().getSelectedItem();
        System.out.println("selectedItem = " + selectedItem);
    }

    private void setCellValueFactory() {
        coleid.setCellValueFactory(new PropertyValueFactory<>("EID"));
        coluid.setCellValueFactory(new PropertyValueFactory<>("UID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        colJobRole.setCellValueFactory(new PropertyValueFactory<>("JobRole"));
        colTelNo.setCellValueFactory(new PropertyValueFactory<>("TelNo"));

    }

    private void getEmployeeId() {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        try {
            List<String> IDList = employeeBO.getEid();
            for (String E_id : IDList) {
                observableList.add(E_id);
            }
            eidcmbbox.setItems(observableList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnAddOnAction(ActionEvent event) {

        String eid = txtEid.getText();
        String uid = txtUid.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String jobrole = txtJobRole.getText();
        String telno = txtTelNo.getText();


        EmployeeDTO employee = new EmployeeDTO(eid, uid, name, address, email, jobrole, telno);
        if (isValid()) {
            try {

                boolean isadded = employeeBO.add(employee);
                if (isadded) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Employee added!").show();
                    initialize();
                }

            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "something went wrong ").show();
            }
            clearFields();


        }
    }

    @FXML
    void btnCheackOnAction(ActionEvent event) {
        String eid = txtEid.getText();

        try {
            EmployeeDTO employee = employeeBO.searchByID(eid);

            if (employee != null) {
                txtEid.setText(employee.getEid());
                txtUid.setText(employee.getUid());
                txtName.setText(employee.getEname());
                txtAddress.setText(employee.getAddress());
                txtEmail.setText(employee.getEmail());
                txtJobRole.setText(employee.getJobRole());
                txtTelNo.setText(employee.getTelNo());
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String eid = txtEid.getText();

        try {
            boolean isDeleted = employeeBO.delete(eid);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee deleted!").show();
                initialize();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        }


    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String eid = txtEid.getText();
        String uid = txtUid.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String jobrole = txtJobRole.getText();
        String telno = txtTelNo.getText();


        EmployeeDTO employee = new EmployeeDTO(eid, uid, name, address, email, jobrole, telno);
        if (isValid()) {
            try {
                boolean isupdate = employeeBO.update(employee);
                if (isupdate) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Employee updated!").show();
                    initialize();
                }

            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "something went wrong ").show();
            }
        }

        clearFields();


    }

    @FXML
    void cmbEmployeeOnAction(ActionEvent event) {
        String id = (String) eidcmbbox.getValue();
        try {
            EmployeeDTO employee = employeeBO.searchByID(id);
            if (employee != null) {
                txtEid.setText(employee.getEid());
                txtUid.setText(employee.getUid());
                txtName.setText(employee.getEname());
                txtAddress.setText(employee.getAddress());
                txtEmail.setText(employee.getEmail());
                txtJobRole.setText(employee.getJobRole());
                txtTelNo.setText(employee.getTelNo());
            } else {

                clearFields();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void clearFields() {
        txtEid.clear();
        txtUid.clear();
        txtName.clear();
        txtAddress.clear();
        txtEmail.clear();
        txtJobRole.clear();
        txtTelNo.clear();
    }


    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/main_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.rootnodeE.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("dashBoard");
        stage.show();
    }


    public boolean isValid() {
        if (!Regex.setTextColor(TextFeid.Email, txtEmail)) {
            return false;
        }

        if (!Regex.setTextColor(TextFeid.TelNo, txtTelNo)) {
            return false;
        }
        if (!Regex.setTextColor(TextFeid.UID, txtUid)) {
            return false;
        }
        if (!Regex.setTextColor(TextFeid.Ename, txtName)) {
            return false;
        }
        if (!Regex.setTextColor(TextFeid.Address, txtAddress)) {
            return false;
        }
        if (!Regex.setTextColor(TextFeid.jobRole, txtJobRole)) {
            return false;
        }
        return true;
    }

    public void txtEmployeeEmailKeyReleased(KeyEvent keyEvent) {

        if (!Regex.setTextColor(TextFeid.Email, txtEmail)) {
        }
    }

    public void txtEmployeeEidKeyReleased(KeyEvent keyEvent) {

    }

    public void txtEmployeeTelNoKeyReleased(KeyEvent keyEvent) {
        if (!Regex.setTextColor(TextFeid.TelNo, txtTelNo)) {
        }
    }

    public void txtEmployeeUseIDKeyReleased(KeyEvent keyEvent) {

        if (!Regex.setTextColor(TextFeid.UID, txtUid)) {
        }
    }

    public void txtNameKeyReleased(KeyEvent keyEvent) {
        if (!Regex.setTextColor(TextFeid.Ename, txtName)) {}
    }

    public void txtAddressKeyRelesed(KeyEvent keyEvent) {
        if (!Regex.setTextColor(TextFeid.Address, txtAddress)) {

        }
    }

    public void txtJobRoleKeyReleased(KeyEvent keyEvent) {
        if (!Regex.setTextColor(TextFeid.jobRole, txtJobRole)) {}
    }

    public void cmbUIDonAction(ActionEvent actionEvent) {
        String id =  cmbUID.getValue();
        try {
            UserDTO user = employeeBO.searchByUserID(id);
            if (user != null) {
                txtUid.setText(user.getUid());

            } else {

                clearFields();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
