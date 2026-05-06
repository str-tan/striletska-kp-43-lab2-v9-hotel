package ua.kpi.model.dao.jdbc;

import ua.kpi.model.dao.*;
import ua.kpi.model.dao.exception.DaoException;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcDaoFactory extends DaoFactory {
    private final DataSource dataSource;

    public JdbcDaoFactory() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:comp/env/jdbc/hotel_db");
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public DaoConnection getConnection() {
        try {
            java.sql.Connection connection = dataSource.getConnection();
            try (java.sql.PreparedStatement ps = connection.prepareStatement("SET NAMES 'UTF8'")) {
                ps.execute();
            }
            return new JdbcDaoConnection(connection);
        } catch (java.sql.SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public VisitorDao createVisitorDao(DaoConnection daoConnection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) daoConnection;
        return new JdbcVisitorDao(jdbcConnection.getConnection());
    }

    @Override
    public RoomDao createRoomDao(DaoConnection daoConnection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) daoConnection;
        return new JdbcRoomDao(jdbcConnection.getConnection());
    }

    @Override
    public BookingDao createBookingDao(DaoConnection daoConnection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) daoConnection;
        return new JdbcBookingDao(jdbcConnection.getConnection());
    }
}