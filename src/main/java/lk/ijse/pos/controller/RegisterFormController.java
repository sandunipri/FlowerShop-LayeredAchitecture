package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.pos.bo.custom.UserBO;
import lk.ijse.pos.bo.custom.impl.UserBOImpl;
import lk.ijse.pos.dto.UserDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class RegisterFormController {
    @FXML
    private Button Register;

    @FXML
    private JFXComboBox<String> cmbUserId;

    @FXML
    private TextField txtPasswrod;

    @FXML
    private TextField txtUserID;

    @FXML
    private TextField txtUserName;

    @FXML
    private AnchorPane rootNodeRR;

    UserBO userBO = new UserBOImpl();

    public void initialize() {

        loadUserIds();
    }

    private void loadUserIds() {
        ObservableList<String> observableList  = FXCollections.observableArrayList();
        try{
            List<String> IDList = userBO.getId();
            for(String u_id : IDList){
                observableList.add(u_id);
            }
            cmbUserId.setItems(observableList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmbUserIdOnAction(ActionEvent event) {
        String id = (String) cmbUserId.getValue();
        try {
            UserDTO user = userBO.searchByID(id);
            if (user != null) {

                txtUserID.setText(user.getUid());

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void btnRegisterOnAction(ActionEvent actionEvent) {
        String uid = txtUserID.getText();
        String Name = txtUserName.getText();
        String passwrod = txtPasswrod.getText();

        UserDTO user = new UserDTO(uid,Name,passwrod);

        try{
            boolean isupdate=  userBO.update(user);
            if (isupdate){
                new Alert(Alert.AlertType.CONFIRMATION,"User updated!").show();
            }

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"something went wrong ").show();
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/main_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.rootNodeRR.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("dashBoard");
        stage.show();
    }
}
