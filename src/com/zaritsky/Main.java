package com.zaritsky;

import java.sql.SQLException;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        MyDataMapper mapper;
        try {
            SQLDB sqldb = new SQLDB();
            mapper = new MyDataMapper();
            /**Запускается 1 раз для создания БД*/
//            mapper.createDB();
            Ticket ticket = mapper.getTicketByPlace(4);
            System.out.println(ticket.toString());
            List<Ticket> tickets = mapper.getTicketsByPrice(9, 60);
            for (int i = 0; i < tickets.size(); i++) {
                System.out.println(tickets.get(i).toString());
            }
            Ticket tiket = mapper.getTicketById(13);
            Ticket tiket2 = mapper.getTicketById(13);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
