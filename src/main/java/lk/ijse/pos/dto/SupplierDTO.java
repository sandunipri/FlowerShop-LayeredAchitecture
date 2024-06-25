package lk.ijse.pos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SupplierDTO {
    private String SID;
    private String StockID;
    private String UID;
    private String SName;
    private String Address;
    private String TelNo;
    private String Fcode;
    private int QTY;
    private double Price;
}

