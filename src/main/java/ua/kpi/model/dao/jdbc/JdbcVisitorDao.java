package ua.kpi.model.dao.jdbc;

import ua.kpi.model.dao.VisitorDao;
import ua.kpi.model.dao.exception.DaoException;
import ua.kpi.model.entity.Visitor;
import java.sql.*;
import java.util.Optional;

public class JdbcVisitorDao extends AbstractJdbcDao<Visitor> implements VisitorDao {
    public JdbcVisitorDao(Connection connection) { super(connection); }

    @Override
    protected String getSelectAllQuery() {
        return "SELECT * FROM visitors";
    }
    @Override
    protected String getCreateQuery() {
        return "INSERT INTO visitors (full_name, email) VALUES (?, ?)";
    }
    @Override
    protected String getUpdateQuery() {
        return "UPDATE visitors SET full_name = ?, email = ? WHERE id = ?";
    }
    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM visitors WHERE id = ?";
    }
    @Override
    protected String getSelectByIdQuery() {
        return "SELECT * FROM visitors WHERE id = ?";
    }

    @Override
    protected Visitor getEntityFromResultSet(ResultSet rs) throws SQLException {
        return new Visitor.Builder()
                .setId(rs.getInt("id"))
                .setFullName(rs.getString("full_name"))
                .setEmail(rs.getString("email"))
                .build();
    }

    @Override
    protected void setIdForEntity(Visitor entity, int id) { entity.setId(id); }

    @Override
    protected void prepareStatementForInsert(PreparedStatement query, Visitor entity) throws SQLException {
        query.setString(1, entity.getFullName());
        query.setString(2, entity.getEmail());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement query, Visitor entity) throws SQLException {
        prepareStatementForInsert(query, entity);
        query.setInt(3, entity.getId());
    }

    @Override
    public Optional<Visitor> findByEmail(String email) {
        String query = "SELECT * FROM visitors WHERE email = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(getEntityFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.empty();
    }
}