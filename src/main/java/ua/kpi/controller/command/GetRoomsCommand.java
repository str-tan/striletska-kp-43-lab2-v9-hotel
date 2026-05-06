package ua.kpi.controller.command;

import ua.kpi.model.entity.Room;
import ua.kpi.model.service.RoomService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetRoomsCommand implements Command {
    private final RoomService roomService = RoomService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String sortType = request.getParameter("sort");
        String filterType = request.getParameter("filter");

        List<Room> rooms;

        if ("onlyAvailable".equals(filterType)) {
            rooms = roomService.getAvailableRooms();
        } else {
            rooms = roomService.getAllRooms(sortType);
        }

        request.setAttribute("rooms", rooms);
        return "/index.jsp";
    }
}
