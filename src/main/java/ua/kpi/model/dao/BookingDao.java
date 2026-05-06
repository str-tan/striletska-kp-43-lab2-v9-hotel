package ua.kpi.model.dao;

import ua.kpi.model.entity.Booking;
import java.util.List;

public interface BookingDao extends GenericDao<Booking> {
    List<Booking> findByVisitorId(Integer visitorId);
    List<Booking> findByRoomId(int roomId);
}