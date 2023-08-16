package edu.humber.views; /*************************************************************************************************
 * Database Pgm Using Java - ITC-5201-RNB – Assignment 2
 * We declare that this assignment is our own work in accordance with Humber Academic Policy.
 * No part of this assignment has been copied manually or electronically from any other source
 * (including websites) or distributed to other students/social media.
 * Name: Swapnil Roy Chowdhury	Student ID: N01469281
 * Name: Nguyen Anh Tuan Le	Student ID: N01414195
 * Date: Mon Feb 14 2022
 **************************************************************************************************/

import edu.humber.controllers.CustomerController;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Customer Read View
 * This class is responsible for displaying all the customer details in a tabular format.
 *
 * @author Swapnil Roy Chowdhury & Nguyen Anh Tuan Le
 */
public class CustomerReadView {
    private final JScrollPane jScrollPane;

    public CustomerReadView(CustomerController customerController, JTabbedPane jTabbedPane) {
        String[] header = {"ID", "NAME", "PHONE", "EMAIL", "POSTAL CODE"};
        /*
         * Converting the list of customers into 2D array containing customer details
         * since JTable only accepts 2D array as a parameter
         */
        String[][] data = customerController.getCustomers().stream().map((customer) -> new String[]{
                        customer.getId() + "", customer.getName(), customer.getPhone(),
                        customer.getEmail(), customer.getPostalCode()})
                .toArray(String[][]::new);

        JTable jTable = new JTable(data, header) {
            /*
             * By default, JTable cells are editable. In this design it was not a good idea
             * since it might create a false impression To the user that the data is being edited
             */
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
//		Add listener to JTable to lead user to the edit view
        jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new CustomerUpdateView(customerController, jTable.getSelectedRow(), jTabbedPane);
            }
        });
        jTable.setBounds(20, 50, 400, 500);
        jTable.setRowHeight(50);
        jTable.setShowHorizontalLines(false);
//		Assign scroll features to the pane
        jScrollPane = new JScrollPane(jTable);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    /**
     * @return JScrollPane
     */
    public JScrollPane getCustomersPage() {
        return this.jScrollPane;
    }
}