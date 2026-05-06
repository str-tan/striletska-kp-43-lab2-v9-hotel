package ua.kpi.controller.command;

import ua.kpi.model.entity.Room;
import ua.kpi.model.entity.Booking;
import ua.kpi.model.service.RoomService;
import ua.kpi.model.service.BookingService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class RoomDetailsCommand implements Command {
    private final RoomService roomService = RoomService.getInstance();
    private final BookingService bookingService = BookingService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int roomId = Integer.parseInt(request.getParameter("roomId"));

        Room room = roomService.getRoomById(roomId);
        List<Booking> bookings = bookingService.getBookingsByRoomId(roomId);

        request.setAttribute("room", room);
        request.setAttribute("bookings", bookings);

        return "/WEB-INF/view/admin/roomDetails.jsp";
    }
}