package lk.ijse.pos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class EventOrderDTO {
    private String EOID;
    private String CID;
    private String Ecode;
    private double Amount;
}
