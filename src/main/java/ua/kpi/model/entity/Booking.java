package ua.kpi.model.entity;

public class Booking {
    private Integer id;
    private Visitor visitor;
    private Room room;
    private int nights;
    private double totalAmount;

    public Booking() {}

    public Booking(Visitor visitor, Room room, int nights) {
        this.visitor = visitor;
        this.room = room;
        this.nights = nights;
        this.totalAmount = room.calculateTotal(nights);
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Visitor getVisitor() { return visitor; }
    public void setVisitor(Visitor visitor) { this.visitor = visitor; }

    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }

    public int getNights() { return nights; }
    public void setNights(int nights) { this.nights = nights; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    @Override
    public String toString() {
        return "Бронювання #" + id + ": " + visitor.getFullName() +
                " (Номер кімнати " + room.getNumber() + ") на " + nights + " ночей.";
    }
}
