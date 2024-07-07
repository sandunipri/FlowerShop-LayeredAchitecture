package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.pos.db.DbConnection;
import lk.ijse.pos.util.Regex;
import lk.ijse.pos.util.TextFeid;
/*import lk.ijse.FlowerShop.db.DbConnection;
import lk.ijse.FlowerShop.util.Regex;
import lk.ijse.FlowerShop.util.TextFeid;*/

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFormController {
    @FXML
    private AnchorPane rootNodeR;

    @FXML
    private Button login;

    @FXML
    private TextField passwrod;

    @FXML
    private AnchorPane rootnode;

    @FXML
    private TextField userid;

    @FXML
    void btnloginOnAction(ActionEvent event) throws IOException {
        String USERID = userid.getText();
        String PASSSWORD = passwrod.getText();
        if (isValid()) {
            try {
                cheak(USERID, PASSSWORD);
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "something went wrong!!").show();
            }

        }
    }

    private void cheak(String USERID, String PASSSWORD) throws SQLException, IOException {
        String sql = "SELECT U_id, U_passwrod FROM User WHERE U_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, USERID);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            String DBPW = resultSet.getString(2);
            if (DBPW.equals(PASSSWORD)) {
                navigateDashboard();
            } else {
                new Alert(Alert.AlertType.ERROR, "Passwrod is incorrect!!").show();
            }
            }else{
                new Alert(Alert.AlertType.INFORMATION, "user id is incorrect!!").show();
            }
        }


    private void navigateDashboard() throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/main_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.rootnode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("dashBoard");
        stage.show();
    }

    public void btnAccountAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane= FXMLLoader.load(this.getClass().getResource("/view/register_form.fxml"));
        this.rootNodeR.getChildren().clear();
        this.rootNodeR.getChildren().add(anchorPane);
    }

    public boolean isValid() {
        if (!Regex.setTextColor(TextFeid.Uid,userid)) {
            return false;
        }

        return true;
    }

    public void txtUseridkeyreleased(KeyEvent keyEvent) {
        if (!Regex.setTextColor(TextFeid.Uid, userid)) {

        }
    }

    public void txtUserPasswrodKeyReleased(KeyEvent keyEvent) {

    }
}



