package ua.kpi.model.entity;

public class Room {
    private Integer id;
    private int number;
    private double pricePerNight;
    private boolean isAvailable;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }
    public double getPricePerNight() { return pricePerNight; }
    public void setPricePerNight(double pricePerNight) { this.pricePerNight = pricePerNight; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    public static class Builder {
        private Room instance = new Room();
        public Builder setId(int id) { instance.id = id; return this; }
        public Builder setNumber(int number) { instance.number = number; return this; }
        public Builder setPrice(double price) { instance.pricePerNight = price; return this; }
        public Builder setAvailable(boolean available) { instance.isAvailable = available; return this; }
        public Room build() { return instance; }
    }
}