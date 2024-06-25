package lk.ijse.pos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class EventPayment {
    private String Paymentid;
    private String CID;
    private String EOID;
    private Date date;
    private double paidPayment;
    private double Amount;
    private double Balance;
}
