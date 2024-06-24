package lk.ijse.FlowerShop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor

public class StockDTO {
    private String StockId;
    private String FCode;
    private int QtyOnHand;
}
