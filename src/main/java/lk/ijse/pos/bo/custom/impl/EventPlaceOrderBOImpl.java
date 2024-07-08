package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.EventPlaceOrderBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.*;
import lk.ijse.pos.dao.custom.impl.*;
import lk.ijse.pos.db.DbConnection;
import lk.ijse.pos.dto.*;
import lk.ijse.pos.entity.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventPlaceOrderBOImpl implements EventPlaceOrderBO {
    EventOrderDAO eventOrderDAO = (EventOrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EVENTORDER);
    EventOrderDetailDAO eventOrderDetailDAO = (EventOrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EVENTORDERDETAIL);
    FlowerDAO flowerDAO = (FlowerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.FLOWER);
    StockDAO stockDAO = (StockDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STOCK);
    EventDAO eventDAO = (EventDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EVENT);
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
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
        EventOrder eventOrder = eventOrderDAO.searchByPOID(id);
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

    @Override
    public List<String> getCodes() throws SQLException, ClassNotFoundException {
        return stockDAO.getCodes();
    }

    @Override
    public List<String> getFlowerName() throws SQLException, ClassNotFoundException {
        return flowerDAO.getFlowerName();
    }

    @Override
    public List<String> getNames() throws SQLException, ClassNotFoundException {
       return eventDAO.getNames();
    }

    @Override
    public List<String> getTelNo() throws SQLException, ClassNotFoundException {
       return customerDAO.getTelNo();
    }

    @Override
    public EventDTO searchByName(String name) throws SQLException, ClassNotFoundException {
        Event event = eventDAO.searchByName(name);
        EventDTO eventDTO = new EventDTO(
                event.getEID(),
                event.getEName(),
                event.getEDate(),
                event.getPrice()
        );
        return eventDTO;
    }

    @Override
    public FlowerDTO searchByFlowerName(String name) throws SQLException, ClassNotFoundException {
        Flower flower = flowerDAO.searchByFlowerName(name);
        FlowerDTO flowerDTO = new FlowerDTO(
                flower.getFCode(),
                flower.getFName()
        );
        return flowerDTO;
    }

    @Override
    public CustomerDTO searchByTelNo(String telNo) throws SQLException, ClassNotFoundException {
       Customer customer = customerDAO.searchByTelNo(telNo);
       CustomerDTO customerDTO = new CustomerDTO(
               customer.getCid(),
               customer.getUid(),
               customer.getCname(),
               customer.getTelNo(),
               customer.getAddress()
       );
       return customerDTO;
    }
}
