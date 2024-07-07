package lk.ijse.pos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductPlaceDTO {
    private ProductOrderDTO productOrder;

    private List<ProductOrderDetailDTO>productOrderDetailList;

}
