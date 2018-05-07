package com.zaritsky;

public class Ticket {
    private final Integer id;
    private final int place;
    private final int price;
    private final String holder;

    public Ticket(int id, int place, int price, String holder) {
        this.id = id;
        this.place = place;
        this.price = price;
        this.holder = holder;
    }

    public int getPlace() {
        return place;
    }

    public int getPrice() {
        return price;
    }

    public String getHolder() {
        return holder;
    }

    @Override
    public String toString() {
        return "Место: " + place + " Цена: " + price + " владелец: " + holder;
    }

    public Integer getId() {
        return id;
    }
}
