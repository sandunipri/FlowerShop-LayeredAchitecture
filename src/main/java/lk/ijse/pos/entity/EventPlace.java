package lk.ijse.pos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class EventPlace {
    private EventOrder eventOrder;
    private List<EventOrderDetail> eventOrderDetailList;
}
