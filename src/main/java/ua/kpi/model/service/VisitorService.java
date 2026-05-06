package ua.kpi.model.service;

import ua.kpi.model.dao.DaoConnection;
import ua.kpi.model.dao.DaoFactory;
import ua.kpi.model.dao.VisitorDao;
import ua.kpi.model.entity.Visitor;
import ua.kpi.model.service.exception.ServiceException;
import ua.kpi.utils.ErrorsMessages;

import java.util.Optional;

public class VisitorService {
    private final DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {
        static final VisitorService INSTANCE = new VisitorService();
    }

    public static VisitorService getInstance() {
        return Holder.INSTANCE;
    }

    public void create(Visitor visitor) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            VisitorDao dao = daoFactory.createVisitorDao(connection);
            connection.begin();

            Optional<Visitor> existing = dao.findByEmail(visitor.getEmail());
            if (existing.isPresent()) {
                throw new ServiceException(ErrorsMessages.SERVICE_ERROR_USER_EXIST);
            }

            dao.create(visitor);
            connection.commit();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public Optional<Visitor> getByEmail(String email) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            return daoFactory.createVisitorDao(connection).findByEmail(email);
        }
    }
}