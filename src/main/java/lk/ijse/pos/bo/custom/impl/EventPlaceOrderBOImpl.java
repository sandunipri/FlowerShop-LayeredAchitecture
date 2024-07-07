package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.EventPlaceOrderBO;
import lk.ijse.pos.dao.custom.EventOrderDAO;
import lk.ijse.pos.dao.custom.EventOrderDetailDAO;
import lk.ijse.pos.dao.custom.EventPaymentDAO;
import lk.ijse.pos.dao.custom.StockDAO;
import lk.ijse.pos.dao.custom.impl.EventOrderDAOImpl;
import lk.ijse.pos.dao.custom.impl.EventOrderDetailDAOImpl;
import lk.ijse.pos.dao.custom.impl.EventPaymentDAOImpl;
import lk.ijse.pos.dao.custom.impl.StockDAOImpl;
import lk.ijse.pos.db.DbConnection;
import lk.ijse.pos.dto.EventOrderDTO;
import lk.ijse.pos.dto.EventOrderDetailDTO;
import lk.ijse.pos.dto.EventPlaceDTO;
import lk.ijse.pos.entity.EventOrder;
import lk.ijse.pos.entity.EventOrderDetail;
import lk.ijse.pos.entity.EventPlace;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventPlaceOrderBOImpl implements EventPlaceOrderBO {
    EventOrderDAO eventOrderDAO = new EventOrderDAOImpl();
    EventOrderDetailDAO eventOrderDetailDAO = new EventOrderDetailDAOImpl();

    StockDAO stockDAO = new StockDAOImpl();
    @Override
    public String currentId() throws SQLException, ClassNotFoundException {
        return eventOrderDAO.currentId();
    }

    @Override
    public boolean saveOrder(EventOrderDTO dto) throws SQLException, ClassNotFoundException {
        EventOrder eventOrder = new EventOrder(
                dto.getEOID(),
                dto.getCID(),
                dto.getEcode(),
                dto.getAmount()
        );
        return eventOrderDAO.save(eventOrder);
    }

    @Override
    public List<String> getid() {
        return eventOrderDAO.getid();
    }

    @Override
    public EventOrderDTO searchByPOID(String id) throws SQLException, ClassNotFoundException {
        EventOrder eventOrder = new EventOrder();
        EventOrderDTO eventOrderDTO = new EventOrderDTO(

                eventOrder.getEOID(),
                eventOrder.getCID(),
                eventOrder.getEcode(),
                eventOrder.getAmount()

        );
        return eventOrderDTO;
    }
    @Override
    public boolean saveOD(EventOrderDetailDTO dto) throws SQLException, ClassNotFoundException {
        EventOrderDetail eventOrderDetail = new EventOrderDetail(
                dto.getEOID(),
                dto.getStockID(),
                dto.getIssuedFlowers(),
                dto.getPrice()
        );

        return eventOrderDetailDAO.save(eventOrderDetail);
    }

    //transaction
    @Override
    public  boolean updateEvent(EventPlaceDTO eventPlace) throws SQLException {
        //convert dto to entity
        EventOrderDTO eventOrderDTO = eventPlace.getEventOrder();
        EventOrder eventOrder = new EventOrder(
                eventOrderDTO.getEOID(),
                eventOrderDTO.getCID(),
                eventOrderDTO.getEcode(),
                eventOrderDTO.getAmount()
        );

        //convert dto list to entity list
        List<EventOrderDetailDTO> eventDtoList = eventPlace.getEventOrderDetailList();
        List<EventOrderDetail> eventList = new ArrayList<>();
        for (EventOrderDetailDTO dto : eventDtoList) {
            EventOrderDetail orderDetail = new EventOrderDetail(
                    dto.getEOID(),
                    dto.getStockID(),
                    dto.getIssuedFlowers(),
                    dto.getPrice()
            );
            eventList.add(orderDetail);
        }


        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try{  boolean isEventSaverd = eventOrderDAO.save(eventOrder);
            if (isEventSaverd) {
                System.out.println("r");
                boolean isOrderDetailSaved = eventOrderDetailDAO.save(eventList);
                System.out.println("eeee");
                if (isOrderDetailSaved) {
                    boolean isStockUpdate = stockDAO.UPDATEQTY(eventList);
                    System.out.println("ggggg");
                    if (isStockUpdate) {
                        connection.commit();
                        return true;

                    }
                }
            }
            connection.rollback();
            return false;
        }catch (Exception e){
            connection.rollback();
            return false;

        }finally {
            connection.setAutoCommit(true);
        }
    }
}
