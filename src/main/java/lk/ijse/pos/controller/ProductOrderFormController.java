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
import lk.ijse.pos.bo.custom.ProductPlaceOrderBO;
import lk.ijse.pos.bo.custom.impl.ProductPlaceOrderBOImpl;
import lk.ijse.pos.dto.*;
import lk.ijse.pos.util.Regex;
import lk.ijse.pos.util.TextFeid;
import lk.ijse.pos.view.tdm.ProductTm;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Integer.parseInt;

public class ProductOrderFormController {
    @FXML
    private AnchorPane rootNodeProduct;
    public TextField txtProductCode;
    @FXML
    private AnchorPane rootNodeEP;
    public Label lblOrder;

    @FXML
    private Button add;
    @FXML
    private Button back;

    @FXML
    private JFXComboBox<String> cmbProduct;

    @FXML
    private JFXComboBox<String> cmbcustomer;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colProductCode;

    @FXML
    private TableColumn<?, ?> colProductName;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colprice;

    @FXML
    private TableColumn<?, ?> colqty;

    @FXML
    private TableView<ProductTm> tblproductAdd;

    @FXML
    private TextField txtQTY;

    @FXML
    private TextField txtqty;

    @FXML
    private Button placeOrder;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtPrice;


    @FXML
    private TextField txtFlowercode;

    @FXML
    private TextField txtQtyOnHand;

    @FXML
    private TextField txtIsuuedFlower;

    @FXML
    private Label lblnettotal;

    @FXML
    private TextField txtProductName;

    @FXML
    private TextField txtproductQty;

    @FXML
    private JFXComboBox<String> cmbStock;

    @FXML
    private JFXComboBox<String> cmbFlower;

    private ObservableList<ProductTm> cartList = FXCollections.observableArrayList();

    private double netTotal = 0;

    private CustomerFormController customerFormController;

    private  AddproductFormController addProductFormController;

    ProductPlaceOrderBO productPlaceOrderBO = new ProductPlaceOrderBOImpl();

    public void initialize(){
        setDate();
        NextOrderNo();
        getCustomerTelNo();
        getProductName();
        setCellValueFactory();
        getFlowerName();
        getStockId();

    }

    private void getStockId() {

        ObservableList<String> observableList = FXCollections.observableArrayList();
        try {
            List<String> StockId = productPlaceOrderBO.getStockCodes();
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
            List<String> nameList = productPlaceOrderBO.getFlowerName();
            for(String name : nameList){
                observableList.add(name);
            }
            cmbFlower.setItems(observableList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void setCellValueFactory() {
        colProductCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colqty.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        colprice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("Total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));
    }


    void getProductName() {
        ObservableList<String> observableList  = FXCollections.observableArrayList();
        try{
            List<String> name = productPlaceOrderBO.getProductName();
            for(String NAME : name){
                observableList.add(NAME);
            }
            cmbProduct.setItems(observableList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    void getCustomerTelNo() {
        ObservableList<String> observableList  = FXCollections.observableArrayList();
        try{
            List<String> TelNoList = productPlaceOrderBO.getTelNo();
            for(String C_id : TelNoList){
                observableList.add(C_id);
            }
            cmbcustomer.setItems(observableList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void NextOrderNo() {
        try {
            String currentId = productPlaceOrderBO.currentId();
            String nextId = nextId(currentId);

            lblOrder.setText(nextId);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String nextId(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("p");
            int id = Integer.parseInt(split[1],10);
            return "p" + String.format("%04d", ++id);
        }
        return "p0001";
    }


    private void setDate() {
        LocalDate now = LocalDate.now();
        txtDate.setText(String.valueOf(now));
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String code = txtFlowercode.getText();
        String Name = cmbProduct.getValue();
        int qty = parseInt(txtQTY.getText());
        double Price = Double.parseDouble(txtPrice.getText());
        double total = qty * Price;
        String stockId = cmbStock.getValue();
        int issuedFlower = Integer.parseInt(txtIsuuedFlower.getText());
        String  productCode = txtProductCode.getText();


        JFXButton btnRemove = new JFXButton("remove");
        btnRemove.setCursor(Cursor.HAND);

        btnRemove.setOnAction((e) -> {
            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if(type.orElse(no) == yes) {
                int selectedIndex = tblproductAdd.getSelectionModel().getSelectedIndex();
                cartList.remove(selectedIndex);

                tblproductAdd.refresh();
                calculateNetTotal();
            }
        });


        for (int i = 0; i < tblproductAdd.getItems().size(); i++) {
            if (code.equals(colProductCode.getCellData(i))) {
                qty += cartList.get(i).getQty();
                total = Price * qty;

                cartList.get(i).setQty((int) qty);
                cartList.get(i).setTotal(total);

                tblproductAdd.refresh();
                calculateNetTotal();
                // txtQTY.setText("");
                return;
            }
        }

        ProductTm cartTm = new ProductTm(code,Name, (int) qty, Price, total, btnRemove,stockId,issuedFlower,productCode);

        cartList.add(cartTm);

        tblproductAdd.setItems(cartList);
        //txtQTY.setText("");
        calculateNetTotal();
    }

    private void calculateNetTotal() {
        netTotal= 0;
        for (int i = 0; i < tblproductAdd.getItems().size(); i++) {
            netTotal += (double) colTotal.getCellData(i);
        }
        lblnettotal.setText(String.valueOf(netTotal));
    }


    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
      /*  AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/productEvent_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.rootNodeEP.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("order");
        stage.show();*/

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        String poId = lblOrder.getText();
        String customerId = txtId.getText();

        ProductOrderDTO productOrder = new ProductOrderDTO(poId, customerId);

        List<ProductOrderDetailDTO> productOrderDetailList = new ArrayList<>();
        for (int i = 0; i < tblproductAdd.getItems().size(); i++) {
            ProductTm productTm = cartList.get(i); // Ensure this corresponds correctly
            String stockId = cmbStock.getValue();
            String productCode = txtProductCode.getText();

            int issuedFlowers = Integer.parseInt(txtIsuuedFlower.getText());


            System.out.println("Creating ProductOrderDetail for item " + i);
            System.out.println("StockID: " + stockId);
            System.out.println("ProductCode: " + productCode);
            System.out.println("IssuedFlowers: " + issuedFlowers);
            System.out.println("Price: " + productTm.getPrice());
            System.out.println("Quantity: " + productTm.getQty());
            System.out.println("Total: " + productTm.getTotal());

            ProductOrderDetailDTO productOrderDetail = new ProductOrderDetailDTO(
                    poId,
                    productTm.getStockID(),
                    productTm.getPOID(),
                    productTm.getIssuedFlowers(),
                    productTm.getPrice(),
                    productTm.getQty(),
                    productTm.getTotal()
            );

            productOrderDetailList.add(productOrderDetail);
        }
        System.out.println(productOrderDetailList);
        ProductPlaceDTO productPlace = new ProductPlaceDTO(productOrder, productOrderDetailList);

        if (isValid()) {
            try {
                boolean isOrderPlaced = productPlaceOrderBO.update(productPlace);
                if (isOrderPlaced) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Order updated successfully!").show();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Order update failed!").show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "An error occurred: " + e.getMessage()).show();
            }
        }
    }
    @FXML
    void cmbCustomerOnAction(ActionEvent event) {
        String TelNo = cmbcustomer.getValue();

        try {
            CustomerDTO customer = productPlaceOrderBO.searchByTelNo(TelNo);

            txtName.setText(customer.getCname());
            txtId.setText(customer.getCid());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    @FXML
    void cmbProductOnAction(ActionEvent event) {
        String name = cmbProduct.getValue();
        try {
            ProductDTO product = productPlaceOrderBO.searchByName(name);
            if (product != null) {

                txtProductCode.setText(product.getPCode());
                txtPrice.setText(String.valueOf(product.getPrice()));

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //txtQTY.requestFocus();

    }

    @FXML
    void cmbFlowerOnAction(ActionEvent event) {
        String name = cmbFlower.getValue();
        try {
            FlowerDTO flower = productPlaceOrderBO.searchByFlowerName(name);
            if (flower != null) {
                txtFlowercode.setText(flower.getFCode());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        txtQTY.requestFocus();
    }

    @FXML
    void txtQtyOnAction(ActionEvent event) {

    }

    public void cmbStockOnAction(ActionEvent actionEvent) {

    }

    public boolean isValid() {
        if (!Regex.setTextColor(TextFeid.qty, txtQTY)) {
            return false;
        }
        if (!Regex.setTextColor(TextFeid.issuedFlowers, txtIsuuedFlower)) {
            return false;
        }
        return true;
    }

    public void txtQtyKeyReleased(KeyEvent keyEvent) {
        if (!Regex.setTextColor(TextFeid.qty, txtQTY)) {}
    }

    public void txtIssuedFlowerKeyReleased(KeyEvent keyEvent) {
        if (!Regex.setTextColor(TextFeid.issuedFlowers, txtIsuuedFlower)) {}
    }

    public void btnPaymentOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNodeProduct = FXMLLoader.load(getClass().getResource("/view/productPayment.fxml"));

        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Popup Window");
        popupStage.setScene(new Scene(rootNodeProduct));
        popupStage.showAndWait();
    }

    public void btnNEWOnAnction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/customer_form.fxml"));

        AnchorPane rootNodeProduct = loader.load();

        Stage popupStage = new Stage();

        customerFormController = loader.getController();
        customerFormController.setProductOrderFormController(this);

        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Popup Window");
        popupStage.setScene(new Scene(rootNodeProduct));
        popupStage.showAndWait();

    }

    public void btnnewOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddProduct_form.fxml"));

        AnchorPane rootNodeProduct = loader.load();

        Stage popupStage = new Stage();

        addProductFormController = loader.getController();
        addProductFormController.setProductOrderFormController(this);


        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Popup Window");
        popupStage.setScene(new Scene(rootNodeProduct));
        popupStage.showAndWait();
    }
}
