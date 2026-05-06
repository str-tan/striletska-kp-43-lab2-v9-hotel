package ua.kpi.model.dao;

import ua.kpi.model.entity.Visitor;
import java.util.Optional;

public interface VisitorDao extends GenericDao<Visitor> {
    Optional<Visitor> findByEmail(String email);
}