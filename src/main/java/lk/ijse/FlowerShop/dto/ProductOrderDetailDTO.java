package lk.ijse.FlowerShop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor

public class ProductOrderDetailDTO {
  private String POID;
  private String StockID;
  private  String PCode;
  private int issuedFlowers;
  private double price;
  private int qty;
  private double Amount;
}
