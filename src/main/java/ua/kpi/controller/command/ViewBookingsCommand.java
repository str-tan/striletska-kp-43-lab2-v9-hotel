package ua.kpi.controller.command;

import ua.kpi.model.service.BookingService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewBookingsCommand implements Command {
    private final BookingService bookingService = BookingService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("bookings", bookingService.getAllBookings());
        return "/WEB-INF/view/user/bookings.jsp";
    }
}