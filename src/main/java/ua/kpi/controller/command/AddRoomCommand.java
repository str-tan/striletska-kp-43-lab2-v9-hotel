package ua.kpi.controller.command;

import ua.kpi.model.service.RoomService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddRoomCommand implements Command {
    private final RoomService roomService = RoomService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int number = Integer.parseInt(request.getParameter("number"));
        double price = Double.parseDouble(request.getParameter("price"));
        HttpSession session = request.getSession();

        ua.kpi.model.entity.Room room = new ua.kpi.model.entity.Room.Builder()
                .setNumber(number).setPrice(price).setAvailable(true).build();

        roomService.createRoom(room);
        session.setAttribute("adminMessage", "Новий номер " + number + " успішно додано!");

        return "redirect:" + request.getContextPath() + "/hotel/admin";
    }
}