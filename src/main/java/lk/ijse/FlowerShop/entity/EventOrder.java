package lk.ijse.FlowerShop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class EventOrder {
    private String EOID;
    private String CID;
    private String Ecode;
    private double Amount;
}
