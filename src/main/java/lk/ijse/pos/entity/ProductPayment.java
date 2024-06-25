package lk.ijse.pos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@Data
@NoArgsConstructor

public class ProductPayment {
    private String Paymentid;
    private String CID;
    private String POID;
    private Date date;
    private double paidPayment;
    private double Amount;
    private double Balance;
}
