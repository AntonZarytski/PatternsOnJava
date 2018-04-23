package com.zaritsky;

/**
 * Реализовал паттерн адаптер
 */
public class Main {

    public static void main(String[] args) {
        AirplaneTicket ticket = new AirplaneTicket(20, 3000);
        GPSNavigator gps = new WaySearcher();
        int restOfWay = ((WaySearcher) gps).getDistance(ticket);

    }
}
