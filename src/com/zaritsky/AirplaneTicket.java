package com.zaritsky;

public class AirplaneTicket implements WayFinder {
    private final double price;
    private final int distance;

    public AirplaneTicket(double price, int distance) {
        this.price = price;
        this.distance = distance;
    }

    @Override
    public int getDistance(AirplaneTicket ticket) {
        return ticket.distance;
    }
}
