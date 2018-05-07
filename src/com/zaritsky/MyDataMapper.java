package com.zaritsky;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MyDataMapper {
    private Connection connection;
    private Statement stmt;
    private ResultSet rs;
    private PreparedStatement ps;
    private IdentityMap map;

    public MyDataMapper() {
        try {
            this.connection = SQLDB.getConnection();
            stmt = connection.createStatement();
            map = new IdentityMap();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createDB() {
        try {
            stmt.execute("CREATE TABLE IF NOT EXISTS 'tickets' ('Id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 'place' INTEGER, 'price' INTEGER, 'holder' TEXT NOT NULL);");
            connection.setAutoCommit(false);
            for (int i = 1; i <= 100; i++) {
                stmt.executeUpdate("INSERT INTO 'tickets'('place', 'price', 'holder') VALUES ('" + i + "','" + i * 5 + "', 'Пассажир" + i + "');");
            }
            connection.commit();
            connection.setAutoCommit(true);
            System.out.println("База данных создана");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean addTicketToBase(Ticket ticket) {
        try {
            stmt.executeUpdate("INSERT INTO 'tickets'('place', 'price', 'holder') VALUES ('" + ticket.getPlace() + "' ,'" + ticket.getPrice() + "', 'Пассажир" + ticket.getHolder() + "');");
            connection.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Ticket getTicketByPlace(int place) {
        try {
            ps = connection.prepareStatement("SELECT * FROM tickets WHERE place=?");
            ps.setInt(1, place);
            rs = ps.executeQuery();
            do {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    int mPlace = rs.getInt(2);
                    String holder = rs.getString(4);
                    int price = rs.getInt(3);
                    return new Ticket(id, mPlace, price, holder);
                } else {
                    System.out.println("Такого билета нет, место свободно.");
                    return null;
                }
            } while (false);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<Ticket> getTicketsByPrice(int fromPrice, int toPrice) {
        List<Ticket> tikets = new ArrayList<>();
        try {
            ps = connection.prepareStatement("SELECT * FROM tickets WHERE price>?");
            ps.setInt(1, fromPrice);
            ps.executeQuery();
            ps = connection.prepareStatement("SELECT * FROM tickets WHERE price<?");
            ps.setInt(1, toPrice);
            rs = ps.executeQuery();
            System.out.println("Список билетов в диапазоне стоимости от " + fromPrice + " до " + toPrice + ":");
            while (rs.next()) {
                tikets.add(new Ticket(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4)));
            }
            return tikets;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Ticket> getTicketsById(int fromId, int toid) {
        List<Ticket> tikets = new ArrayList<>();
        try {
            ps = connection.prepareStatement("SELECT * FROM tickets WHERE Id>?");
            ps.setInt(1, fromId);
            ps.executeQuery();
            ps = connection.prepareStatement("SELECT * FROM tickets WHERE Id<?");
            ps.setInt(1, toid);
            rs = ps.executeQuery();
            System.out.println("Список билетов в диапазоне стоимости от " + fromId + " до " + toid + ":");
            for (int i = fromId; i < toid; i++) {
                if (map.isContains(i)) {
                    tikets.add(map.getTicket(i));
                }
            }
            if (!tikets.isEmpty()) {
                return tikets;
            }
            while (rs.next()) {
                Ticket tiket = new Ticket(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4));
                map.putTicket(tiket);
                tikets.add(tiket);
            }
            return tikets;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Ticket getTicketById(int id) {
        if (map.isContains(id)) {
            System.out.println("Билет достали из мапы");
            return map.getTicket(id);
        }
        try {
            ps = connection.prepareStatement("SELECT * FROM tickets WHERE place=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            do {
                if (rs.next()) {
                    int mPlace = rs.getInt(2);
                    String holder = rs.getString(4);
                    int price = rs.getInt(3);
                    Ticket ticket = new Ticket(id, mPlace, price, holder);
                    map.putTicket(ticket);
                    System.out.println("Билет достали из базы");
                    return ticket;
                } else {
                    System.out.println("Такого билета нет, место свободно.");
                    return null;
                }
            } while (false);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
