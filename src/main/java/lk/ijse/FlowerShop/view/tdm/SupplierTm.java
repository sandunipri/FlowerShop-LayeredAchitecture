package lk.ijse.FlowerShop.view.tdm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class SupplierTm {
    private String Name;
    private String TelNo;
    private String Address;
    private String  StockID;
    private String FCode;;
    private int QTY ;
    private double price;
    private JFXButton btnRemove;

}
