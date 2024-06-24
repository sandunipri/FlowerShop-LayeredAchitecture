package lk.ijse.FlowerShop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class EventPaymentDTO {
    private String Paymentid;
    private String CID;
    private String EOID;
    private Date date;
    private double paidPayment;
    private double Amount;
    private double Balance;
}
