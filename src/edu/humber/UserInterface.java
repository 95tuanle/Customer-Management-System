package edu.humber; /*************************************************************************************************
 * Database Pgm Using Java - ITC-5201-RNB – Assignment 2
 * We declare that this assignment is our own work in accordance with Humber Academic Policy.
 * No part of this assignment has been copied manually or electronically from any other source
 * (including websites) or distributed to other students/social media.
 * Name: Swapnil Roy Chowdhury	Student ID: N01469281
 * Name: Nguyen Anh Tuan Le	Student ID: N01414195
 * Date: Mon Feb 14 2022
 **************************************************************************************************/

import edu.humber.controllers.CustomerController;
import edu.humber.views.CustomerCreateView;
import edu.humber.views.CustomerReadView;

import javax.swing.*;
import java.awt.*;

/**
 * User Interface
 * This class is responsible for managing the frame containing all the tabbed panes for adding and viewing customers.
 *
 * @author Swapnil Roy Chowdhury & Nguyen Anh Tuan Le
 */
public class UserInterface extends JFrame {
    private final CustomerController customerController;
    private final JTabbedPane jTabbedPane;

    public UserInterface() {
        customerController = new CustomerController();

        jTabbedPane = new JTabbedPane();

//		Add tabs for each functionality to the GUI - Create Customer tab and View Customers tab
        CustomerCreateView customerCreateView = new CustomerCreateView(customerController);
        jTabbedPane.addTab("Add Customer", customerCreateView.getAddCustomerJPanel());

        CustomerReadView customerReadView = new CustomerReadView(customerController, this.jTabbedPane);
        jTabbedPane.addTab("View Customers", customerReadView.getCustomersPage());

//		Refresh the Create Customer and View Customers tabs on tab change after a new customer has been added or updated
        jTabbedPane.addChangeListener(e -> {
            if (jTabbedPane.getTitleAt(jTabbedPane.getSelectedIndex()).equalsIgnoreCase("Add Customer")) {
                CustomerCreateView customerCreateView1 = new CustomerCreateView(customerController);
                jTabbedPane.setComponentAt(jTabbedPane.getSelectedIndex(), customerCreateView1.getAddCustomerJPanel());
            }
            if (jTabbedPane.getTitleAt(jTabbedPane.getSelectedIndex()).equalsIgnoreCase("View Customers")) {
                CustomerReadView customerReadView1 = new CustomerReadView(customerController, this.jTabbedPane);
                jTabbedPane.setComponentAt(jTabbedPane.getSelectedIndex(), customerReadView1.getCustomersPage());
            }
        });

        this.setLayout(new BorderLayout());
        this.setSize(700, 500);
        this.setTitle("Customer Management System");
        this.setLocationRelativeTo(null);
        this.add(jTabbedPane, BorderLayout.CENTER);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * The point of entry for the application.
     */
    public static void main(String[] args) {
        new UserInterface();
    }
}