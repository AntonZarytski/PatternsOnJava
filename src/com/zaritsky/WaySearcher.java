package com.zaritsky;

public class WaySearcher extends GPSNavigator implements WayFinder {

    @Override
    public int getDistance(AirplaneTicket ticket) {
      return ticket.getDistance(ticket);
    }
}
