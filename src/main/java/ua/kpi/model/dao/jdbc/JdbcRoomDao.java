package ua.kpi.model.dao.jdbc;

import ua.kpi.model.dao.RoomDao;
import ua.kpi.model.dao.exception.DaoException;
import ua.kpi.model.entity.Room;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcRoomDao extends AbstractJdbcDao<Room> implements RoomDao {

    public JdbcRoomDao(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Room entity) {
        super.create(entity);
    }

    @Override
    public List<Room> findAvailableRooms() {
        List<Room> result = new ArrayList<>();
        String query = "SELECT * FROM rooms WHERE is_available = true ORDER BY room_number";

        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
                result.add(getEntityFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    protected String getSelectAllQuery() {
        return "SELECT * FROM rooms ORDER BY is_available DESC, room_number ASC";
    }
    @Override protected String getSelectByIdQuery() {
        return "SELECT * FROM rooms WHERE id = ?";
    }
    @Override protected String getCreateQuery() {
        return "INSERT INTO rooms (room_number, price_per_night, is_available) VALUES (?, ?, ?)";
    }
    @Override protected String getUpdateQuery() {
        return "UPDATE rooms SET room_number = ?, price_per_night = ?, is_available = ? WHERE id = ?";
    }
    @Override protected String getDeleteQuery() {
        return "DELETE FROM rooms WHERE id = ?";
    }

    @Override
    protected Room getEntityFromResultSet(ResultSet rs) throws SQLException {
        return new Room.Builder()
                .setId(rs.getInt("id"))
                .setNumber(rs.getInt("room_number"))
                .setPrice(rs.getDouble("price_per_night"))
                .setAvailable(rs.getBoolean("is_available"))
                .build();
    }
    @Override
    public List<Room> findAllSorted(String sortBy) {
        List<Room> result = new ArrayList<>();
        String orderBy = "room_number ASC";

        if ("priceDesc".equals(sortBy)) {
            orderBy = "price_per_night DESC";
        } else if ("priceAsc".equals(sortBy)) {
            orderBy = "price_per_night ASC";
        }

        String query = "SELECT * FROM rooms ORDER BY " + orderBy;

        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
                result.add(getEntityFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    protected void setIdForEntity(Room entity, int id) { entity.setId(id); }

    @Override
    protected void prepareStatementForInsert(PreparedStatement q, Room e) throws SQLException {
        q.setInt(1, e.getNumber());
        q.setDouble(2, e.getPricePerNight());
        q.setBoolean(3, e.isAvailable());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement q, Room e) throws SQLException {
        prepareStatementForInsert(q, e);
        q.setInt(4, e.getId());
    }
}