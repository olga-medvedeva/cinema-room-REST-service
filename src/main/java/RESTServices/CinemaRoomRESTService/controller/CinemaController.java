package RESTServices.CinemaRoomRESTService.controller;

import RESTServices.CinemaRoomRESTService.entities.*;
import RESTServices.CinemaRoomRESTService.security.Token;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class CinemaController {

    private Cinema cinema = new Cinema();
    List<Order> orders = new ArrayList<>();

    @GetMapping("/seats")
    public Cinema getSeats() {
        return cinema;
    }

    @PostMapping("/purchase")
    public ResponseEntity purchaseSeat(@RequestBody Seat requestSeat) {
        if (requestSeat.getRow() < 1 || requestSeat.getRow() > 9 || requestSeat.getColumn() < 1 || requestSeat.getColumn() > 9) {
            return new ResponseEntity(Map.of("error", "The number of a row or a column is out of bounds!"), HttpStatus.BAD_REQUEST);
        }
        Seat seat = cinema.getSeat(requestSeat.getRow(), requestSeat.getColumn());
        if (seat.isPurchased()) {
            return new ResponseEntity(Map.of("error", "The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);
        } else {
            seat.setPurchased(true);
            Order order = new Order(seat);
            orders.add(order);
            return new ResponseEntity(order, HttpStatus.OK);
        }
    }

    @PostMapping("/return")
    public ResponseEntity returnSeat(@RequestBody Token token) {
        Order order = null;
        for (Order ord : orders) {
            if (token.getToken().equals(ord.getToken())) {
                order = ord;
            }
        }
        if (order == null) {
            return new ResponseEntity(Map.of("error", "Wrong token!"), HttpStatus.BAD_REQUEST);
        }
        Seat seat = cinema.getSeat(order.getTicket().getRow(), order.getTicket().getColumn());
        seat.setPurchased(false);
        return new ResponseEntity(new ReturnOrder(seat), HttpStatus.OK);
    }

    @PostMapping("/stats")
    public ResponseEntity stats(@RequestParam(required = false) String password) {
        if (password == null || !password.equals("super_secret")) {
            return new ResponseEntity(Map.of("error", "The password is wrong!"), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(new Statistics(cinema.countIncome(), cinema.numberOfVacantSeats(), cinema.numberOfPurchasedSeats()), HttpStatus.OK);
    }
}
