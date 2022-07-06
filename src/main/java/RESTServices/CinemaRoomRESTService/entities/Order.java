package RESTServices.CinemaRoomRESTService.entities;

import java.util.UUID;

public class Order {
    private String token;
    private Seat ticket;

    public Order(Seat ticket) {
        this.ticket = ticket;
        this.token = UUID.randomUUID().toString();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Seat getTicket() {
        return ticket;
    }

    public void setTicket(Seat ticket) {
        this.ticket = ticket;
    }
}
