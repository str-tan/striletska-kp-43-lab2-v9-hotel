package ua.kpi.model.service;

import ua.kpi.model.dao.DaoConnection;
import ua.kpi.model.dao.DaoFactory;
import ua.kpi.model.entity.Booking;
import ua.kpi.model.entity.Room;
import ua.kpi.model.service.exception.ServiceException;
import java.util.List;

public class BookingService {
    private final DaoFactory daoFactory = DaoFactory.getInstance();
    private static final BookingService INSTANCE = new BookingService();
    public static BookingService getInstance() { return INSTANCE; }

    public void createBooking(Booking booking) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            daoFactory.createBookingDao(connection).create(booking);
            Room room = booking.getRoom();
            room.setAvailable(false);
            daoFactory.createRoomDao(connection).update(room);
            connection.commit();
        } catch (Exception e) { throw new ServiceException(e); }
    }

    public void releaseRoom(int bookingId) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            Booking booking = daoFactory.createBookingDao(connection).find(bookingId).orElseThrow();
            Room room = booking.getRoom();
            room.setAvailable(true);
            daoFactory.createRoomDao(connection).update(room);
            daoFactory.createBookingDao(connection).delete(bookingId);
            connection.commit();
        } catch (Exception e) { throw new ServiceException(e); }
    }

    public List<Booking> getBookingsByRoomId(int roomId) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            return daoFactory.createBookingDao(connection).findByRoomId(roomId);
        }
    }

    public List<Booking> getAllBookings() {
        try (DaoConnection connection = daoFactory.getConnection()) {
            return daoFactory.createBookingDao(connection).findAll();
        }
    }
}