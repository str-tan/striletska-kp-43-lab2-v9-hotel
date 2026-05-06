package ua.kpi.model.service;

import ua.kpi.model.dao.DaoConnection;
import ua.kpi.model.dao.DaoFactory;
import ua.kpi.model.dao.RoomDao;
import ua.kpi.model.entity.Room;

import java.util.List;

public class RoomService {
    private final DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {
        static final RoomService INSTANCE = new RoomService();
    }

    public static RoomService getInstance() {
        return Holder.INSTANCE;
    }

    public void createRoom(Room room) {
        try (ua.kpi.model.dao.DaoConnection connection = daoFactory.getConnection()) {
            daoFactory.createRoomDao(connection).create(room);
        } catch (Exception e) {
            throw new ua.kpi.model.service.exception.ServiceException(e);
        }
    }

    public void updateRoomPrice(int roomId, double newPrice) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            RoomDao roomDao = daoFactory.createRoomDao(connection);
            Room room = roomDao.find(roomId).orElseThrow();
            room.setPricePerNight(newPrice);
            roomDao.update(room);
        }
    }

    public void deleteRoom(int id) {
        try (ua.kpi.model.dao.DaoConnection connection = daoFactory.getConnection()) {
            daoFactory.createRoomDao(connection).delete(id);
        } catch (Exception e) {
            throw new ua.kpi.model.service.exception.ServiceException(e);
        }
    }

    public List<Room> getAllRooms(String sortType) {
        try (ua.kpi.model.dao.DaoConnection connection = daoFactory.getConnection()) {
            return daoFactory.createRoomDao(connection).findAllSorted(sortType);
        }
    }

    public Room getRoomById(int id) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            return daoFactory.createRoomDao(connection).find(id)
                    .orElseThrow(() -> new RuntimeException("Кімнату не знайдено"));
        }
    }

    public List<Room> getAvailableRooms() {
        try (DaoConnection connection = daoFactory.getConnection()) {
            RoomDao roomDao = daoFactory.createRoomDao(connection);
            return roomDao.findAvailableRooms();
        }
    }

    public List<Room> getAllRooms() {
        try (DaoConnection connection = daoFactory.getConnection()) {
            return daoFactory.createRoomDao(connection).findAll();
        }
    }
}