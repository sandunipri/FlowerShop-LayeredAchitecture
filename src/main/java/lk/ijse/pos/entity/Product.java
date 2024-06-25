package lk.ijse.pos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data@NoArgsConstructor

public class Product {
    private String PCode;
    private String PName;
    private double price;


}
