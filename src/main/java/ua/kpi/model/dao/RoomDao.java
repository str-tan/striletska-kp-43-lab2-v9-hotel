package ua.kpi.model.dao;

import ua.kpi.model.entity.Room;
import java.util.List;

public interface RoomDao extends GenericDao<Room> {
    List<Room> findAvailableRooms();
    List<Room> findAllSorted(String sortBy);
}