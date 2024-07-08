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
import lk.ijse.pos.bo.custom.EventPlaceOrderBO;
import lk.ijse.pos.bo.custom.StockBO;
import lk.ijse.pos.bo.custom.impl.EventPlaceOrderBOImpl;
import lk.ijse.pos.bo.custom.impl.StockBOImpl;
import lk.ijse.pos.db.DbConnection;
import lk.ijse.pos.dto.*;
import lk.ijse.pos.util.Regex;
import lk.ijse.pos.util.TextFeid;
import lk.ijse.pos.view.tdm.EventTm;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class EventOrderFormController {
    @FXML
    private AnchorPane rootnodeEventPayment;
    @FXML
    private AnchorPane rootNodeEvent;
    @FXML
    private Button add;

    @FXML
    private Button btnPalceOrder;

    @FXML
    private TableColumn<?, ?> cloEName;

    @FXML
    private JFXComboBox<String> cmbEvent;

    @FXML
    private JFXComboBox<String> cmbFlower;

    @FXML
    private JFXComboBox<String> cmbStock;

    @FXML
    private JFXComboBox<String> cmbTelNo;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colECode;

    @FXML
    private TableColumn<?, ?> colFName;

    @FXML
    private TableColumn<?, ?> colIssuedFlower;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private Label lblEventOrder;

    @FXML
    private Label lblTotal;

    @FXML
    private TableView<EventTm> tblEventAdd;

    @FXML
    private TextField txtCusId;

    @FXML
    private TextField txtCusName;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtECode;

    @FXML
    private TextField txtEPrice;

    @FXML
    private TextField txtFCode;

    @FXML
    private TextField txtIssuedFlower;

    private ObservableList<EventTm> cartList = FXCollections.observableArrayList();

    private double netTotal = 0;

    private CustomerFormController customerFormController;

    private  AddEventFormController addEventFormController;

    String EOID;

    EventPlaceOrderBO eventPlaceOrderBO = new EventPlaceOrderBOImpl();
    public void initialize() {
        setDate();
        NextOrderNo();
        getCustomerTelNo();
        gerEventName();
        getFlowerName();
        getStockId();
        setCellValueFactory();

    }

    private void setCellValueFactory() {
        colECode.setCellValueFactory(new PropertyValueFactory<>("Ecode"));
        cloEName.setCellValueFactory(new PropertyValueFactory<>("EName"));
        colFName.setCellValueFactory(new PropertyValueFactory<>("FName"));
        colIssuedFlower.setCellValueFactory(new PropertyValueFactory<>("IssuedFlower"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));
    }

    private void getStockId() {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        try {
            List<String> StockId = eventPlaceOrderBO.getCodes();
            for (String STOCKID : StockId) {
                observableList.add(STOCKID);
            }
            cmbStock.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void getFlowerName() {
        ObservableList<String> observableList  = FXCollections.observableArrayList();
        try{
            List<String> nameList = eventPlaceOrderBO.getFlowerName();
            for(String name : nameList){
                observableList.add(name);
            }
            cmbFlower.setItems(observableList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    void gerEventName() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> NameList = eventPlaceOrderBO.getNames();
            for (String Name : NameList) {
                obList.add(Name);
            }

            cmbEvent.setItems(obList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    void getCustomerTelNo() {
        ObservableList<String> observableList  = FXCollections.observableArrayList();
        try{
            List<String> TelNoList = eventPlaceOrderBO.getTelNo();
            for(String C_id : TelNoList){
                observableList.add(C_id);
            }
            cmbTelNo.setItems(observableList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void NextOrderNo() {
        try {
            String currentId = eventPlaceOrderBO.currentId();
            String nextId = nextId(currentId);

            lblEventOrder.setText(nextId);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String nextId(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("e");
            int id = Integer.parseInt(split[1],10);
            return "e" + String.format("%04d", ++id);
        }
        return "e0001";
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        txtDate.setText(String.valueOf(now));
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String ECode = txtECode.getText();
        String EName = cmbEvent.getValue();
        String FName = cmbFlower.getValue();
        int IssuedFlower = Integer.parseInt(txtIssuedFlower.getText());
        double Price = Double.parseDouble(txtEPrice.getText());

        JFXButton btnRemove = new JFXButton("remove");
        btnRemove.setCursor(Cursor.HAND);

        btnRemove.setOnAction((e) -> {
            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if(type.orElse(no) == yes) {
                int selectedIndex = tblEventAdd.getSelectionModel().getSelectedIndex();
                cartList.remove(selectedIndex);

                tblEventAdd.refresh();
                calculateNetTotal();
            }
        });

        for (int i = 0; i < tblEventAdd.getItems().size(); i++) {
            if (ECode.equals(colECode.getCellData(i))) {

                cartList.get(i).setIssuedFlower(cartList.get(i).getIssuedFlower() + IssuedFlower);


                tblEventAdd.refresh();
                calculateNetTotal();
                txtIssuedFlower.setText("");
                return;
            }
        }

        EventTm cartTm = new EventTm(ECode, EName, FName, IssuedFlower, Price, btnRemove);

        cartList.add(cartTm);

        tblEventAdd.setItems(cartList);
        calculateNetTotal();
    }

    private void calculateNetTotal() {
        netTotal= 0;
        for (int i = 0; i < tblEventAdd.getItems().size(); i++) {
            netTotal += (double) colPrice.getCellData(i);
        }
        lblTotal.setText(String.valueOf(netTotal));
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        EOID = lblEventOrder.getText();
        String CID  = txtCusId.getText();
        String ECode = txtECode.getText();
        double Amount = Double.parseDouble(lblTotal.getText());

        EventOrderDTO eventOrder = new EventOrderDTO(EOID, CID, ECode,Amount);


        List<EventOrderDetailDTO> eventOrderDetailList = new ArrayList<>();
        for (int i = 0; i < tblEventAdd.getItems().size(); i++) {
            EventTm eventTm = cartList.get(i);

            String EOiD = lblEventOrder.getText();
            String StockID = cmbStock.getValue();
            int issuedFlowers = Integer.parseInt(txtIssuedFlower.getText());


            EventOrderDetailDTO eventOrderDetail = new EventOrderDetailDTO(
                    EOiD,
                    StockID,
                    issuedFlowers,
                    eventTm.getPrice()
            );
            eventOrderDetailList.add(eventOrderDetail);
        }


        EventPlaceDTO eventPlace = new EventPlaceDTO(eventOrder, eventOrderDetailList);
        if (isValid()) {
            try {
                boolean isPlaceOrder = eventPlaceOrderBO.updateEvent(eventPlace);
                if (isPlaceOrder) {
                    new Alert(Alert.AlertType.CONFIRMATION, " updated!").show();


                } else {
                    new Alert(Alert.AlertType.WARNING, " not updated").show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    @FXML
    void cmbEventOnAction(ActionEvent event) {
        String name = cmbEvent.getValue();
        try {
            EventDTO event1 = eventPlaceOrderBO.searchByName(name);
            if (event1 != null) {
                txtECode.setText(event1.getEID());
                txtEPrice.setText(String.valueOf(event1.getPrice()));

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void cmbFlowerOnAction(ActionEvent event) {
        String name = cmbFlower.getValue();
        try {
            FlowerDTO flower = eventPlaceOrderBO.searchByFlowerName(name);
            if (flower != null) {
                txtFCode.setText(flower.getFCode());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void cmbStockOnAction(ActionEvent event) {

    }

    @FXML
    public void cmbTelNoOnAction(ActionEvent event) {
        String TelNo = cmbTelNo.getValue();

        try {
                CustomerDTO customer = eventPlaceOrderBO.searchByTelNo(TelNo);

            txtCusName.setText(customer.getCname());
            txtCusId.setText(customer.getCid());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void btnBillOnAction(ActionEvent actionEvent) throws JRException, SQLException {

        JasperDesign report = JRXmlLoader.load("src/main/resources/reports/order_report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(report);

        Map<String , Object> data = new HashMap<>();
        // System.out.println(data);
        data.put("orderId", EOID);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, data, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint, false);
    }

    public void BTNNEWOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/customer_form.fxml"));

        AnchorPane rootNodeEvent = loader.load();

        Stage popupStage = new Stage();

        customerFormController = loader.getController();

        customerFormController.setEventOrderFormController(this);

        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Popup Window");
        popupStage.setScene(new Scene(rootNodeEvent));
        popupStage.showAndWait();
    }

    public void btnnewOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/view/AddEvent_form.fxml"));
        AnchorPane rootNodeEvent = loader.load();

        Stage popupStage = new Stage();

        addEventFormController = loader.getController();
        addEventFormController.setEventOrderFormController(this);

        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Popup Window");
        popupStage.setScene(new Scene(rootNodeEvent));
        popupStage.showAndWait();
    }

    public void btnPaymentOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNodeEvent = FXMLLoader.load(getClass().getResource("/view/EventPayment_form.fxml"));

        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Popup Window");
        popupStage.setScene(new Scene(rootNodeEvent));
        popupStage.showAndWait();
    }
    public boolean isValid(){
        if (!Regex.setTextColor(TextFeid.issuedFlowers, txtIssuedFlower)) {
            return false;
        }
        return true;
    }

    public void txtEventOrderKeyReleasedOnAction(KeyEvent keyEvent) {
        if (!Regex.setTextColor(TextFeid.issuedFlowers, txtIssuedFlower)) {
        }
    }
}
