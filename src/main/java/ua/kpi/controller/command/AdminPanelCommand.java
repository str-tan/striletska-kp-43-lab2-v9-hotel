package ua.kpi.controller.command;

import ua.kpi.model.service.RoomService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminPanelCommand implements Command {
    private final RoomService roomService = RoomService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String passwordParam = request.getParameter("password");

        if ("kpi2026".equals(passwordParam)) {
            session.setAttribute("isAdmin", true);
        }

        if (Boolean.TRUE.equals(session.getAttribute("isAdmin"))) {
            request.setAttribute("rooms", roomService.getAllRooms());
            return "/WEB-INF/view/admin/adminPanel.jsp";
        }

        return "redirect:" + request.getContextPath() + "/hotel/";
    }
}