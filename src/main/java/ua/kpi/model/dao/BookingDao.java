package ua.kpi.model.dao;

import ua.kpi.model.entity.Booking;
import ua.kpi.model.entity.Room;
import ua.kpi.model.entity.Visitor;
import ua.kpi.fpspm.utils.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDao {

    public List<Booking> findAll() throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        // A JOIN request to get everything in one step
        String sql = "SELECT b.id, b.nights, b.total_amount, " +
                "r.room_number, r.price_per_night, " +
                "v.full_name, v.email " +
                "FROM bookings b " +
                "JOIN rooms r ON b.room_id = r.id " +
                "JOIN visitors v ON b.visitor_id = v.id";

        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Visitor visitor = new Visitor(rs.getString("full_name"), rs.getString("email"));
                Room room = new Room(rs.getInt("room_number"), rs.getDouble("price_per_night"));

                Booking booking = new Booking(visitor, room, rs.getInt("nights"));
                booking.setId(rs.getInt("id"));
                booking.setTotalAmount(rs.getDouble("total_amount"));

                bookings.add(booking);
            }
        }
        return bookings;
    }
}