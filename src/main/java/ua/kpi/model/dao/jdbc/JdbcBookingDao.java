package ua.kpi.model.dao.jdbc;

import ua.kpi.model.dao.BookingDao;
import ua.kpi.model.dao.exception.DaoException;
import ua.kpi.model.entity.Booking;
import ua.kpi.model.entity.Room;
import ua.kpi.model.entity.Visitor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcBookingDao extends AbstractJdbcDao<Booking> implements BookingDao {
    public JdbcBookingDao(Connection connection) { super(connection); }

    @Override
    protected String getSelectAllQuery() {
        return "SELECT b.id AS booking_id, b.nights, b.total_amount, " +
                "v.id AS v_id, v.full_name AS v_name, v.email AS v_email, " +
                "r.id AS r_id, r.room_number AS r_num, r.price_per_night AS r_price " +
                "FROM bookings b " +
                "JOIN visitors v ON b.visitor_id = v.id " +
                "JOIN rooms r ON b.room_id = r.id";
    }

    @Override protected String getSelectByIdQuery() { return getSelectAllQuery() + " WHERE b.id = ?"; }
    @Override protected String getCreateQuery() { return "INSERT INTO bookings (room_id, visitor_id, nights, total_amount) VALUES (?, ?, ?, ?)"; }
    @Override protected String getUpdateQuery() { return "UPDATE bookings SET room_id = ?, visitor_id = ?, nights = ?, total_amount = ? WHERE id = ?"; }
    @Override protected String getDeleteQuery() { return "DELETE FROM bookings WHERE id = ?"; }

    @Override
    public List<Booking> findByRoomId(int roomId) {
        List<Booking> bookings = new ArrayList<>();
        String query = getSelectAllQuery() + " WHERE b.room_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, roomId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) bookings.add(getEntityFromResultSet(rs));
            }
        } catch (SQLException e) { throw new DaoException(e); }
        return bookings;
    }

    @Override
    public List<Booking> findByVisitorId(Integer visitorId) {
        List<Booking> result = new ArrayList<>();
        String query = getSelectAllQuery() + " WHERE b.visitor_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, visitorId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) result.add(getEntityFromResultSet(rs));
            }
        } catch (SQLException e) { throw new DaoException(e); }
        return result;
    }

    @Override
    protected Booking getEntityFromResultSet(ResultSet rs) throws SQLException {
        Visitor visitor = new Visitor.Builder().setId(rs.getInt("v_id")).setFullName(rs.getString("v_name")).setEmail(rs.getString("v_email")).build();
        Room room = new Room.Builder().setId(rs.getInt("r_id")).setNumber(rs.getInt("r_num")).setPrice(rs.getDouble("r_price")).build();
        return new Booking.Builder().setId(rs.getInt("booking_id")).setVisitor(visitor).setRoom(room).setNights(rs.getInt("nights")).setTotalAmount(rs.getDouble("total_amount")).build();
    }

    @Override protected void setIdForEntity(Booking entity, int id) { entity.setId(id); }
    @Override protected void prepareStatementForInsert(PreparedStatement q, Booking e) throws SQLException {
        q.setInt(1, e.getRoom().getId());
        q.setInt(2, e.getVisitor().getId());
        q.setInt(3, e.getNights());
        q.setDouble(4, e.getTotalAmount());
    }
    @Override protected void prepareStatementForUpdate(PreparedStatement q, Booking e) throws SQLException {
        prepareStatementForInsert(q, e);
        q.setInt(5, e.getId());
    }
}