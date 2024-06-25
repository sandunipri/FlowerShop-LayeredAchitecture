package lk.ijse.pos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class productPlace {
    private ProductOrder productOrder;

    private List<ProductOrderDetail>productOrderDetailList;

}
