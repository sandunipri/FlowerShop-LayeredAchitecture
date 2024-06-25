package lk.ijse.pos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class EventPlaceDTO {
    private EventOrderDTO eventOrder;
    private List<EventOrderDetailDTO> eventOrderDetailList;
}
