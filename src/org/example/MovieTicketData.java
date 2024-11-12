package org.example;

public class MovieTicketData {
    private String movieName;
    private int numberOfTickets;
    private double ticketPrice;

    public MovieTicketData(String movieName, int numberOfTickets, double ticketPrice) {
        this.movieName = movieName;
        this.numberOfTickets = numberOfTickets;
        this.ticketPrice = ticketPrice;
    }

    public String getMovieName() {
        return movieName;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }
}