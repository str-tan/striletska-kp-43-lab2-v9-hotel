package ua.kpi.controller.command;

import ua.kpi.model.service.RoomService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetBookingFormCommand implements Command {
    private final RoomService roomService = RoomService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int roomId = Integer.parseInt(request.getParameter("roomId"));
        request.setAttribute("room", roomService.getAllRooms().stream()
                .filter(r -> r.getId() == roomId).findFirst().orElse(null));

        return "/WEB-INF/view/user/bookingForm.jsp";
    }
}
