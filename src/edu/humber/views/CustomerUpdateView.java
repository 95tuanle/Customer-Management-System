package edu.humber.views; /*************************************************************************************************
 * Database Pgm Using Java - ITC-5201-RNB – Assignment 2
 * We declare that this assignment is our own work in accordance with Humber Academic Policy.
 * No part of this assignment has been copied manually or electronically from any other source
 * (including websites) or distributed to other students/social media.
 * Name: Swapnil Roy Chowdhury	Student ID: N01469281
 * Name: Nguyen Anh Tuan Le	Student ID: N01414195
 * Date: Mon Feb 14 2022
 **************************************************************************************************/

import edu.humber.Validator;
import edu.humber.controllers.CustomerController;
import edu.humber.models.Customer;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Customer Update View
 * This class forms the GUI for Update Customer frame. It also takes necessary actions to meet
 * the boundary conditions And finally call the method that pushes the data into customers.txt.
 *
 * @author Swapnil Roy Chowdhury & Nguyen Anh Tuan Le
 */
public class CustomerUpdateView extends JFrame {
    private final Border defaultJTextFieldBorder = new JTextField().getBorder();

    public CustomerUpdateView(CustomerController customerController, int selectedRow, JTabbedPane jTabbedPane) {
        Customer customer = customerController.getCustomers().get(selectedRow);

        // All fields and respective labels are grouped in a panel with grid layout
        JPanel gridJPanel = new JPanel(new GridLayout(9, 2));
        // All button are grouped into a panel with flow layout
        JPanel flowJPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JLabel titleJLabel = new JLabel("Customer Details");
        titleJLabel.setFont(new Font("", Font.BOLD, 13));
        JLabel nameJLabel = new JLabel("Name");
        JLabel phoneJLabel = new JLabel("Phone");
        JLabel emailJLabel = new JLabel("Email");
        JLabel postalCodeJLabel = new JLabel("Postal Code");

        JLabel titleLineBreakJLabel = new JLabel();
        JLabel nameLineBreak = new JLabel();
        JLabel phoneLineBreak = new JLabel();
        JLabel emailLineBreak = new JLabel();
        JLabel postalCodeLineBreak = new JLabel();

        JLabel nameResultJLabel = new JLabel();
        JLabel phoneResultJLabel = new JLabel();
        JLabel emailResultJLabel = new JLabel();
        JLabel postalCodeResultJLabel = new JLabel();

        JTextField nameJTextField = new JTextField(customer.getName());
        JTextField phoneJTextField = new JTextField(customer.getPhone());
        JTextField emailJTextField = new JTextField(customer.getEmail());
        JTextField postalCodeJTextField = new JTextField(customer.getPostalCode());

        JButton cancelJButton = new JButton("CANCEL");
        JButton updateJButton = new JButton("UPDATE");

        gridJPanel.add(titleJLabel);
        gridJPanel.add(titleLineBreakJLabel);

        gridJPanel.add(nameLineBreak);
        gridJPanel.add(nameResultJLabel);

        gridJPanel.add(nameJLabel);
        gridJPanel.add(nameJTextField);

        gridJPanel.add(phoneLineBreak);
        gridJPanel.add(phoneResultJLabel);

        gridJPanel.add(phoneJLabel);
        gridJPanel.add(phoneJTextField);

        gridJPanel.add(emailLineBreak);
        gridJPanel.add(emailResultJLabel);

        gridJPanel.add(emailJLabel);
        gridJPanel.add(emailJTextField);

        gridJPanel.add(postalCodeLineBreak);
        gridJPanel.add(postalCodeResultJLabel);

        gridJPanel.add(postalCodeJLabel);
        gridJPanel.add(postalCodeJTextField);

        flowJPanel.add(cancelJButton);
        flowJPanel.add(updateJButton);

        gridJPanel.setPreferredSize(new Dimension(600, 200));
//		All panels are grouped into a single panel with Border layout.
        JPanel updateCustomerJPanel = new JPanel(new BorderLayout());
        updateCustomerJPanel.add(gridJPanel, BorderLayout.NORTH);
        updateCustomerJPanel.add(flowJPanel, BorderLayout.CENTER);

//		Allow user to close the current JFrame with a cancel button
        cancelJButton.addActionListener(e -> this.dispose());

//		When the user clicks update button, the customer is updated after performing a set of checks.
        updateJButton.addActionListener(e -> {
            if (new Validator(defaultJTextFieldBorder).validate(nameJTextField, nameResultJLabel, phoneJTextField, phoneResultJLabel, emailJTextField, emailResultJLabel, postalCodeJTextField, postalCodeResultJLabel)) {
                customerController.updateCustomer(customer.getId(), nameJTextField.getText(), phoneJTextField.getText(), emailJTextField.getText(), postalCodeJTextField.getText());
                JOptionPane.showMessageDialog(null, "Customer has been updated successfully.");
                this.dispose();
                CustomerReadView customerReadView = new CustomerReadView(customerController, jTabbedPane);
                jTabbedPane.setComponentAt(jTabbedPane.getSelectedIndex(), customerReadView.getCustomersPage());
            }
        });
        this.setLayout(new BorderLayout());
        this.setSize(600, 400);
        this.setTitle("Update Customer Details");
        this.setLocationRelativeTo(null);
        this.add(updateCustomerJPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }
}
