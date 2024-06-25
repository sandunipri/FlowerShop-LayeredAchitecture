package lk.ijse.pos.view.tdm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class productTm {
    private String code;
    private String Name;
    private int qty;
    private double Price;
    private double total;
    private JFXButton btnRemove;

    private String POID;
    private String StockID;
    private int issuedFlowers;

    public productTm(String code, String name, int qty, double price, double total, JFXButton btnRemove) {
        this.code = code;
        Name = name;
        this.qty = qty;
        Price = price;
        this.total = total;
        this.btnRemove = btnRemove;
    }

    //        productTm cartTm = new productTm(code,Name, (int) qty, Price, total, btnRemove,issuedFlower,stockId);


    public productTm(String code, String name, int qty, double price, double total, JFXButton btnRemove, String stockID, int issuedFlowers,String POID) {
        this.code = code;
        Name = name;
        this.qty = qty;
        Price = price;
        this.total = total;
        this.btnRemove = btnRemove;
        this.POID = POID;
        StockID = stockID;
        this.issuedFlowers = issuedFlowers;
    }
}
