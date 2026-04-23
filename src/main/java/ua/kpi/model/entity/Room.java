package ua.kpi.model.entity;

import java.util.Objects;

public class Room {
    private Integer id; // ID з бази даних
    private int number;
    private double pricePerNight;
    private boolean isAvailable;

    public Room() {}

    public Room(int number, double pricePerNight) {
        this.number = number;
        this.pricePerNight = pricePerNight;
        this.isAvailable = true;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }

    public double getPricePerNight() { return pricePerNight; }
    public void setPricePerNight(double pricePerNight) { this.pricePerNight = pricePerNight; }

    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { this.isAvailable = available; }

    public double calculateTotal(int nights) {
        if (nights <= 0) throw new IllegalArgumentException("Кількість ночей має бути > 0");
        return pricePerNight * nights;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return number == room.number;
    }

    @Override
    public int hashCode() { return Objects.hash(number); }

    @Override
    public String toString() { return "Номер " + number + " ($" + pricePerNight + ")"; }
}
