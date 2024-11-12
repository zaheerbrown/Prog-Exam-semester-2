package org.example;

import org.example.IMovieTickets;
import org.example.MovieTicketData;
import org.example.MovieTickets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovieTicketApp extends JFrame implements ActionListener {
    private JComboBox<String> movieComboBox;
    private JTextField ticketsField, priceField;
    private JTextArea reportArea;
    private JMenuItem processMenuItem, clearMenuItem, exitMenuItem;

    public MovieTicketApp() {
        setTitle("Movie Tickets");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create menu
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu toolsMenu = new JMenu("Tools");

        exitMenuItem = new JMenuItem("Exit");
        processMenuItem = new JMenuItem("Process");
        clearMenuItem = new JMenuItem("Clear");

        fileMenu.add(exitMenuItem);
        toolsMenu.add(processMenuItem);
        toolsMenu.add(clearMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        setJMenuBar(menuBar);

        // Panel for input fields
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Movie:"));
        movieComboBox = new JComboBox<>(new String[]{"Napoleon", "Oppenheimer", "Damsel"});
        inputPanel.add(movieComboBox);

        inputPanel.add(new JLabel("Number of Tickets:"));
        ticketsField = new JTextField();
        inputPanel.add(ticketsField);

        inputPanel.add(new JLabel("Ticket Price:"));
        priceField = new JTextField();
        inputPanel.add(priceField);

        // Read-only text area for report
        reportArea = new JTextArea(5, 20);
        reportArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reportArea);

        // Add components to frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Add action listeners
        processMenuItem.addActionListener(this);
        clearMenuItem.addActionListener(this);
        exitMenuItem.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == processMenuItem) {
            processTicket();
        } else if (e.getSource() == clearMenuItem) {
            clearFields();
        } else if (e.getSource() == exitMenuItem) {
            System.exit(0);
        }
    }

    private void processTicket() {
        try {
            String movie = (String) movieComboBox.getSelectedItem();
            int tickets = Integer.parseInt(ticketsField.getText());
            double ticketPrice = Double.parseDouble(priceField.getText());

            MovieTicketData movieData = new MovieTicketData(movie, tickets, ticketPrice);
            IMovieTickets movieTickets = new MovieTickets(movie, tickets, ticketPrice);

            // Validate input
            if (!movieTickets.ValidateData(movieData)) {
                JOptionPane.showMessageDialog(this, "Invalid data: ensure fields are filled correctly.\n"
                                + "Movie Name cannot be empty.\nTicket Price and Number of Tickets must be greater than 0.",
                        "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Calculate total price with VAT
            double totalWithVAT = movieTickets.CalculateTotalTicketPrice(tickets, ticketPrice);

            // Display report in the text area
            reportArea.setText(String.format("MOVIE NAME: %s\nMOVIE TICKET PRICE: R %.2f\nNUMBER OF TICKETS: %d\nTOTAL TICKET PRICE: R %.2f",
                    movie, ticketPrice, tickets, totalWithVAT));

            // Save report to file
            ((MovieTickets) movieTickets).saveReportToFile(totalWithVAT);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for tickets and price.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        ticketsField.setText("");
        priceField.setText("");
        reportArea.setText("");
        movieComboBox.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MovieTicketApp().setVisible(true);
        });
    }
}