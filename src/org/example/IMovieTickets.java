package org.example;

public interface IMovieTickets {
    int TotalMovieSales(int[] movieTicketSales);
    String TopMovie(String[] movies, int[] totalSales);
}