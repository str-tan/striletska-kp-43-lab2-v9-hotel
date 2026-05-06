package ua.kpi.controller.command;

import ua.kpi.model.service.BookingService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteBookingCommand implements Command {
    private final BookingService bookingService = BookingService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int bookingId = Integer.parseInt(request.getParameter("bookingId"));
        bookingService.releaseRoom(bookingId);

        request.setAttribute("message", "Бронювання було успішно скасовано. Номер знову вільний.");
        request.setAttribute("targetUrl", "/hotel/");
        return "/WEB-INF/view/common/success.jsp";
    }
}