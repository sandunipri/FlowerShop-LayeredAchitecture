package lk.ijse.FlowerShop.view.tdm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class EventTm {
    private String Ecode;
    private String EName;
    private String FName;
    private int IssuedFlower;
    private double Price;
    private JFXButton btnRemove;
}
