package ua.kpi.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/hotel/*")
public class FrontController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String path = request.getPathInfo();
        Command command;

        try {
            if (path == null || path.equals("/")) {
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
            else if ("/view-bookings".equals(path)) {

                command = new ua.kpi.controller.command.impl.ViewBookingsCommand();

                String page = command.execute(request, response);

                request.getRequestDispatcher(page).forward(request, response);
            }

        } catch (Exception e) {

            request.setAttribute("errorMessage", "Виникла помилка при обробці вашого запиту.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }

        if (path.equals("/test-db")) {
            try (java.sql.Connection conn = ua.kpi.fpspm.utils.DatabaseConnector.getConnection();
                 java.sql.Statement stmt = conn.createStatement();
                 java.sql.ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM rooms")) {

                if (rs.next()) {
                    int count = rs.getInt(1);
                    response.getWriter().println("З'єднання встановлено! Кількість кімнат у базі: " + count);
                }
            } catch (Exception e) {
                response.getWriter().println("Помилка підключення: " + e.getMessage());
            }
        }
    }

    @Override
    public void init() throws ServletException {
        try (Connection connection = ua.kpi.fpspm.utils.DatabaseConnector.getConnection()) {
            if (connection != null && !connection.isClosed()) {
                System.out.println("----------------------------------------------");
                System.out.println("УСПІХ: База даних lab2_v9_hotel підключена!");
                System.out.println("----------------------------------------------");
            }
        } catch (SQLException e) {
            System.err.println("----------------------------------------------");
            System.err.println("ПОМИЛКА БД: " + e.getMessage());
            System.err.println("----------------------------------------------");
        }
    }
}