package org.example;
import java.io.FileWriter;
import java.io.IOException;

public class MovieTickets implements IMovieTickets {
    private String movieName;
    private int numberOfTickets;
    private double ticketPrice;

    public MovieTickets(String movieName, int numberOfTickets, double ticketPrice) {
        this.movieName = movieName;
        this.numberOfTickets = numberOfTickets;
        this.ticketPrice = ticketPrice;
    }

    @Override
    public double CalculateTotalTicketPrice(int numberOfTickets, double ticketPrice) {
        double totalPrice = numberOfTickets * ticketPrice;
        double vat = totalPrice * 0.14; // 14% VAT
        return totalPrice + vat;
    }

    @Override
    public boolean ValidateData(MovieTicketData movieTicketData) {
        return !movieTicketData.getMovieName().isEmpty() &&
                movieTicketData.getTicketPrice() > 0 &&
                movieTicketData.getNumberOfTickets() > 0;
    }

    // Method to save the report to a file
    public void saveReportToFile(double totalWithVAT) {
        try (FileWriter writer = new FileWriter("report.txt", true)) {
            writer.write("MOVIE NAME: " + movieName + "\n");
            writer.write("MOVIE TICKET PRICE: R " + ticketPrice + "\n");
            writer.write("NUMBER OF TICKETS: " + numberOfTickets + "\n");
            writer.write("TOTAL TICKET PRICE: R " + totalWithVAT + "\n\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}