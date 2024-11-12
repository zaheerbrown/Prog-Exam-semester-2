package org.example;

public class MovieTickets implements IMovieTickets {
    // Calculate total movie sales for a single movie
    @Override
    public int TotalMovieSales(int[] movieTicketSales) {
        int total = 0;
        for (int sales : movieTicketSales) {
            total += sales;
        }
        return total;
    }

    // Identify the top-performing movie based on total sales
    @Override
    public String TopMovie(String[] movies, int[] totalSales) {
        int maxSales = totalSales[0];
        String topMovie = movies[0];

        for (int i = 1; i < movies.length; i++) {
            if (totalSales[i] > maxSales) {
                maxSales = totalSales[i];
                topMovie = movies[i];
            }
        }
        return topMovie;
    }
}