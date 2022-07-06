package RESTServices.CinemaRoomRESTService.entities;

public class Cinema {
    private int total_rows = 9;
    private int total_columns = 9;
    private Seat[] available_seats = new Seat[81];

    public Cinema() {
        int count = 0;
        for (int i = 0; i < total_rows; i++) {
            for (int j = 0; j < total_columns; j++) {
                available_seats[count] = new Seat(i + 1, j + 1);
                count++;
            }
        }
    }

    public Seat getSeat(int row, int column) {
        for (Seat seat : available_seats) {
            if (seat.getRow() == row && seat.getColumn() == column) {
                return seat;
            }
        }
        return null;
    }

    public int getTotal_rows() {
        return total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public Seat[] getAvailable_seats() {
        return available_seats;
    }

    public void setTotal_rows(int total_rows) {
        this.total_rows = total_rows;
    }

    public void setTotal_columns(int total_columns) {
        this.total_columns = total_columns;
    }

    public void setAvailable_seats(Seat[] available_seats) {
        this.available_seats = available_seats;
    }

    public int countIncome() {
        int income = 0;
        for (Seat seat : available_seats) {
            if (seat.isPurchased()) {
                income = income + seat.getPrice();
            }
        }
        return income;
    }

    public int numberOfVacantSeats() {
        int vacantSeats = 0;
        for (Seat seat : available_seats) {
            if (!seat.isPurchased()) {
                vacantSeats++;
            }
        }
        return vacantSeats;
    }

    public int numberOfPurchasedSeats() {
        int purchasedSeats = 0;
        for (Seat seat : available_seats) {
            if (seat.isPurchased()) {
                purchasedSeats++;
            }
        }
        return purchasedSeats;
    }
}
