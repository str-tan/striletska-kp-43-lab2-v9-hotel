package ua.kpi.controller.command;

import ua.kpi.model.service.RoomService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteRoomCommand implements Command {
    private final RoomService roomService = RoomService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int roomId = Integer.parseInt(request.getParameter("roomId"));
        HttpSession session = request.getSession();

        ua.kpi.model.entity.Room room = roomService.getRoomById(roomId);

        if (room != null && !room.isAvailable()) {
            session.setAttribute("adminMessage", "Помилка: номер заброньований!");
        } else {
            roomService.deleteRoom(roomId);
            session.setAttribute("adminMessage", "Номер " + (room != null ? room.getNumber() : "") + " видалено!");
        }

        return "redirect:" + request.getContextPath() + "/hotel/admin";
    }
}