package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.EventBO;
import lk.ijse.pos.bo.custom.impl.EventBOImpl;
import lk.ijse.pos.dto.EventDTO;
import lk.ijse.pos.util.Regex;
import lk.ijse.pos.util.TextFeid;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class AddEventFormController {
    @FXML
    private Button btnAdd;

    @FXML
    private Button txtDelete;

    @FXML
    private TextField txtECode;

    @FXML
    private TextField txtEDate;

    @FXML
    private TextField txtEName;

    @FXML
    private TextField txtEPrice;
    private EventOrderFormController eventOrderFormController;

    private EventBO eventBO = (EventBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EVENT);

    public void setEventOrderFormController(EventOrderFormController eventOrderFormController) {
        this.eventOrderFormController = eventOrderFormController;

    }

    public void initialize() {
        nextEventId();
        setDate();
    }
    private void setDate() {
        LocalDate now = LocalDate.now();
        txtEDate.setText(String.valueOf(now));
    }

    private void nextEventId() {
        try {
            String currentId = eventBO.currentId();
            String nextId = nextId(currentId);

            txtECode.setText(nextId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String nextId(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("Z");
            int id = Integer.parseInt(split[1],10);
            return "Z" + String.format("%04d", ++id);
        }
        return "Z0001";
    }


    @FXML
    void btnAddOnAction(ActionEvent event) {
        String ECODE = txtECode.getText();
        String ENAME = txtEName.getText();
        Date date = Date.valueOf(LocalDate.now());
        double price = Double.parseDouble(txtEPrice.getText());


        EventDTO event1 = new EventDTO(ECODE, ENAME, date, price);
        if (isValid()){
            try {

                boolean isadded = eventBO.add(event1);
                if (isadded) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Event added!").show();
                    initialize();
                    eventOrderFormController.gerEventName();
                }

            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "something went wrong ").show();

                //clearFields();


            }

        }


    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException {
        String ECode = txtECode.getText();

        try {
            boolean isDeleted = eventBO.delete(ECode);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Flower deleted!").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        }

    }
    public boolean isValid(){
        if (!Regex.setTextColor(TextFeid.Ename, txtEName)) {
            return false;
        }
        if (!Regex.setTextColor(TextFeid.price, txtEPrice)) {
            return false;
        }
        return true;
    }


    public void txtENameKeyReleasedOnAction(KeyEvent keyEvent) {
        if (!Regex.setTextColor(TextFeid.Ename, txtEName)) {
        }

    }

    public void txtEPriceKeyReleasedOnAction(KeyEvent keyEvent) {
        if (!Regex.setTextColor(TextFeid.price, txtEPrice)) {
        }
    }

}
