package lk.ijse.FlowerShop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class EventOrderDetail {

    private String EOID;
    private String StockID;
    private int issuedFlowers;
    private double price;
}
