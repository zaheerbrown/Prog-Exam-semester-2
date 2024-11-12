package org.example;

public class MovieTicketReport {
    public static void main(String[] args) {
        // Define movie names and their monthly sales data for January, February, and March
        String[] movies = {"Napoleon", "Oppenheimer"};
        int[][] salesData = {
                {3000, 1500, 1700},  // Sales for Napoleon in Jan, Feb, and Mar
                {3500, 1200, 1600}   // Sales for Oppenheimer in Jan, Feb, and Mar
        };

        // Instantiate MovieTickets class to calculate sales
        MovieTickets movieTickets = new MovieTickets();
        int[] totalSales = new int[movies.length];

        // Display the report header
        System.out.println("MOVIE TICKET SALES REPORT - 2024");
        System.out.println("=================================");

        // Display each movie's sales data and calculate total sales per movie
        System.out.printf("%-12s %-5s %-5s %-5s%n", "Movie", "JAN", "FEB", "MAR");
        for (int i = 0; i < movies.length; i++) {
            int movieTotal = movieTickets.TotalMovieSales(salesData[i]);
            totalSales[i] = movieTotal;

            // Display monthly sales for each movie
            System.out.printf("%-12s %-5d %-5d %-5d%n", movies[i], salesData[i][0], salesData[i][1], salesData[i][2]);

        }

        System.out.println("=================================");

        // Display total ticket sales for each movie
        System.out.println("\nTotal movie ticket sales for " + movies[0] + ": " + totalSales[0]);
        System.out.println("Total movie ticket sales for " + movies[1] + ": " + totalSales[1]);
        System.out.println("=================================");

        // Determine and display the top-performing movie
        String topMovie = movieTickets.TopMovie(movies, totalSales);
        System.out.println("\nTop performing movie: " + topMovie);
    }
}