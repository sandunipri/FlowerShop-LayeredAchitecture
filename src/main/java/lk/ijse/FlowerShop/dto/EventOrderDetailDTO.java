package lk.ijse.FlowerShop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class EventOrderDetailDTO {

    private String EOID;
    private String StockID;
    private int issuedFlowers;
    private double price;
}
