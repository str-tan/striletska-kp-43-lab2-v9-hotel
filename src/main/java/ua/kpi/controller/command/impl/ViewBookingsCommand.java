package ua.kpi.controller.command.impl;

import ua.kpi.controller.Command;
import ua.kpi.model.dao.BookingDao;
import ua.kpi.model.entity.Booking;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ViewBookingsCommand implements Command {
    private final BookingDao bookingDao = new BookingDao();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Booking> bookings = bookingDao.findAll();
            request.setAttribute("bookings", bookings);
            return "/bookings.jsp";
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Не вдалося завантажити список бронювань");
            return "/error.jsp";
        }
    }
}