package ua.kpi.controller.command;

import ua.kpi.model.entity.Room;
import ua.kpi.model.service.RoomService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateRoomPriceCommand implements Command {
    private final RoomService roomService = RoomService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int roomId = Integer.parseInt(request.getParameter("roomId"));
        double newPrice = Double.parseDouble(request.getParameter("newPrice"));
        HttpSession session = request.getSession();

        Room room = roomService.getRoomById(roomId);

        if (!room.isAvailable()) {
            session.setAttribute("adminMessage", "Помилка: заброньованим номерам не можна змінювати ціну!");
        } else {
            roomService.updateRoomPrice(roomId, newPrice);
            session.setAttribute("adminMessage", "Ціну для номера " + room.getNumber() + " оновлено!");
        }

        return "redirect:" + request.getContextPath() + "/hotel/admin";
    }
}