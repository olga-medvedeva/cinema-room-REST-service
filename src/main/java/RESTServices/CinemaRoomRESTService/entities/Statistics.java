package RESTServices.CinemaRoomRESTService.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Statistics {
    @JsonProperty(value = "current_income")
    private int currentIncome;

    @JsonProperty(value = "number_of_available_seats")
    private int availableSeats;

    @JsonProperty(value = "number_of_purchased_tickets")
    private int purchasedTickets;

    public Statistics(int currentIncome, int availableSeats, int purchasedTickets) {
        this.currentIncome = currentIncome;
        this.availableSeats = availableSeats;
        this.purchasedTickets = purchasedTickets;
    }
}
