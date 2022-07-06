package RESTServices.CinemaRoomRESTService.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReturnOrder {
    @JsonProperty(value = "returned_ticket")
    private Seat returnedTicket;

    public ReturnOrder(Seat returnedTicket) {
        this.returnedTicket = returnedTicket;
    }

    public Seat getReturnedTicket() {
        return returnedTicket;
    }

    public void setReturnedTicket(Seat returnedTicket) {
        this.returnedTicket = returnedTicket;
    }
}
