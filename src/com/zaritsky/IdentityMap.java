package com.zaritsky;

import java.util.HashMap;

public class IdentityMap {
    final HashMap<Integer, Ticket> tikets;

    public IdentityMap() {
        tikets = new HashMap<>();
    }

    public boolean isContains(Integer id) {
        if (tikets.containsKey(id))
            return true;
        else return false;
    }

    public void putTicket(Ticket ticket) {
        tikets.put(ticket.getId(), ticket);
    }

    public Ticket getTicket(Integer id) {
        return tikets.get(id);
    }

}
