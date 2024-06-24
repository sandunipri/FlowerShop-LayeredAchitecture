package lk.ijse.FlowerShop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class EmployeeDTO {
    private String Eid;
    private String Uid;
    private String Ename;
    private String Address;
    private String Email;
    private String jobRole;
    private String TelNo;

}

