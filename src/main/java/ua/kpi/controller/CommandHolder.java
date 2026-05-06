package ua.kpi.controller;

import ua.kpi.controller.command.*;
import java.util.HashMap;
import java.util.Map;

public class CommandHolder {
    public static final String DELIMITER = ":";
    private final Map<String, Command> commands = new HashMap<>();

    public CommandHolder() {
        initCommands();
    }

    private void initCommands() {

        commands.put("GET" + DELIMITER + "/hotel/", new GetRoomsCommand());
        commands.put("GET" + DELIMITER + "/hotel/view-bookings", new ViewBookingsCommand());
        commands.put("GET" + DELIMITER + "/hotel/booking-form", new GetBookingFormCommand());
        commands.put("POST" + DELIMITER + "/hotel/add-booking", new AddBookingCommand());
        commands.put("POST" + DELIMITER + "/hotel/delete-booking", new DeleteBookingCommand());

        commands.put("GET" + DELIMITER + "/hotel/admin", new AdminPanelCommand());
        commands.put("POST" + DELIMITER + "/hotel/admin", new AdminPanelCommand());

        commands.put("POST" + DELIMITER + "/hotel/admin/add-room", new AddRoomCommand());
        commands.put("POST" + DELIMITER + "/hotel/admin/delete-room", new DeleteRoomCommand());
        commands.put("POST" + DELIMITER + "/hotel/logout", new LogoutCommand());
        commands.put("POST" + DELIMITER + "/hotel/admin/update-price", new UpdateRoomPriceCommand());
        commands.put("GET" + DELIMITER + "/hotel/admin/room-details", new RoomDetailsCommand());
    }

    public Command getCommand(String key) {
        return commands.getOrDefault(key, commands.get("GET" + DELIMITER + "/hotel/"));
    }
}