package lk.ijse.FlowerShop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class EventDTO {
    private String EID;
    private String EName;
    private Date EDate;
    private double Price;
}
