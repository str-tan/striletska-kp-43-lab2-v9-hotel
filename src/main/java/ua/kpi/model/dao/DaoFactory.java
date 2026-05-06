package ua.kpi.model.dao;

import ua.kpi.model.dao.exception.DaoException;
import java.io.InputStream;
import java.util.Properties;

public abstract class DaoFactory {
    public abstract DaoConnection getConnection();

    public abstract VisitorDao createVisitorDao(DaoConnection daoConnection);
    public abstract RoomDao createRoomDao(DaoConnection daoConnection);
    public abstract BookingDao createBookingDao(DaoConnection daoConnection);

    private static DaoFactory instance;

    public static DaoFactory getInstance() {
        if (instance == null) {
            try (InputStream inputStream = DaoFactory.class.getResourceAsStream("/db.properties")) {
                Properties dbProps = new Properties();
                dbProps.load(inputStream);
                String factoryClass = dbProps.getProperty("factory.class");
                instance = (DaoFactory) Class.forName(factoryClass).getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new DaoException(e);
            }
        }
        return instance;
    }
}
