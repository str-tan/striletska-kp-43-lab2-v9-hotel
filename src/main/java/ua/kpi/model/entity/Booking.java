package ua.kpi.model.entity;

public class Booking {
    private Integer id;
    private Visitor visitor;
    private Room room;
    private int nights;
    private double totalAmount;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Visitor getVisitor() { return visitor; }
    public void setVisitor(Visitor visitor) { this.visitor = visitor; }
    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }
    public int getNights() { return nights; }
    public void setNights(int nights) { this.nights = nights; }
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double total) { this.totalAmount = total; }

    public static class Builder {
        private Booking instance = new Booking();
        public Builder setId(int id) { instance.id = id; return this; }
        public Builder setVisitor(Visitor visitor) { instance.visitor = visitor; return this; }
        public Builder setRoom(Room room) { instance.room = room; return this; }
        public Builder setNights(int nights) { instance.nights = nights; return this; }
        public Builder setTotalAmount(double total) { instance.totalAmount = total; return this; }
        public Booking build() { return instance; }
    }
}