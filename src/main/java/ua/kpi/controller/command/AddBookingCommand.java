package ua.kpi.controller.command;

import ua.kpi.model.entity.Booking;
import ua.kpi.model.entity.Room;
import ua.kpi.model.entity.Visitor;
import ua.kpi.model.service.BookingService;
import ua.kpi.model.service.RoomService;
import ua.kpi.model.service.VisitorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddBookingCommand implements Command {
    private final BookingService bookingService = BookingService.getInstance();
    private final VisitorService visitorService = VisitorService.getInstance();
    private final RoomService roomService = RoomService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String email = request.getParameter("email");
        String fullName = request.getParameter("fullName");
        int roomId = Integer.parseInt(request.getParameter("roomId"));
        int nights = Integer.parseInt(request.getParameter("nights"));
        double price = Double.parseDouble(request.getParameter("price"));

        Visitor visitor = visitorService.getByEmail(email).orElseGet(() -> {
            Visitor newVisitor = new Visitor.Builder()
                    .setFullName(fullName)
                    .setEmail(email)
                    .build();
            visitorService.create(newVisitor);

            return visitorService.getByEmail(email).get();
        });

        Room room = roomService.getAllRooms().stream()
                .filter(r -> r.getId() == roomId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Помилка: Кімнату не знайдено в базі"));

        Booking booking = new Booking.Builder()
                .setVisitor(visitor)
                .setRoom(room)
                .setNights(nights)
                .setTotalAmount(nights * price)
                .build();

        bookingService.createBooking(booking);

        request.setAttribute("message", "Ваше бронювання успішно оформлено. Чекаємо на вас у Star Rain!");
        request.setAttribute("targetUrl", "/hotel/view-bookings");
        request.setAttribute("btnText", "Переглянути мої бронювання");
        return "/WEB-INF/view/common/success.jsp";
    }
}